package org.example.Logic;

public class EstudianteFactory extends PerfilFactory {
    @Override
    public Estudiante crearPerfil(String nombre, String correo) {
        this.incId();
        return new PerfilBuilder().setNombre(nombre).setCorreo(correo).setId(this.id).buildEstudiante();
    }
}