package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private DefaultListModel<String> modeloTutores;
    private DefaultListModel<String> modeloEstudiantes;
    private ListaPerfiles gestor;

    public Ventana(ListaPerfiles gestor) {
        this.gestor = gestor;

        this.setTitle("Aplicacion de Tutorias");
        setSize(700, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));
        setLocation(100, 100);

        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 20, 0));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        /*
        Aca tenemos el panel de los tutores
         */
        JPanel panelTutores = new JPanel(new BorderLayout());
        panelTutores.setBorder(BorderFactory.createTitledBorder("Tutores"));

        modeloTutores = new DefaultListModel<>();
        for (Tutor t : gestor.getTutores()) {
            modeloTutores.addElement(t.getNombre());
        }
        JList<String> listaTutores = new JList<>(modeloTutores);
        JScrollPane scrollTutores = new JScrollPane(listaTutores);

        JButton btnAgregarTutor = new JButton("+");

        panelTutores.add(scrollTutores, BorderLayout.CENTER);
        panelTutores.add(btnAgregarTutor, BorderLayout.NORTH);

        /*
        Aca tenemos el panel de estudiantes
         */
        JPanel panelEstudiantes = new JPanel(new BorderLayout());
        panelEstudiantes.setBorder(BorderFactory.createTitledBorder("Estudiantes"));

        modeloEstudiantes = new DefaultListModel<>();
        for (Estudiante est : gestor.getEstudiantes()) {
            modeloEstudiantes.addElement(est.getNombre());
        }
        JList<String> listaEstudiantes = new JList<>(modeloEstudiantes);
        JScrollPane scrollEstudiantes = new JScrollPane(listaEstudiantes);

        JButton btnAgregarEstudiante = new JButton("+");
        btnAgregarEstudiante.addActionListener(e -> AgregarEstudiante());

        panelEstudiantes.add(scrollEstudiantes, BorderLayout.CENTER);
        panelEstudiantes.add(btnAgregarEstudiante, BorderLayout.NORTH);

        panelCentral.add(panelTutores);
        panelCentral.add(panelEstudiantes);

        add(panelCentral, BorderLayout.CENTER);
        setVisible(true);
    }

    private void AgregarEstudiante() {
        JTextField campoNombre = new JTextField();
        JTextField campoCorreo = new JTextField();

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(campoNombre);
        panel.add(new JLabel("Correo:"));
        panel.add(campoCorreo);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Nuevo Estudiante",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            String nombre = campoNombre.getText().trim();
            String correo = campoCorreo.getText().trim();

            if (!nombre.isEmpty() && !correo.isEmpty()) {
                Estudiante nuevo = new Estudiante(nombre, correo, 1); //por mientras una id 1 para probar
                gestor.agregarPerfil(nuevo);
                modeloEstudiantes.addElement(String.valueOf(nuevo));
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
            }
        }
    }
}


