package org.example.Logic;
import java.util.Arrays;

public class MainPruebas {
    public static void main(String[] args) {
        ListaPerfiles gestor = new ListaPerfiles();

        PeriflFactory estudianteFactory = new EstudianteFactory();
        TutorFactory tutorFactory = new TutorFactory();

        gestor.agregarPerfil(estudianteFactory.createPerfil("Ana", "Ingeniería"));
        gestor.agregarPerfil(estudianteFactory.createPerfil("Luis", "Medicina"));

        gestor.agregarPerfil(tutorFactory.crearTutor(
                "Laura",
                "",
                1,
                Arrays.asList("Matematicas", "Fisica"),
                25,
                5,
                "Lunes a Viernes, 9am - 2pm" //Esta hora esta en string para probar por mientras
        ));


        gestor.agregarPerfil(tutorFactory.crearTutor(
                "Carlos",
                "Car@si",
                2,
                Arrays.asList("Programacion", "Algoritmos"),
                30,
                3,
                "Martes y Jueves, 4pm - 6pm"
        ));

        Perfil encontrado = gestor.buscarTutor("Laura"); //Coloca algun nombre de los que se agrego arriba,
        if (encontrado != null) {                                //pero tiene que ser tutor, si quieres buscar
            System.out.println("Perfil encontrado");            //algun estudiante, cambia el metodo a 'buscarEstudiante'
        } else {
            System.out.println("Perfil no encontrado");
        }

    }
}
