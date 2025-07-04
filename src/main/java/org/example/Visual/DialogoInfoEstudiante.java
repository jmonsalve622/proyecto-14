package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;

public class DialogoInfoEstudiante extends JDialog {
    public DialogoInfoEstudiante(Window parent, Estudiante estudiante) {
        super(parent, "Información del Estudiante", ModalityType.APPLICATION_MODAL);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JTextArea areaInfo = new JTextArea();
        areaInfo.setEditable(false);

        StringBuilder info = new StringBuilder();
        info.append("Nombre: ").append(estudiante.getNombre()).append("\n");
        info.append("Correo: ").append(estudiante.getCorreo()).append("\n");
        info.append("ID: ").append(estudiante.getId()).append("\n");

        areaInfo.setText(info.toString());

        add(new JScrollPane(areaInfo), BorderLayout.CENTER);


        JPanel panelBotones = new JPanel();
        JButton btnModificar = new JButton("Modificar Info");
        JButton btnCalendario = new JButton("Calendario");


        panelBotones.add(btnModificar);
        panelBotones.add(btnCalendario);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
