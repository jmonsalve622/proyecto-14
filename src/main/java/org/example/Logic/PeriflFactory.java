package org.example.Logic;

public abstract class PeriflFactory {
    protected int id = 0;

    protected void incId(){
        this.id++;
    }

    public abstract Perfil createPerfil(String nombre, String correo);
}
