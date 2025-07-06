package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;

/*
Esta clase es la que se encarga de inicializar la aplicacion por completo, contiene una instancia a la clase Ventana
 */
public class Aplicacion {
    public static void main(String[] args) {
        ListaPerfiles gestor = new ListaPerfiles();
        SwingUtilities.invokeLater(() -> new Ventana(gestor));
    }
}
