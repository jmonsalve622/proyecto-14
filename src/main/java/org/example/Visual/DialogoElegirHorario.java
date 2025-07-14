package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class DialogoElegirHorario extends JDialog {

    public DialogoElegirHorario(Window parent, Tutor tutor, Estudiante estudiante) {
        super(parent, "Elegir Horario con " + tutor.getNombre(), ModalityType.APPLICATION_MODAL);

        setSize(900, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        Set<PanelSeleccionHorario.BloqueHorario> bloquesTutor = convertirHorariosABloques(tutor.getListaDisp());

        PanelSeleccionHorario calendario = new PanelSeleccionHorario();
        calendario.setModoRestringido(bloquesTutor);
        calendario.setBloquesSeleccionados(bloquesTutor);

        add(calendario, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar horarios");
        btnGuardar.addActionListener(e -> {
            Set<PanelSeleccionHorario.BloqueHorario> seleccionados = calendario.getBloquesSeleccionados();

            estudiante.limpiarHorarios();

            for (PanelSeleccionHorario.BloqueHorario b : seleccionados) {
                Horario nuevoHorario = convertirBloqueAHorario(b);
                try {
                    estudiante.agregarHorario(nuevoHorario);
                } catch (ConflicoDeHorarioException ex) {
                    JOptionPane.showMessageDialog(this, "Conflicto: " + ex.getMessage());
                }
            }
            dispose();
        });

        add(btnGuardar, BorderLayout.SOUTH);
    }

    private Set<PanelSeleccionHorario.BloqueHorario> convertirHorariosABloques(List<Horario> listaDisp) {
        Set<PanelSeleccionHorario.BloqueHorario> bloques = new HashSet<>();

        for (Horario h : listaDisp) {
            int columna = h.getDia().getValue(); // Lunes=1 ... Domingo=7

            for (int i = 0; i < PanelSeleccionHorario.FRANJAS.length; i++) {
                String franja = PanelSeleccionHorario.FRANJAS[i];
                String horaInicioStr = franja.split(" - ")[0];
                LocalTime horaInicio = LocalTime.parse(horaInicioStr);

                if (horaInicio.equals(h.getHoraInicio())) {
                    bloques.add(new PanelSeleccionHorario.BloqueHorario(i, columna));
                    break;
                }
            }
        }
        return bloques;
    }

    private Horario convertirBloqueAHorario(PanelSeleccionHorario.BloqueHorario bloque) {
        DayOfWeek dia = DayOfWeek.of(bloque.columna);

        String franja = PanelSeleccionHorario.FRANJAS[bloque.fila];
        String[] partes = franja.split(" - ");
        LocalTime horaInicio = LocalTime.parse(partes[0]);
        LocalTime horaFin = LocalTime.parse(partes[1]);

        return new Horario(dia, horaInicio, horaFin);
    }
}