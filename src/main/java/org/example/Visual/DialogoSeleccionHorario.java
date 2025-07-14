package org.example.Visual;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * Esta clase cumple con la responsabilidad de ser la ventana que lleve consigo el panel del calendario que sera usado
 * por todos los perfiles, sean tutores o estudiantes
 */
public class DialogoSeleccionHorario extends JDialog {
    /**
     * @param Privates estos cumplen con la funcion de guardar info importante que se usara aca dentro, el boolean guardado
     *                 sera utilizado para entregar un valor que indicara la verificacion del bloque de hora,
     *                 el panelseleccionhorario es una instancia directa al calendario que permite la funcionalidad de
     *                 un calendario, por ultimo tenemos los bloquesSeleccionados, pues este indica los bloques que fueron
     *                 marcados en la modificacion del calendario desde el estudiante, los cuales representan el horario
     *                 disponible de un tutor
     */
    private boolean guardado = false;
    private PanelSeleccionHorario panelHorario;
    private Set<PanelSeleccionHorario.BloqueHorario> bloquesSeleccionados;

    /**
     * @param parent es el parametro que representa al frame, osea la ventana que mostrara el codigo
     * @param bloquesIniciales es el parametro que representa a los bloques iniciales del calendario, que son utiles
     *                         para guiar al administrador, como la indicacion del dia
     * @metodo 'DialogoSeleccionHorario' Este es un constructor, encargado de la creacion de un calendario que sea funcional,
     *                                   pues en este calendario se podra marcar sus casillas y guardarlas para ser utilizadas
     *                                   como horario de disponibilidad del tutor elegido
     */
    public DialogoSeleccionHorario(Window parent, Set<PanelSeleccionHorario.BloqueHorario> bloquesIniciales) {
        super(parent, "Horario", ModalityType.APPLICATION_MODAL);
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

    /**
     * @return, devuelve los bloques marcados anteriormente en la ejecucion
     * @metodo 'getBloquesSeleccionados' este metodo es el que entrega a otras clases los bloques que
     *                                   fueron seleccionados como el horario del tutor, estos seran usados especialmente
     *                                   para el calendario del estudiante
     */
    public Set<PanelSeleccionHorario.BloqueHorario> getBloquesSeleccionados() {
        return bloquesSeleccionados;
    }

    /**
     * @return, este retorna un false o true, dependiendo de lo recibido
     * @metodo 'isGuardado' Este metodo es el que informara a otras clases de que los bloques con los que se esta trabajando, son bloques adecuados
     *                       o no, este retornada true si es uno adecuado y false si no es adecuado
     */
    public boolean isGuardado() {
        return guardado;
    }


    /**
     * @param bloquesPermitidos son los mismos bloques marcados anteriormente por el tutor en su calendario
     * @metodo 'setModoRestringido' Este metodo es el que restringe al estudiante en la modificacion del calendario,
     *                              excepto en estos bloques, que son los llamados bloques permitidos, que fueron elegidos
     *                              por el tutor
     */
    public void setModoRestringido(Set<PanelSeleccionHorario.BloqueHorario> bloquesPermitidos) {
        panelHorario.setModoRestringido(bloquesPermitidos);
    }
}