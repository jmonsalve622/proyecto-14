package org.example.Logic;

public abstract class PerfilFactory {
    protected static int id = 0;

    protected void incId(){
        id++;
    }

    protected int getId() {
        return id;
    }

    public abstract Perfil crearPerfil(String nombre, String correo);
}
