package org.example.Visual;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class DialogoSeleccionHorario extends JDialog {
    private boolean guardado = false;
    private PanelSeleccionHorario panelHorario;
    private Set<PanelSeleccionHorario.BloqueHorario> bloquesSeleccionados;

    public DialogoSeleccionHorario(Window parent, Set<PanelSeleccionHorario.BloqueHorario> bloquesIniciales) {
        super(parent, "Modificar Horario", ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panelHorario = new PanelSeleccionHorario();
        add(panelHorario, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            bloquesSeleccionados = panelHorario.getBloquesSeleccionados();
            guardado = true;
            dispose();
        });

        add(btnGuardar, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    public Set<PanelSeleccionHorario.BloqueHorario> getBloquesSeleccionados() {
        return bloquesSeleccionados;
    }

    public boolean isGuardado() {
        return guardado;
    }

    public void setModoRestringido(Set<PanelSeleccionHorario.BloqueHorario> bloquesPermitidos) {
        panelHorario.setModoRestringido(bloquesPermitidos);
    }
}