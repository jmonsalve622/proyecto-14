package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.*;
import java.util.*;
import java.util.List;

/**
Esta clase es la que muestra la lista de tutores en la parte de la ventana de los estudiantes, esta lista solamente
podra ser vista desde la gestion de un perfil de estudiante
 */
public class DialogoListaTutores extends JDialog implements TutorObserver {
    /**
     * @param Privates Estos son los privates, pues el primero es una lista, donde iran guardados los objetos tutores dentro que seran
     *                 usados, tambien hay una instancia a un objeto que sera modelo de un estudiante, por ultimo estan las creaciones
     *                 de los botones que estaran dentro de esta ventana.
     */
    private JList<Tutor> lista;
    private JButton btnVerInfo;
    private JButton btnElegirHorario;
    private Estudiante estudiante;

    /**
     * @param frame es el parametro que representa al frame, osea la ventana que mostrara el codigo
     * @param tutores representa a los objetos que son tutores
     * @param estudiante representa a los objetos que son estudiantes
     * @metodo 'DialogoListaTutores' Este es el constructor de la clase, este crea los detalles de la ventana, su altura, la escritura y por supuesto,
     *     su contenido, la cual es la lista de tutores, el constructor tambien construye dos botones que tendran su propia
     *     funcion, uno sera ver la informacion de un pertil de algun tutor elegido, por ultimo, el otro boton tiene la
     *     funcion de elegir un horario de disponibilidad de algun tutor elegido, pero eso esta en otra clase,
     *     pues aca solo esta el boton con la instacia, pero eso ayuda a colocar otras restricciones como la cantidad
     *     maxima de estudiantes, pues un estudiante tiene permitido elegir solamente a un tutor de la lista, aunque ese tutor
     *     puede ser compartido
     */
    public DialogoListaTutores(JFrame frame, List<Tutor> tutores, Estudiante estudiante) {
        super(frame, "Lista de Tutores", true);
        this.estudiante = estudiante;

        setSize(300, 450);
        setLocationRelativeTo(frame);
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
                new DialogoInfoTutor(frame, seleccionado, true).setVisible(true);
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

            DialogoSeleccionHorario dialogo = new DialogoSeleccionHorario(frame, bloquesDisponibles);
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
            btnElegirHorario.setEnabled(seleccionado != null);
        });

        panelBotones.add(btnVerInfo);
        panelBotones.add(btnElegirHorario);
        add(panelBotones, BorderLayout.SOUTH);
    }

    /**
     * @metodo Abajo tenemos algunos metodos, los cuales estan encargador principalmente de verificar la seleccion de un tutor y
     *         por ultimo, la restriccion de la cantidad maxima de estudiante y tutores que pueden tener estos
     */

    /**
     * @param tutorActualizado representa a un tutor, pues este sera el tutor que sera verificado en el metodo
     */
    @Override
    public void onTutorUpdated(Tutor tutorActualizado) {
        Tutor seleccionado = lista.getSelectedValue();
        if (seleccionado != null && seleccionado.equals(tutorActualizado)) {
            SwingUtilities.invokeLater(() -> {
                btnElegirHorario.setEnabled(tutorActualizado.estaDisponible());
            });
        }
    }

    /**
     * @param tutores representa a los objetos que son tutores
     * @return, este retorna el visto bueno o no, dependiendo de los valores y si son correctos
     */
    private boolean estudianteYaTieneTutor(List<Tutor> tutores) {
        for (Tutor t : tutores) {
            if (t.getEstudiantesAsignados().contains(estudiante)) {
                return true;
            }
        }
        return false;
    }
}