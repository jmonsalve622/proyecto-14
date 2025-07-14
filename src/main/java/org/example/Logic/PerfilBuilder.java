package org.example.Logic;

public class PerfilBuilder {
    private String nombre;
    private String correo;
    private int id;
    private int tarifa;
    private int maxEst;

    /**
     *
     * @param nombre del perfil que se quiere crear
     * @return la misma instancia de PerfilBuilder
     */
    public PerfilBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    /**
     *
     * @param correo del perfil que se quiere crear
     * @return la misma instancia de PerfilBuilder
     */
    public PerfilBuilder setCorreo(String correo) {
        this.correo = correo;
        return this;
    }

    /**
     *
     * @param id del perfil que se quiere crear
     * @return la misma instancia de PerfilBuilder
     */
    public PerfilBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @param tarifa del perfil que se quiere crear, solo Tutor
     * @return la misma instancia de PerfilBuilder
     */
    public PerfilBuilder setTarifa(int tarifa) {
        this.tarifa = tarifa;
        return this;
    }

    /**
     *
     * @param maxEst del perfil que se quiere crear, solo Tutor
     * @return la misma instancia de PerfilBuilder
     */
    public PerfilBuilder setMaxEst(int maxEst) {
        this.maxEst = maxEst;
        return this;
    }

    /**
     *
     * @return instancia de Estudiante con los atributos del PerfilBuilder
     */
    public Estudiante buildEstudiante() {
        return new Estudiante(nombre, correo, id);
    }

    /**
     *
     * @return instancia de Tutor con los atributos del PerfilBuilder
     */
    public Tutor buildTutor() {
        return new Tutor(nombre, correo, id, tarifa, maxEst);
    }
}
