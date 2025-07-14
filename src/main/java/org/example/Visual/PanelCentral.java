package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Este es el PanelCentral, en este panel es donde se juntan los paneles de estudiantes y tutores en un solo panel
 * para la ventana completa
 */
public class PanelCentral extends JPanel {
    /**
     * @param gestor es una instancia, osea este parametro, es el que da acceso a toda la lista de los perfiles
     * @param frame es el parametro que representa al frame, osea la ventana que mostrara el codigo
     * @metodo 'PanelCentral' Esta encargado de construir un panel donde se fusionaran los paneles de estudiante
     * y tutor en un solo panel
     */
    public PanelCentral(ListaPerfiles gestor, JFrame frame) {
        setLayout(new GridLayout(1, 2, 20, 0));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Tutor> listaTutores = gestor.getTutores();

        PanelTutores panelTutores = new PanelTutores(gestor, frame);
        PanelEstudiantes panelEstudiantes = new PanelEstudiantes(gestor, new EstudianteFactory(), listaTutores);

        add(panelTutores);
        add(panelEstudiantes);
    }
}
