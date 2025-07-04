package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;

public class DialogoInfoTutor extends JDialog {
    public DialogoInfoTutor(JFrame parent, Tutor tutor) {
        super(parent, "Información del Tutor", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JTextArea areaInfo = new JTextArea();
        areaInfo.setEditable(false);

        StringBuilder info = new StringBuilder();
        info.append("Nombre: ").append(tutor.getNombre()).append("\n");
        info.append("Correo: ").append(tutor.getCorreo()).append("\n");
        info.append("ID: ").append(tutor.getId()).append("\n");
        info.append("Tarifa: $").append(tutor.getTarifa()).append("\n");
        info.append("Máx. Estudiantes: ").append(tutor.getMaxEst()).append("\n");
        info.append("Materias:\n");
        for (String materia : tutor.getListaMaterias()) {
            info.append("  - ").append(materia).append("\n");
        }

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
