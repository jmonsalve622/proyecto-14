package org.example.Logic;

public class MainPruebas {
    public static void main(String[] args) {
        Perfil a = new Estudiante("alberto","a@udec.cl", 1);
        Perfil b = new Tutor("pedro","b@udec.cl", 2);
        System.out.println(a.getNombre() + " " + a.getCorreo());
        System.out.println(b.getNombre() + " " + b.getCorreo());
    }
}
