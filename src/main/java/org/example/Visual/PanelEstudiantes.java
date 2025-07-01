package org.example.Visual;
import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class PanelEstudiantes extends JPanel {
    public PanelEstudiantes(List<Estudiante> estudiantes) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Estudiantes"));

        JTextArea area = new JTextArea();
        area.setEditable(false);

        for (Estudiante e : estudiantes) {
            area.append(e.getNombre() + "\n");
        }

        add(new JScrollPane(area), BorderLayout.CENTER);
    }
}
