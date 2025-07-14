package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.time.*;
import java.time.format.*;
import java.util.Vector;
import java.util.List;

/**
Esta clase es la que crea el calendario que sera mostrado en la info de cada estudiante de manera independiente
 */
public class DialogoCalendarioEstudiante extends JDialog {
    /**
     * @param Privates Estos son los privates, estos cumplen con el objetivo de guardar informacion vital y traspasar esa info por el codigo
     *     de manera que haga este funcionar el codigo, tambien incluye dos listas que se usaran para representar la visual
     *     del calendario como sus horas y dias de manera visual
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

    /**
     * @param frame es el parametro que representa al frame, osea la ventana que mostrara el codigo
     * @param estudiante representa a los objetos que son estudiantes en el codigo
     * @param listaTutores representa a la lista de tutores que seran usados a medida que se crean de estos y agregados dentro
     * @metodo 'DialogoCalendarioEstudiante' Este es el constructor de la clase, este cumple con la creacion de un calendario en una ventana que no tendra alguna
     *     funcionalidad, hasta apretar un boton que te permita elegir uno de los bloques marcados que son las clases libres
     *     que tendra algun tutor disponible
     */
    public DialogoCalendarioEstudiante(JFrame frame, Estudiante estudiante, List<Tutor> listaTutores) {
        super(frame, "Calendario del Estudiante", true);
        this.estudiante = estudiante;
        setSize(1000, 500);
        setLocationRelativeTo(frame);
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
        tablaHorario.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.WHITE);

                if (column > 0 && estudiante != null) {
                    DayOfWeek dia = DayOfWeek.of(column);
                    LocalTime horaInicioBloque = obtenerHoraDesdeFila(row);

                    for (Horario h : estudiante.getListaDisp()) {
                        if (h.getDia().equals(dia) && timeBetween(horaInicioBloque, h.getHoraInicio(), h.getHoraFin())) {
                            c.setBackground(Color.RED);
                            break;
                        }
                    }
                }

                return c;
            }
        });

        add(new JScrollPane(tablaHorario), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        JButton btnVerClase = new JButton("Ver Clases");
        btnVerClase.addActionListener(e -> {
            DialogoVerClases dialogo = new DialogoVerClases((JFrame) SwingUtilities.getWindowAncestor(this), estudiante);
            dialogo.setVisible(true);
        });

        JButton btnBuscarClase = new JButton("Buscar Clase");
        btnBuscarClase.addActionListener(e -> {
            DialogoListaTutores dialogo = new DialogoListaTutores((JFrame) SwingUtilities.getWindowAncestor(this), (List<Tutor>) listaTutores, estudiante);
            dialogo.setVisible(true);
        });

        panelBotones.add(btnVerClase);
        panelBotones.add(btnBuscarClase);
        add(panelBotones, BorderLayout.SOUTH);
    }

    /**
     * @metodo Estos metodos son utiles cuando se trata de trabajar en la parte del horario en la ejecucion del codigo,
     *     pues estos trabajan en la obtencion de la hora
     */


    /**
     * @param fila representa las filas del calendario
     * @return, este retorna la hora de la fila que fue elegida anteriormente
     */
    private LocalTime obtenerHoraDesdeFila(int fila) {
        String franja = FILAS[fila];
        return LocalTime.parse(franja.split(" - ")[0], DateTimeFormatter.ofPattern("H:mm"));
    }

    /**
     * @param x, tiempo que se quiere comprobar si está entre a y b
     * @param a, tiempo inicial del rango comparable
     * @param b tiempo final del rango comparable
     * @return valor de verdad si x está entre a y b
     */
    private boolean timeBetween(LocalTime x, LocalTime a, LocalTime b) {
        return (x.equals(a) || x.isAfter(a)) && x.isBefore(b);
    }
}
