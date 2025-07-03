package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;

public class PanelInfoTutor extends JPanel {
    private JLabel lblNombre, lblCorreo, lblId, lblTarifa, lblMaxEst;
    private DefaultListModel<String> modeloMaterias;
    private JList<String> listaMaterias;

    public PanelInfoTutor() {
        setLayout(new BorderLayout());

        JPanel infoSuperior = new JPanel(new GridLayout(3, 1));
        lblNombre = new JLabel("Nombre: ");
        lblCorreo = new JLabel("Correo: ");
        lblId = new JLabel("ID: ");
        infoSuperior.add(lblNombre);
        infoSuperior.add(lblCorreo);
        infoSuperior.add(lblId);

        JPanel infoCentral = new JPanel(new GridLayout(1, 2));
        modeloMaterias = new DefaultListModel<>();
        listaMaterias = new JList<>(modeloMaterias);
        JScrollPane scrollMaterias = new JScrollPane(listaMaterias);
        scrollMaterias.setBorder(BorderFactory.createTitledBorder("Materias"));
        infoCentral.add(scrollMaterias);

        JPanel tarifas = new JPanel(new GridLayout(2, 1));
        lblTarifa = new JLabel("Tarifa: ");
        lblMaxEst = new JLabel("Máx Estudiantes: ");
        tarifas.add(lblTarifa);
        tarifas.add(lblMaxEst);
        infoCentral.add(tarifas);

        add(infoSuperior, BorderLayout.NORTH);
        add(infoCentral, BorderLayout.CENTER);
    }

    public void mostrarInfoDeTutor(Tutor tutor) {
        lblNombre.setText("Nombre: " + tutor.getNombre());
        lblCorreo.setText("Correo: " + tutor.getCorreo());
        lblId.setText("ID: " + tutor.getId());
        lblTarifa.setText("Tarifa: $" + tutor.getTarifa() + " /h");
        lblMaxEst.setText("Máx Estudiantes: " + tutor.getMaxEst());

        modeloMaterias.clear();
        for (String m : tutor.getListaMaterias()) {
            modeloMaterias.addElement(m);
        }
    }
}