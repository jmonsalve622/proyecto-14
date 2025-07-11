package org.example.Logic;

import java.util.ArrayList;
import java.util.List;

public class ListaPerfiles implements BuscarClase{
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

    @Override
    public List<Clase> buscarClase(String materia, List<Horario> horariosDisp) {
        List<Clase> resultado = new ArrayList<>();
        for (Tutor tutor : tutores) {
            if(tutor.getListaMaterias().contains(materia)) {
                for (Horario horario1 : tutor.getListaDisp()) {
                    for (Horario horario2 : horariosDisp) {
                        if (horario1.contieneHorario(horario2)) {
                            resultado.add(new Clase(materia, tutor, null, horario2));
                        }
                    }
                }
            }
        }
        return resultado;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public List<Tutor> getTutores() {
        return tutores;
    }
}
