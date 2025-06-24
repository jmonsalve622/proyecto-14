package org.example.Logic;

public class TutorFactory extends PeriflFactory {
    @Override
    public Perfil createPerfil(String nombre, String correo) {
        this.incId();
        return new Estudiante(nombre, correo, this.id);
    }
}