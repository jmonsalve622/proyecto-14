package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private ListaPerfiles gestor;

    public Ventana(ListaPerfiles gestor) {
        this.gestor = gestor;

        setTitle("Aplicación de Tutorías");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(350, 220);

        PanelCentral panelCentral = new PanelCentral(gestor, this);
        add(panelCentral);


        setVisible(true);
    }
}



