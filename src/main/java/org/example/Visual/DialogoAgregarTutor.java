package org.example.Visual;

import org.example.Logic.Tutor;
import org.example.Logic.TutorFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class DialogoAgregarTutor extends JDialog {
    private JTextField campoNombre;
    private JTextField campoCorreo;
    private JTextField campoTarifa;
    private JTextField campoMaxEstudiantes;
    private DefaultListModel<String> modeloMaterias;
    private JList<String> listaMaterias;

    private TutorFactory tutorFactory;
    private boolean guardado = false;
    private Tutor tutorCreado;

    public DialogoAgregarTutor(JFrame parent) {
        super(parent, "Agregar Tutor", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 10, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        campoNombre = new JTextField();
        campoCorreo = new JTextField();
        campoTarifa = new JTextField();
        campoMaxEstudiantes = new JTextField();

        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(campoNombre);
        panelCampos.add(new JLabel("Correo:"));
        panelCampos.add(campoCorreo);
        panelCampos.add(new JLabel("Tarifa: $"));
        panelCampos.add(campoTarifa);
        panelCampos.add(new JLabel("N° máx. de estudiantes:"));
        panelCampos.add(campoMaxEstudiantes);

        JButton btnHorarios = new JButton("Definir Horarios Disponibles");
        // btnHorarios.addActionListener()
        panelCampos.add(btnHorarios);
        panelCampos.add(new JLabel(""));

        add(panelCampos, BorderLayout.WEST);

        JPanel panelMaterias = new JPanel(new BorderLayout());
        panelMaterias.setBorder(BorderFactory.createTitledBorder("Materias"));

        modeloMaterias = new DefaultListModel<>();
        listaMaterias = new JList<>(modeloMaterias);
        JScrollPane scroll = new JScrollPane(listaMaterias);

        JButton btnAgregarMateria = new JButton("+");
        btnAgregarMateria.addActionListener(e -> {
            String nuevaMateria = JOptionPane.showInputDialog(this, "Ingrese una materia:");
            if (nuevaMateria != null && !nuevaMateria.trim().isEmpty()) {
                modeloMaterias.addElement(nuevaMateria.trim());
            }
        });

        JPanel topMaterias = new JPanel(new BorderLayout());
        topMaterias.add(new JLabel("Materias:"), BorderLayout.WEST);
        topMaterias.add(btnAgregarMateria, BorderLayout.EAST);

        panelMaterias.add(topMaterias, BorderLayout.NORTH);
        panelMaterias.add(scroll, BorderLayout.CENTER);

        add(panelMaterias, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> guardarTutor());
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void guardarTutor() {
        try {
            String nombre = campoNombre.getText().trim();
            String correo = campoCorreo.getText().trim();
            int tarifa = (int) Double.parseDouble(campoTarifa.getText().trim());
            int maxEstudiantes = Integer.parseInt(campoMaxEstudiantes.getText().trim());

            List<String> materias = new ArrayList<>();
            for (int i = 0; i < modeloMaterias.size(); i++) {
                materias.add(modeloMaterias.get(i));
            }

            if (nombre.isEmpty() || correo.isEmpty() || materias.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completa todos los campos y agrega al menos una materia.");
                return;
            }

            tutorCreado = tutorFactory.crearPerfil(nombre, correo, tarifa, maxEstudiantes);
            guardado = true;
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tarifa y N° de estudiantes deben ser valores válidos.");
        }
    }

    public boolean fueGuardado() {
        return guardado;
    }

    public Tutor getTutorCreado() {
        return tutorCreado;
    }
}