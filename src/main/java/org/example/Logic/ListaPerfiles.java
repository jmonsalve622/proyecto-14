package org.example.Logic;

import java.util.ArrayList;
import java.util.List;

public class ListaPerfiles {
    private List<Estudiante> estudiantes;
    private List<Tutor> tutores;

    public ListaPerfiles() {
        estudiantes = new ArrayList<>();
        tutores = new ArrayList<>();
    }

    public void agregarPerfil(Perfil perfil) {
        if (perfil instanceof Estudiante) {
            estudiantes.add((Estudiante) perfil);
        } else if (perfil instanceof Tutor) {
            tutores.add((Tutor) perfil);
        }
    }

    public Estudiante buscarEstudiante(String nombre) {
        for (Estudiante e : estudiantes) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    public Tutor buscarTutor(String nombre) {
        for (Tutor t : tutores) {
            if (t.getNombre().equalsIgnoreCase(nombre)) {
                return t;
            }
        }
        return null;
    }
}
