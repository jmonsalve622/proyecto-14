package org.example.Visual;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    public Ventana() {
        super();

        this.setTitle("Aplicacion de Tutorias");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());


        this.pack();
        this.setResizable(false);

        this.setVisible(true);

    }
}
