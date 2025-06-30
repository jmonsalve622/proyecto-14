package org.example.Logic;
import java.util.List;

public class TutorFactory extends PeriflFactory {
    public Perfil crearTutor(String nombre, String correo, int id, List<String> listmaterias, int tarifa, int maxEst, String horario) {
        return new Tutor(nombre, correo, id, listmaterias, tarifa, maxEst, horario);
    }
    @Override
    public Perfil createPerfil(String nombre, String correo) {
        this.incId();
        return new Estudiante(nombre, correo, this.id);
    }
}
