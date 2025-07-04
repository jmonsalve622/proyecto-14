package org.example.Logic;

public class TutorFactory extends PerfilFactory {
    public Tutor crearPerfil(String nombre, String correo, int tarifa, int maxEst) {
        incId();
        return new PerfilBuilder().setNombre(nombre).setCorreo(correo).setId(getId()).setTarifa(tarifa).setMaxEst(maxEst).buildTutor();
    }

    @Override
    public Perfil crearPerfil(String nombre, String correo) {
        return null;
    }
}
