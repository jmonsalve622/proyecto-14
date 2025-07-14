package org.example.Logic;

public class EstudianteFactory extends PerfilFactory {
    /**
     *
     * @param nombre, nombre del estudiante a crear
     * @param correo, correo del estudiante a crear
     * @return una instancia de Estudiante creada con PerfilBuilder
     */
    @Override
    public Estudiante crearPerfil(String nombre, String correo) {
        incId();
        return new PerfilBuilder().setNombre(nombre).setCorreo(correo).setId(getId()).buildEstudiante();
    }
}