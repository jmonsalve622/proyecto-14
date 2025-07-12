package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class DialogoCalendarioTutor extends JDialog {

    private JTable tablaHorario;
    private DefaultTableModel modeloTabla;
    private Tutor tutor;

    private static final String[] COLUMNAS = {
            "Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
    };

    private static final String[] FILAS = {
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
        for (String franja : FILAS) {
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
        add(new JScrollPane(tablaHorario), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        JButton btnCancelarClase = new JButton("Cancelar clase");
        btnCancelarClase.addActionListener(e -> {
        });

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(e -> {
        });

        panelBotones.add(btnCancelarClase);
        panelBotones.add(btnFiltrar);
        add(panelBotones, BorderLayout.SOUTH);

    }

}
