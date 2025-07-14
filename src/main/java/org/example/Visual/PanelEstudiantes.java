package org.example.Visual;
import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
Este Panel esta encargado de mostrar la lista de estudiantes y tambien permite agregar mas estudiantes a la lista
 */
public class PanelEstudiantes extends JPanel {
    /**
     * @param Privates Aca estan los privates, los cuales contienen dos listas, una la cual es la lista de perfiles, que contendra los
     *                 nombres de todos los perfiles creados, incluyendo tutores y estudiantes, la segunda lista es una lista modelo para
     *                 estudiantes, la cual sera la lista que sera usada para mostrar en la visual como lista de estudiantes.
     *                 El tercer y ultimo private es una instancia a la clase EstudianteFactory, que es la clase donde permite retornar
     *                 un objeto Estudiante ya fabricado y pasado por el Builder.
     */
    private DefaultListModel<Estudiante> modeloEstudiantes;
    private ListaPerfiles gestor;
    private EstudianteFactory estudianteFactory;

    /**
     * @param gestor es una instancia, osea este parametro, es el que da acceso a toda la lista de los perfiles
     * @param estudianteFactory es la instancia que representa la fabrica de estudiantes, pues es aca donde se crean
     * @param listaTutores representa a la lista de tutores que seran creados en el codigo
     * @metodo 'PanelEstudiante' Aca esta el constructor del panel, aca es donde esta el boton de agregado de estudiantes y tambien es donde permite
     *                           la lista de estudiantes y su info
     */
    public PanelEstudiantes(ListaPerfiles gestor, EstudianteFactory estudianteFactory, List listaTutores) {
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

        listaEstudiantes.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Estudiante seleccionado = listaEstudiantes.getSelectedValue();
                if (seleccionado != null) {
                    DialogoInfoEstudiante infoDialog = new DialogoInfoEstudiante((Window) SwingUtilities.getWindowAncestor(this), seleccionado, listaTutores);
                    infoDialog.setVisible(true);
                }
            }
        });

        JButton btnAgregarEstudiante = new JButton("+");
        btnAgregarEstudiante.addActionListener(e -> agregarEstudiante());

        add(scrollEstudiantes, BorderLayout.CENTER);
        add(btnAgregarEstudiante, BorderLayout.NORTH);
    }

    /**
     * @metodo 'agregarEstudiante' Este metodo esta encargado de mostrar una pequeña ventana donde poner la info del estudiante que sera
     *                             agregado y asi para finalmente poder agregarlo a la lista
     */
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
