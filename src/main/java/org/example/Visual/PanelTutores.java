package org.example.Visual;

import org.example.Logic.ListaPerfiles;
import org.example.Logic.Tutor;
import org.example.Logic.TutorFactory;

import javax.swing.*;
import java.awt.*;

/**
Este Panel es donde se muestra la lista de Tutores y donde se permite el agregar mas tutores a la lista
 */
public class PanelTutores extends JPanel {
    /**
     * @param Privates Aca tenemos los privates, los cuales contienen dos listas, una que es la lista de perfiles, la cual contiene el
     *                 nombre de todos los perfiles creados y su info, la segunda lista es el modelo que sera usado para mostrar en la visual
     *                 como la lista de tutores, tambien tenemos una instancia al factory de los tutores, por ultimo tenemos un private que
     *                 representa el valor de una ventana como JFrame
     */
    private DefaultListModel<String> modeloTutores;
    private ListaPerfiles gestor;
    private TutorFactory tutorFactory;
    private JFrame frame;

    /**
     * @param gestor es una instancia, osea este parametro, es el que da acceso a toda la lista de los perfiles
     * @param frame es el parametro que representa al frame, osea la ventana que mostrara el codigo
     * @metodo El constructor del panel, aca esta agregado el boton de agregar tutores y permite mostrar la lista junto su info
     */
    public PanelTutores(ListaPerfiles gestor, JFrame frame) {
        this.gestor = gestor;
        this.frame = frame;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Tutores"));

        modeloTutores = new DefaultListModel<>();
        for (Tutor t : gestor.getTutores()) {
            modeloTutores.addElement(t.getNombre());
        }

        JList<String> listaTutores = new JList<>(modeloTutores);
        JScrollPane scrollTutores = new JScrollPane(listaTutores);

        JButton btnAgregarTutor = new JButton("+");

        add(scrollTutores, BorderLayout.CENTER);
        add(btnAgregarTutor, BorderLayout.NORTH);

        btnAgregarTutor.addActionListener(e -> {
            DialogoAgregarTutor dialogo = new DialogoAgregarTutor(frame, new TutorFactory());
            dialogo.setVisible(true);

            if (dialogo.fueGuardado()) {
                Tutor nuevo = dialogo.getTutorCreado();
                gestor.agregarPerfil(nuevo);
                modeloTutores.addElement(nuevo.getNombre());
            }
        });

        listaTutores.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String nombre = listaTutores.getSelectedValue();
                Tutor seleccionado = gestor.getTutores().stream()
                        .filter(t -> t.getNombre().equals(nombre))
                        .findFirst()
                        .orElse(null);
                if (seleccionado != null) {
                    DialogoInfoTutor infoDialog = new DialogoInfoTutor(frame, seleccionado, false);
                    infoDialog.setVisible(true);
                }
            }
        });
    }
}