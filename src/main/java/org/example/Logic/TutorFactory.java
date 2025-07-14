package org.example.Logic;

public class TutorFactory extends PerfilFactory {
    /**
     *
     * @param nombre, nombre del tutor a crear
     * @param correo, correo del tutor a crear
     * @param tarifa, tarifa del tutor a crear
     * @param maxEst, cantidad máxima de estudiantes del tutor a crear
     * @return una instancia de Tutor creada con PerfilBuilder
     */
    public Tutor crearPerfil(String nombre, String correo, int tarifa, int maxEst) {
        incId();
        return new PerfilBuilder().setNombre(nombre).setCorreo(correo).setId(getId()).setTarifa(tarifa).setMaxEst(maxEst).buildTutor();
    }

    /**
     * Este método no se ocupado dado la diferencia de argumentos
     * @return devuelve null si se intenta ocupar
     */
    @Override
    public Perfil crearPerfil(String nombre, String correo) {
        return null;
    }
}
