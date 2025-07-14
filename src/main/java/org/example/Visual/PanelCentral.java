package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/*
Este es el PanelCentral, en este panel es donde se juntan los paneles de estudiantes y tutores en un solo panel
para la ventana completa
 */
public class PanelCentral extends JPanel {
    public PanelCentral(ListaPerfiles gestor, JFrame parentVentana) {
        setLayout(new GridLayout(1, 2, 20, 0));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List listaTutores = (List) gestor.getTutores();

        PanelTutores panelTutores = new PanelTutores(gestor, parentVentana);
        PanelEstudiantes panelEstudiantes = new PanelEstudiantes(gestor, new EstudianteFactory(), (java.util.List) listaTutores);

        add(panelTutores);
        add(panelEstudiantes);
    }
}
