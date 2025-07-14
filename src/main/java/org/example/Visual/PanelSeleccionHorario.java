package org.example.Visual;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/*
Esta clase esta encargada de permitir la eleccion de un horario para un perfil
 */
public class PanelSeleccionHorario extends JPanel {
    private JTable tablaHorario;
    private DefaultTableModel modeloTabla;
    private Set<BloqueHorario> bloquesDisponibles = new HashSet<>();
    private boolean modoRestringido = false;

    private static final String[] COLUMNAS = {
            "Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
    };

    public static final String[] FRANJAS = {
            "08:15 - 09:00", "09:15 - 10:00", "10:15 - 11:00", "11:15 - 12:00",
            "12:15 - 13:00", "13:15 - 14:00", "14:15 - 15:00", "15:15 - 16:00",
            "16:15 - 17:00", "17:15 - 18:00", "18:15 - 19:00", "19:15 - 20:00"
    };

    private Set<BloqueHorario> bloquesSeleccionados = new HashSet<>();

    public PanelSeleccionHorario() {
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(COLUMNAS, 0);
        for (String franja : FRANJAS) {
            Vector<Object> fila = new Vector<>();
            fila.add(franja);
            for (int i = 0; i < 7; i++) fila.add("");
            modeloTabla.addRow(fila);
        }

        tablaHorario = new JTable(modeloTabla) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaHorario.setRowHeight(30);
        tablaHorario.setCellSelectionEnabled(true);
        tablaHorario.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                BloqueHorario bloque = new BloqueHorario(row, column);

                if (column == 0) {
                    c.setBackground(Color.LIGHT_GRAY);
                } else if (bloquesSeleccionados.contains(bloque)) {
                    c.setBackground(Color.RED);
                } else if (modoRestringido && !bloquesDisponibles.contains(bloque)) {
                    c.setBackground(Color.DARK_GRAY);
                } else {
                    c.setBackground(Color.WHITE);
                }

                return c;
            }
        });

        tablaHorario.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tablaHorario.getSelectedRow();
                int col = tablaHorario.getSelectedColumn();
                if (col > 0) {
                    BloqueHorario bloque = new BloqueHorario(row, col);
                    boolean yaSeleccionado = bloquesSeleccionados.contains(bloque);

                    if (modoRestringido) {
                        if (yaSeleccionado) {
                            bloquesSeleccionados.remove(bloque);
                            tablaHorario.repaint();
                        } else if (bloquesDisponibles.contains(bloque)) {
                            bloquesSeleccionados.add(bloque);
                            tablaHorario.repaint();
                        }
                    } else {
                        if (yaSeleccionado) {
                            bloquesSeleccionados.remove(bloque);
                        } else {
                            bloquesSeleccionados.add(bloque);
                        }
                        tablaHorario.repaint();
                    }
                }
            }
        });

        add(new JScrollPane(tablaHorario), BorderLayout.CENTER);
    }

    public Set<BloqueHorario> getBloquesSeleccionados() {
        return bloquesSeleccionados;
    }

    public void setBloquesSeleccionados(Set<BloqueHorario> bloques) {
        this.bloquesSeleccionados.clear();
        if (bloques != null) {
            for (BloqueHorario b : bloques) {
                if (b.columna > 0 && b.columna <= 7) {
                    this.bloquesSeleccionados.add(new BloqueHorario(b.fila, b.columna));
                }
            }
        }
        tablaHorario.repaint();
    }

    public void setModoRestringido(Set<BloqueHorario> bloquesTutor) {
        this.bloquesDisponibles = bloquesTutor;
        this.modoRestringido = true;
        tablaHorario.repaint();
    }

    public static class BloqueHorario {
        public int fila;
        public int columna;

        public BloqueHorario(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BloqueHorario)) return false;
            BloqueHorario that = (BloqueHorario) o;
            return fila == that.fila && columna == that.columna;
        }

        @Override
        public int hashCode() {
            return Objects.hash(fila, columna);
        }
    }
}