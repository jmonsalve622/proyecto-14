package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class DialogoCalendarioTutor extends JDialog {

    private JTable tablaHorario;
    private DefaultTableModel modeloTabla;
    private Tutor tutor;
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("H:mm");

    private static final String[] COLUMNAS = {
            "Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
    };

    public static final String[] FRANJAS = {
            "8:15 - 9:00", "9:15 - 10:00", "10:15 - 11:00", "11:15 - 12:00",
            "12:15 - 13:00", "13:15 - 14:00", "14:15 - 15:00", "15:15 - 16:00",
            "16:15 - 17:00", "17:15 - 18:00", "18:15 - 19:00", "19:15 - 20:00"
    };

    public DialogoCalendarioTutor(JFrame parent, Tutor tutor) {
        super(parent, "Calendario del Tutor", true);
        this.tutor = tutor;
        setSize(1000, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(COLUMNAS, 0);
        for (String franja : FRANJAS) {
            Vector<String> fila = new Vector<>();
            fila.add(franja);
            for (int i = 0; i < 7; i++) {
                fila.add("");
            }
            modeloTabla.addRow(fila);
        }

        tablaHorario = new JTable(modeloTabla) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaHorario.setRowHeight(35);

        tablaHorario.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.WHITE);

                if (column > 0 && tutor != null) {
                    DayOfWeek dia = DayOfWeek.of(column); // Lunes = 1
                    LocalTime horaInicioBloque = obtenerHoraDesdeFila(row);

                    for (Horario h : tutor.getListaDisp()) {
                        if (h.getDia().equals(dia) && timeBetween(horaInicioBloque, h.getHoraInicio(), h.getHoraFin())) {
                            c.setBackground(Color.GREEN);
                            break;
                        }
                    }
                }

                return c;
            }
        });

        add(new JScrollPane(tablaHorario), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        JButton btnBuscarClase = new JButton("Modificar Horario o Cancelar Horario");
        btnBuscarClase.addActionListener(e -> {
            Set<DialogoSeleccionHorario.BloqueHorario> bloquesActuales = new HashSet<>();
            for (Horario h : tutor.getListaDisp()) {
                int fila = h.getHoraInicio().getHour() - 8;
                int columna = h.getDia().getValue();
                DialogoSeleccionHorario.BloqueHorario bloque = new DialogoSeleccionHorario.BloqueHorario(fila, columna);
                bloquesActuales.add(bloque);
            }

            DialogoSeleccionHorario dialogo = new DialogoSeleccionHorario(DialogoCalendarioTutor.this);
            dialogo.setBloquesSeleccionados(bloquesActuales);
            dialogo.setVisible(true);

            Set<DialogoSeleccionHorario.BloqueHorario> nuevosBloques = dialogo.getBloquesSeleccionados();
            if (nuevosBloques != null) {
                tutor.limpiarHorarios();

                for (DialogoSeleccionHorario.BloqueHorario bloque : nuevosBloques) {
                    if (bloque.columna < 1 || bloque.columna > 7) continue;

                    try {
                        DayOfWeek dia = DayOfWeek.of(bloque.columna);
                        if (bloque.fila < 0 || bloque.fila >= FRANJAS.length) continue;

                        LocalTime horaInicio = LocalTime.of(8, 15).plusMinutes(60 * bloque.fila);
                        LocalTime horaFin = horaInicio.plusMinutes(45);
                        Horario nuevo = new Horario(dia, horaInicio, horaFin);
                        tutor.agregarHorario(nuevo);
                    } catch (ConflicoDeHorarioException ex) {
                        System.err.println("Conflicto: " + ex.getMessage());
                    }
                }

                tablaHorario.repaint();
            }
        });
        panelBotones.add(btnBuscarClase);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private LocalTime obtenerHoraDesdeFila(int fila) {
        String franja = FRANJAS[fila];
        return LocalTime.parse(franja.split(" - ")[0], FORMATO_HORA);
    }

    private boolean timeBetween(LocalTime x, LocalTime a, LocalTime b) {
        return (x.equals(a) || x.isAfter(a)) && x.isBefore(b);
    }
}