package org.example.Logic;

public class EstudianteFactory extends PeriflFactory {
    public Perfil crearPerfil(String nombre, String correo) {
        this.incId();
        return new Estudiante(nombre, correo, this.id);
    }
}