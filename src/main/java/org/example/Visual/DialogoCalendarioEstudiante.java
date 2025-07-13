package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/*
Esta clase es la que crea el calendario que sera mostrado en la info de cada estudiante de manera independiente
 */
public class DialogoCalendarioEstudiante extends JDialog {
    /*
    Estos son los privates, estos cumplen con el objetivo de guardar informacion vital y traspasar esa info por el codigo
    de manera que haga este funcionar el codigo, tambien incluye dos listas que se usaran para representar la visual
    del calendario como sus horas y dias de manera visual
     */
    private JTable tablaHorario;
    private DefaultTableModel modeloTabla;
    private Estudiante estudiante;

    private static final String[] COLUMNAS = {
            "Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
    };

    private static final String[] FILAS = {
            "8:15 - 9:00", "9:15 - 10:00", "10:15 - 11:00", "11:15 - 12:00",
            "12:15 - 13:00", "13:15 - 14:00", "14:15 - 15:00", "15:15 - 16:00",
            "16:15 - 17:00", "17:15 - 18:00", "18:15 - 19:00", "19:15 - 20:00"
    };

    /*
    Este es el constructor de la clase, este cumple con la creacion de un calendario en una ventana que con tendra alguna
    funcionalidad, hasta apretar un boton que te permita elegir uno de los bloques marcados que son las clases libres
    que tendra algun tutor disponible
     */
    public DialogoCalendarioEstudiante(JFrame parent, Estudiante estudiante) {
        super(parent, "Calendario del Estudiante", true);
        this.estudiante = estudiante;
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
        //aun falta hacer el codigo para los botones
        JButton btnBuscarClase = new JButton("Buscar Clase");
        btnBuscarClase.addActionListener(e -> {
        });

        panelBotones.add(btnCancelarClase);
        panelBotones.add(btnFiltrar);
        panelBotones.add(btnBuscarClase);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
