package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Aplicacion {
    public static void main(String[] args) {
        ListaPerfiles gestor = new ListaPerfiles();
        SwingUtilities.invokeLater(() -> new Ventana(gestor));
    }
}
