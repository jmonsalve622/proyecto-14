package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;

/**
Esta clase esta encargada de mostrar la ventana con la informacion del tutor seleccionado en la lista de tutores
 */
public class DialogoInfoTutor extends JDialog {
    /**
     * @param Privates Se usara un private que referira al area donde se mostrara la info.
     *                 Tambien tenemos una instancia a la clase Tutor donde se usaran sus metodos aca
     */
    private JTextArea areaInfo;
    private Tutor tutor;

    /**
     * @param frame es el parametro que representa al frame, osea la ventana que mostrara el codigo
     * @param tutor representa a los objetos que son tutores
     * @param soloLectura es el valor que indicara mas que nada si la info del tutor esta siendo vista desde un tutor o estudiante,
     *                    si este es vista por tutor, no pasa nada, pero en caso de un estudiante, este se bloqueara
     * @metodo 'DialogoInfoTutor' Este es el constructor de la clase, aca es donde se establecera el tamaño de la ventana con la info del tutor y
     *     donde tambien se podra modificar la info y ver su calendario, en el que el mismo calendario se podra filtrar
     *     las mismas clases que tendra el tutor con los estudiantes
     */
    public DialogoInfoTutor(JFrame frame, Tutor tutor, boolean soloLectura) {
        super(frame, "Información del Tutor", true);
        this.tutor = tutor;

        setSize(400, 300);
        setLocationRelativeTo(frame);
        setLayout(new BorderLayout());

        areaInfo = new JTextArea();
        areaInfo.setEditable(false);
        actualizarTexto();

        add(new JScrollPane(areaInfo), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        if (!soloLectura) {
            JButton btnModificar = new JButton("Modificar Info");
            btnModificar.addActionListener(e -> modificarInfoTutor());
            panelBotones.add(btnModificar);

            JButton btnCalendario = new JButton("Calendario");
            btnCalendario.addActionListener(e -> abrirDialogoCalendario());
            panelBotones.add(btnCalendario);
        }

        add(panelBotones, BorderLayout.SOUTH);
    }

    /**
     * @metodo 'actualizarTexto' Este metodo esta encargado de actualizar el texto con la info actual del tutor guardado, se actualiza cada vez
     *                           que se modifica la info, incluyendo la creacion del perfil
     */
    private void actualizarTexto() {
        StringBuilder info = new StringBuilder();
        info.append("Nombre: ").append(tutor.getNombre()).append("\n");
        info.append("Correo: ").append(tutor.getCorreo()).append("\n");
        info.append("ID: ").append(tutor.getId()).append("\n");
        info.append("Tarifa: $").append(tutor.getTarifa()).append("/h\n");
        info.append("Máx. Estudiantes: ").append(tutor.getMaxEst()).append("\n");
        info.append("Materias:\n");
        for (String materia : tutor.getListaMaterias()) {
            info.append("  - ").append(materia).append("\n");
        }

        areaInfo.setText(info.toString());
    }

    /**
     * @metodo 'abrirDialogoCalendario' Este metodo te permite abrir una ventana dentro que incluira el calendario del tutor elegido
     */
    private void abrirDialogoCalendario() {
        DialogoCalendarioTutor dialogoCalendario = new DialogoCalendarioTutor((JFrame) getParent(), tutor);
        dialogoCalendario.setVisible(true);
    }

    /**
     * @metodo 'modificarInfoTutor' Este metodo es el que hace posible la modificacion de los valores del tutor como su nombre, correo, tarifa, maxEst
     *                              y las materias que contiene este
     */
    private void modificarInfoTutor() {
        JTextField campoNombre = new JTextField(tutor.getNombre());
        JTextField campoCorreo = new JTextField(tutor.getCorreo());
        JTextField campoTarifa = new JTextField(String.valueOf(tutor.getTarifa()));
        JTextField campoMaxEst = new JTextField(String.valueOf(tutor.getMaxEst()));
        JTextField campoMateriaNueva = new JTextField();
        JTextField campoMateriaEliminar = new JTextField();

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(campoNombre);
        panel.add(new JLabel("Correo:"));
        panel.add(campoCorreo);
        panel.add(new JLabel("Tarifa:"));
        panel.add(campoTarifa);
        panel.add(new JLabel("Máx. Estudiantes:"));
        panel.add(campoMaxEst);
        panel.add(new JLabel("Agregar Materia:"));
        panel.add(campoMateriaNueva);
        panel.add(new JLabel("Eliminar Materia:"));
        panel.add(campoMateriaEliminar);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Modificar Información del Tutor",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            String nombre = campoNombre.getText().trim();
            String correo = campoCorreo.getText().trim();
            String tarifaStr = campoTarifa.getText().trim();
            String maxEstStr = campoMaxEst.getText().trim();
            String materiaAgregar = campoMateriaNueva.getText().trim();
            String materiaEliminar = campoMateriaEliminar.getText().trim();

            try {
                int tarifa = Integer.parseInt(tarifaStr);
                int maxEst = Integer.parseInt(maxEstStr);

                tutor.setNombre(nombre);
                tutor.setCorreo(correo);
                tutor.setTarifa(tarifa);
                tutor.setMaxEst(maxEst);

                if (!materiaAgregar.isEmpty()) {
                    tutor.agregarMateria(materiaAgregar);
                }

                if (!materiaEliminar.isEmpty()) {
                    tutor.eliminarMateria(materiaEliminar);
                }

                actualizarTexto();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Tarifa y Máx. Estudiantes deben ser números enteros.");
            }
        }
    }
}