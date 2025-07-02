package org.example.Visual;

import org.example.Logic.ListaPerfiles;

import javax.swing.*;
import java.awt.*;

public class PanelCentral extends JPanel {
    public PanelCentral(ListaPerfiles gestor, JFrame parentVentana) {
        setLayout(new GridLayout(1, 2, 20, 0));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        PanelTutores panelTutores = new PanelTutores(gestor, parentVentana);
        PanelEstudiantes panelEstudiantes = new PanelEstudiantes(gestor);

        add(panelTutores);
        add(panelEstudiantes);
    }
}
