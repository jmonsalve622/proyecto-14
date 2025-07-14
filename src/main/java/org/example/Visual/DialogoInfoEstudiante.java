package org.example.Visual;

import org.example.Logic.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DialogoInfoEstudiante extends JDialog {
    /*
    Aca tenemos los privates, uno de los cuales sera el area donde se trabajara en mostrar la informacion de
    uno de los estudiantes seleccionados, el otro private es una instancia a la clase Estudiante
     */
    private JTextArea areaInfo;
    private Estudiante estudiante;
    private List<Tutor> listaTutores;

    /*
    Este es el constructor de la clase, aca es donde se establecera el tamaño de la ventana con la info de los
    estudiantes, donde tambien se podra modificar la info y ver su calendario, en el que el mismo calendario
    se podra realizar reservas de clases con los tutores disponibles en los horarios pedidos
     */
    public DialogoInfoEstudiante(Window parent, Estudiante estudiante, List<Tutor> listaTutores){
        super(parent, "Información del Estudiante", ModalityType.APPLICATION_MODAL);
        this.estudiante = estudiante;
        this.listaTutores = listaTutores;

        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        areaInfo = new JTextArea();
        areaInfo.setEditable(false);
        actualizarTexto();

        add(new JScrollPane(areaInfo), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnModificar = new JButton("Modificar Info");
        JButton btnCalendario = new JButton("Calendario");

        panelBotones.add(btnModificar);
        panelBotones.add(btnCalendario);
        add(panelBotones, BorderLayout.SOUTH);

        btnModificar.addActionListener(e -> modificarInfoEstudiante());
        btnCalendario.addActionListener(e -> abrirDialogoCalendario());
    }

    /*
    Este metodo esta encargado de actualizar el texto con la info actual del estudiante elegido, se actualiza cada vez
    que se modifica la info, incluyendo la creacion del perfil que cuenta como una actualizacion de la info
     */
    private void actualizarTexto() {
        StringBuilder info = new StringBuilder();
        info.append("Nombre: ").append(estudiante.getNombre()).append("\n");
        info.append("Correo: ").append(estudiante.getCorreo()).append("\n");
        info.append("ID: ").append(estudiante.getId()).append("\n");

        areaInfo.setText(info.toString());
    }

    /*
    Este metodo te permite abrir la ventana que incluye el calendario del estudiante seleccionado
     */
    private void abrirDialogoCalendario() {
        DialogoCalendarioEstudiante dialogoCalendario = new DialogoCalendarioEstudiante((JFrame) getParent(), estudiante, listaTutores);
        dialogoCalendario.setVisible(true);
    }

    /*
    Este metodo es el que hace posible la modificacion de los valores de los estudiantes, valores los cuales
    estan conformados por solamente el nombre y el correo. Existe el valor de la ID, pero este es un valor
    el cual no debe modificarse, ya que representa la identificacion de este en la aplicacion
     */
    private void modificarInfoEstudiante() {
        JTextField campoNombre = new JTextField(estudiante.getNombre());
        JTextField campoCorreo = new JTextField(estudiante.getCorreo());

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Nuevo nombre:"));
        panel.add(campoNombre);
        panel.add(new JLabel("Nuevo correo:"));
        panel.add(campoCorreo);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Modificar Información",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            String nuevoNombre = campoNombre.getText().trim();
            String nuevoCorreo = campoCorreo.getText().trim();

            if (!nuevoNombre.isEmpty() && !nuevoCorreo.isEmpty()) {
                estudiante.setNombre(nuevoNombre);
                estudiante.setCorreo(nuevoCorreo);
                actualizarTexto();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
            }
        }
    }
}