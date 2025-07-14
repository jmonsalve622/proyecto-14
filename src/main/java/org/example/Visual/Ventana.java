package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;

/**
Esta clase es la Ventana, la cual es la que crea la ventana en la que se mostrara el trabajo del codigo en ejecucion
de manera visual, este contiene todos los paneles con los que se trabaja en el codigo
 */
public class Ventana extends JFrame {
    public Ventana(ListaPerfiles gestor) {
        setTitle("Aplicación de Tutorías");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(350, 220);

        PanelCentral panelCentral = new PanelCentral(gestor, this);
        add(panelCentral);

        setVisible(true);
    }
}



