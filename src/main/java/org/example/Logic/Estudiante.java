package org.example.Logic;

public class Estudiante extends Perfil {
    private static int contadorId = 1;
    private int id;

    public Estudiante(String nombre, String correo, int id) {
        super(nombre, correo, id);
        this.id = contadorId++;
    }

    public void crearReserva() {
        
    }

    @Override
    public String toString() {
        return id + " - " + getNombre();
    }
}
