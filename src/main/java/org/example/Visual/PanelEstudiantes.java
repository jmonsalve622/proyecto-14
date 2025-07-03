package org.example.Visual;
import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelEstudiantes extends JPanel {
    private DefaultListModel<Estudiante> modeloEstudiantes;
    private ListaPerfiles gestor;
    private EstudianteFactory estudianteFactory;

    public PanelEstudiantes(ListaPerfiles gestor, EstudianteFactory estudianteFactory) {
        this.gestor = gestor;
        this.estudianteFactory = estudianteFactory;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Estudiantes"));

        modeloEstudiantes = new DefaultListModel<>();
        for (Estudiante est : gestor.getEstudiantes()) {
            modeloEstudiantes.addElement(est);
        }
        JList<Estudiante> listaEstudiantes = new JList<>(modeloEstudiantes);
        JScrollPane scrollEstudiantes = new JScrollPane(listaEstudiantes);

        JButton btnAgregarEstudiante = new JButton("+");
        btnAgregarEstudiante.addActionListener(e -> agregarEstudiante());

        add(scrollEstudiantes, BorderLayout.CENTER);
        add(btnAgregarEstudiante, BorderLayout.NORTH);
    }

    private void agregarEstudiante() {
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
                Estudiante nuevo = estudianteFactory.crearPerfil(nombre, correo);
                gestor.agregarPerfil(nuevo);
                modeloEstudiantes.addElement(nuevo);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
            }
        }
    }
}
