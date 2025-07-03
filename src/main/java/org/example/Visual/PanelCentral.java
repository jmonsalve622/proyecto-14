package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;

public class PanelCentral extends JPanel {
    public PanelCentral(ListaPerfiles gestor, JFrame parentVentana) {
        setLayout(new GridLayout(1, 2, 20, 0));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        PanelInfoTutor panelInfo = new PanelInfoTutor();
        PanelTutores panelTutores = new PanelTutores(parentVentana, gestor, panelInfo);
        PanelEstudiantes panelEstudiantes = new PanelEstudiantes(gestor, new EstudianteFactory());

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.add(panelTutores, BorderLayout.WEST);
        panelIzquierdo.add(panelInfo, BorderLayout.CENTER);

        add(panelIzquierdo);
        add(panelEstudiantes);
    }
}
