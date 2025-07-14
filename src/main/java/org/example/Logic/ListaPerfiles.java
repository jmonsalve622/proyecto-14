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

    /**
     *
     * @param perfil que se quiere agregar a una de las dos listas
     */
    public void agregarPerfil(Perfil perfil) {
        if (perfil instanceof Estudiante) {
            estudiantes.add((Estudiante) perfil);
        } else if (perfil instanceof Tutor) {
            tutores.add((Tutor) perfil);
        }
    }

    /**
     *
     * @param id del Estudiante que se quiere buscar en la lista de estudiantes
     * @return instancia del Estudiante si esta en la lista, null si es que no
     */
    public Estudiante buscarEstudiante(int id) {
        for (Estudiante e : estudiantes) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    /**
     *
     * @param id del Tutor que se quiere buscar en la lista de tutores
     * @return instancia del Tutor si esta en la lista, null si es que no
     */
    public Tutor buscarTutor(int id) {
        for (Tutor t : tutores) {
            if (t.getId() == id) {
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
