package org.example.Logic;

public class TutorFactory extends PeriflFactory {
    public Tutor crearPerfil(String nombre, String correo, int tarifa, int maxEst) {
        this.incId();
        return new PerfilBuilder().setNombre(nombre).setCorreo(correo).setId(this.id).setTarifa(tarifa).setMaxEst(maxEst).buildTutor();
    }

    @Override
    public Perfil crearPerfil(String nombre, String correo) {
        return null;
    }
}
