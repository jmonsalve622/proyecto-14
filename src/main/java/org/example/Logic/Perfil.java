package org.example.Logic;

import java.util.ArrayList;
import java.util.List;

public abstract class Perfil {
    protected String nombre;
    protected String correo;
    protected int id;
    protected List<Clase> calendario = new ArrayList<>();

    public Perfil(String nombre, String correo, int id) {
        this.nombre = nombre;
        this.correo = correo;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public int getId() {
        return id;
    }

    public List<Clase> getCalendario() {
        return calendario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
