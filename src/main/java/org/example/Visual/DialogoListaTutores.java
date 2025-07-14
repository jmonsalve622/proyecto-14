package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.*;
import java.util.*;
import java.util.List;

public class DialogoListaTutores extends JDialog implements TutorObserver {
    private JList<Tutor> lista;
    private JButton btnVerInfo;
    private JButton btnElegirHorario;
    private Estudiante estudiante;

    public DialogoListaTutores(JFrame parent, List<Tutor> tutores, Estudiante estudiante) {
        super(parent, "Lista de Tutores", true);
        this.estudiante = estudiante;

        setSize(300, 450);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        lista = new JList<>(new DefaultListModel<>());
        DefaultListModel<Tutor> modelo = (DefaultListModel<Tutor>) lista.getModel();

        for (Tutor t : tutores) {
            modelo.addElement(t);
            t.agregarObserver(this);
        }

        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(lista);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnVerInfo = new JButton("Ver Información");
        btnElegirHorario = new JButton("Elegir Horario");
        btnElegirHorario.setEnabled(false);

        btnVerInfo.addActionListener(e -> {
            Tutor seleccionado = lista.getSelectedValue();
            if (seleccionado != null) {
                new DialogoInfoTutor(parent, seleccionado, true).setVisible(true);
            }
        });

        btnElegirHorario.addActionListener(e -> {
            Tutor seleccionado = lista.getSelectedValue();
            if (seleccionado == null || estudiante == null) return;

            if (estudianteYaTieneTutor(tutores) && !seleccionado.getEstudiantesAsignados().contains(estudiante)) {
                JOptionPane.showMessageDialog(this, "Ya tienes asignado un tutor. Solo puedes modificar tu horario con él.", "No permitido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!seleccionado.estaDisponible() && !seleccionado.getEstudiantesAsignados().contains(estudiante)) {
                JOptionPane.showMessageDialog(this, "Este tutor ya no tiene cupos disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Set<PanelSeleccionHorario.BloqueHorario> bloquesDisponibles = new HashSet<>();
            for (Horario h : seleccionado.getListaDisp()) {
                int fila = (int) Duration.between(LocalTime.of(8, 15), h.getHoraInicio()).toMinutes() / 60;
                int columna = h.getDia().getValue();
                bloquesDisponibles.add(new PanelSeleccionHorario.BloqueHorario(fila, columna));
            }

            DialogoSeleccionHorario dialogo = new DialogoSeleccionHorario(parent, bloquesDisponibles);
            dialogo.setModoRestringido(bloquesDisponibles);
            dialogo.setVisible(true);

            Set<PanelSeleccionHorario.BloqueHorario> seleccionados = dialogo.getBloquesSeleccionados();
            if (seleccionados != null && !seleccionados.isEmpty()) {
                if (!seleccionado.getEstudiantesAsignados().contains(estudiante)) {
                    boolean registrado = seleccionado.agregarEstudiante(estudiante);
                    if (!registrado) {
                        JOptionPane.showMessageDialog(this, "Este tutor ya no tiene cupos disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                for (PanelSeleccionHorario.BloqueHorario bloque : seleccionados) {
                    try {
                        DayOfWeek dia = DayOfWeek.of(bloque.columna);
                        LocalTime horaInicio = LocalTime.of(8, 15).plusMinutes(60 * bloque.fila);
                        LocalTime horaFin = horaInicio.plusMinutes(45);
                        Horario nuevo = new Horario(dia, horaInicio, horaFin);
                        estudiante.agregarHorario(nuevo);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                JOptionPane.showMessageDialog(this, "Horario guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        lista.addListSelectionListener(e -> {
            Tutor seleccionado = lista.getSelectedValue();
            boolean puedeEditar = seleccionado != null &&
                    (seleccionado.getEstudiantesAsignados().contains(estudiante) ||
                            (!estudianteYaTieneTutor(tutores) && seleccionado.estaDisponible()));

            btnElegirHorario.setEnabled(puedeEditar);
        });

        panelBotones.add(btnVerInfo);
        panelBotones.add(btnElegirHorario);
        add(panelBotones, BorderLayout.SOUTH);
    }

    @Override
    public void onTutorUpdated(Tutor tutorActualizado) {
        Tutor seleccionado = lista.getSelectedValue();
        if (seleccionado != null && seleccionado.equals(tutorActualizado)) {
            SwingUtilities.invokeLater(() -> {
                btnElegirHorario.setEnabled(tutorActualizado.estaDisponible());
            });
        }
    }

    private boolean estudianteYaTieneTutor(List<Tutor> tutores) {
        for (Tutor t : tutores) {
            if (t.getEstudiantesAsignados().contains(estudiante)) {
                return true;
            }
        }
        return false;
    }

    private Tutor getTutorDelEstudiante(List<Tutor> tutores) {
        for (Tutor t : tutores) {
            if (t.getEstudiantesAsignados().contains(estudiante)) {
                return t;
            }
        }
        return null;
    }
}