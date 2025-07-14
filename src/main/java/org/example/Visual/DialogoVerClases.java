package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

/**
Esta clase es la encargada de mostrar la ventana con la informacion del horario de las clases del estudiante elegido
 */
public class DialogoVerClases extends JDialog {
    /**
     * @param frame es el parametro que representa al frame, osea la ventana que mostrara el codigo
     * @param estudiante representa un objeto Estudiante
     * @metodo 'DialogoVerClases' Este es el constructor de la clase, aca es donde de construye la ventana y le da su forma, tambien es aca donde
     *                            muestra toda la informacion recibida de las otras clases sobre los horarios de las clases del estudiante,
     *                            el horario ofrece el dia de la clase, y sus horas de inicio y termino
     */
    public DialogoVerClases(JFrame frame, Estudiante estudiante) {
        super(frame, "Clases del Estudiante", true);
        setSize(400, 300);
        setLocationRelativeTo(frame);
        setLayout(new BorderLayout());

        java.util.List<Horario> horarios = estudiante.getListaDisp();

        if (horarios.isEmpty()) {
            add(new JLabel("No tienes clases marcadas."), BorderLayout.CENTER);
            return;
        }

        StringBuilder sb = new StringBuilder("<html><body style='padding:10px;'>");
        sb.append("<h3>Clases marcadas:</h3>");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (Horario h : horarios) {
            sb.append("• <b>Día:</b> ").append(h.getDia())
                    .append(" - <b>Hora:</b> ").append(h.getHoraInicio().format(formatter))
                    .append(" a ").append(h.getHoraFin().format(formatter))
                    .append("<br>");
        }

        sb.append("</body></html>");

        JLabel contenido = new JLabel(sb.toString());
        JScrollPane scrollPane = new JScrollPane(contenido);
        add(scrollPane, BorderLayout.CENTER);

        JButton cerrar = new JButton("Cerrar");
        cerrar.addActionListener(e -> dispose());
        add(cerrar, BorderLayout.SOUTH);
    }
}
