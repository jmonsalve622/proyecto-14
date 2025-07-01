package org.example.Visual;

import org.example.Logic.Tutor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelTutores extends JPanel{
    public PanelTutores(List<Tutor> tutores) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Tutores"));

        JTextArea area = new JTextArea();
        area.setEditable(false);

        for (Tutor t : tutores) {
            area.append(t.getNombre() + "\n");
        }

        add(new JScrollPane(area), BorderLayout.CENTER);
    }
}
