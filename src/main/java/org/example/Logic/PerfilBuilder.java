package org.example.Logic;

public class PerfilBuilder {
    private String nombre;
    private String correo;
    private int id;
    private int tarifa;
    private int maxEst;
    private int actEst= 0;

    public PerfilBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public PerfilBuilder setCorreo(String correo) {
        this.correo = correo;
        return this;
    }

    public PerfilBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public PerfilBuilder setTarifa(int tarifa) {
        this.tarifa = tarifa;
        return this;
    }

    public PerfilBuilder setMaxEst(int maxEst) {
        this.maxEst = maxEst;
        return this;
    }

    public Estudiante buildEstudiante() {
        return new Estudiante(nombre, correo, id);
    }

    public Tutor buildTutor() {
        return new Tutor(nombre, correo, id, tarifa, maxEst);
    }
}
