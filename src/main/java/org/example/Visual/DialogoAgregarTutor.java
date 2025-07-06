package org.example.Visual;

import org.example.Logic.Tutor;
import org.example.Logic.TutorFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
Esta clase es donde se trabaja con la informacion del tutor, pues aca es donde el administrador proporcionara la info
que contendra el nuevo perfil del tutor que sera creado
 */
public class DialogoAgregarTutor extends JDialog {
    /*
    Aca tenemos los privates. pues la mayoria de estos estan encargados de conformar uno de los valores que representara
    la info del perfil del tutor que sera creado, tambien contiene una instancia a la clase TutorFactory, que es donde
    se trabaja en la creacion de un nuevo perfil tutor con la informacion que sera ofrecida con los campos
    rellenados en la ejecucion del codigo
     */
    private JTextField campoNombre;
    private JTextField campoCorreo;
    private JTextField campoTarifa;
    private JTextField campoMaxEstudiantes;
    private DefaultListModel<String> modeloMaterias;
    private JList<String> listaMaterias;

    private TutorFactory tutorFactory;
    private boolean guardado = false;
    private Tutor tutorCreado;

    /*
    Aca tenemos el constructor, aca es donde se define el tamaño y el como sera proporcionado los campos donde
    se ingresara la info del nuevo perfil de tutor creado, tambien contiene el boton que permite elegir un horario
    donde se mostraran a los estudiantes, en que horario trabaja este tutor
     */
    public DialogoAgregarTutor(JFrame parent, TutorFactory tutorFactory) {
        super(parent, "Agregar Tutor", true);
        this.tutorFactory = tutorFactory;
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
        //btnHorarios.addActionListener() /aun no se usara hasta tener listo el horario
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

    /*
    Este metodo esta encargado de verificar los valores ingresados en los campos, en tal caso de ser valores correctos,
    entonces este metodo dara su visto bueno y solamente faltara pasar por otro metodo para que este perfil pueda
    ser guardado
     */
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

            for (String materia : materias) {
                tutorCreado.agregarMateria(materia);
            }

            guardado = true;
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tarifa y N° de estudiantes deben ser valores válidos.");
        }
    }

    /*
    Este metodo es el que informara a otras clases de que el perfil con el que se esta trabajando, es un perfil adecuado
    o no, este retornada true si es uno adecuado y false si no es adecuado
     */
    public boolean fueGuardado() {
        return guardado;
    }

    /*
    Este metodo es donde se guarda toda la info del perfil del tutor ya creado
     */
    public Tutor getTutorCreado() {
        return tutorCreado;
    }
}