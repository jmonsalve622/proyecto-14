package org.example.Visual;

import org.example.Logic.ListaPerfiles;
import org.example.Logic.Tutor;
import org.example.Logic.TutorFactory;

import javax.swing.*;
import java.awt.*;

import org.example.Logic.ListaPerfiles;
import org.example.Logic.Tutor;
import org.example.Logic.TutorFactory;

import javax.swing.*;
import java.awt.*;

public class PanelTutores extends JPanel {
    private DefaultListModel<String> modeloTutores;
    private ListaPerfiles gestor;
    private PanelInfoTutor panelInfo;
    private JFrame frame;

    public PanelTutores(JFrame frame, ListaPerfiles gestor, PanelInfoTutor panelInfo) {
        this.frame = frame;
        this.gestor = gestor;
        this.panelInfo = panelInfo;

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
                    panelInfo.mostrarInfoDeTutor(seleccionado);
                }
            }
        });
    }
}