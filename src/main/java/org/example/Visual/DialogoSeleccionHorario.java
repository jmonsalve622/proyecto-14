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
public class DialogoSeleccionHorario extends JDialog {
    /*
    Aca tenemos los privates, pues algunos privates son una forma de guardar informacion que se usara en el
    mismo codigo despues, pero los hay dos privates, son unas listas ya hechas de string,
    estas listas representan lo que se mostrara en la primera fila y columna del calendario, una forma de guiar en el.
     */
    private JTable tablaHorario;
    private DefaultTableModel modeloTabla;

    private static final String[] COLUMNAS = {
            "Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
    };

    private static final String[] FRANJAS = {
            "08:15 - 09:00", "09:15 - 10:00", "10:15 - 11:00", "11:15 - 12:00",
            "12:15 - 13:00", "13:15 - 14:00", "14:15 - 15:00", "15:15 - 16:00",
            "16:15 - 17:00", "17:15 - 18:00", "18:15 - 19:00", "19:15 - 20:00"
    };

    private Set<BloqueHorario> bloquesSeleccionados = new HashSet<>();

    /*
    Este es el constructor de la clase, este construye y le da su forma a la ventana del calendario
    que se esta creando para luego mostrarla de una marena funcional con otros metodos,
    tambien es donde se permite el seleccionar un bloque y este quedara seleccionado marcado con un color,
    para finalmente, que los bloques seleccionados sean guardados para usar en otras clases
     */
    public DialogoSeleccionHorario(Window parent) {
        super(parent, "Seleccionar Horarios Disponibles", ModalityType.APPLICATION_MODAL);

        setSize(900, 500);
        setLocationRelativeTo(parent);
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
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                BloqueHorario bloque = new BloqueHorario(row, column);
                if (bloquesSeleccionados.contains(bloque)) {
                    c.setBackground(Color.GREEN);
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
                    if (bloquesSeleccionados.contains(bloque)) {
                        bloquesSeleccionados.remove(bloque);
                    } else {
                        bloquesSeleccionados.add(bloque);
                    }
                    tablaHorario.repaint();
                }
            }
        });

        add(new JScrollPane(tablaHorario), BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar horarios");
        btnGuardar.addActionListener(e -> dispose());
        add(btnGuardar, BorderLayout.SOUTH);
    }

    /*
    Este es un metodo que permite el retornar y guardar los bloques seleccionados para otras clases
     */
    public Set<BloqueHorario> getBloquesSeleccionados() {
        return bloquesSeleccionados;
    }

    /*
    esta clase es lo que define un bloque de horario, tambien incluye las verificaciones de que este mismo si sea un bloque
     */
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

    /*
    este metodo es el que hace el trabajo de pintar un bloque seleccionado, tiene el metodo de pintar en otro lado
     */
    public void setBloquesSeleccionados(Set<BloqueHorario> bloques) {
        this.bloquesSeleccionados.clear();
        for (BloqueHorario b : bloques) {
            if (b.columna > 0 && b.columna <= 7) {
                this.bloquesSeleccionados.add(new BloqueHorario(b.fila, b.columna));
            }
        }
        tablaHorario.repaint();
    }
}
