package org.example.Logic;
import java.util.List;

public class TutorFactory extends PeriflFactory {
    public Perfil crearPerfil(String nombre, String correo) {
        this.incId();
        return new Estudiante(nombre, correo, this.id);
    }
}
