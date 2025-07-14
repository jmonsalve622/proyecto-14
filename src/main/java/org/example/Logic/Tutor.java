package org.example.Logic;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tutor extends Perfil implements CancelarClase, Observador, FiltroCalendario {
    private int tarifa;
    private int maxEst;
    private List<Estudiante> estudiantesAsignados = new ArrayList<>();
    private Set<TutorObserver> observadores = new HashSet<>();
    private List<String> listaMaterias = new ArrayList<>();

    public Tutor(String nombre, String correo, int id, int tarifa, int maxEst) {
        super(nombre, correo, id);
        this.tarifa = tarifa;
        this.maxEst = maxEst;
    }

    public int getTarifa() {
        return tarifa;
    }

    public int getMaxEst() {
        return maxEst;
    }

    public List<String> getListaMaterias() {
        return listaMaterias;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public void setMaxEst(int maxEst) {
        this.maxEst = maxEst;
    }

    public void agregarMateria(String materia) {
        String upperMateria = materia.toUpperCase();
        boolean repetido = false;
        for (String m : listaMaterias) {
            if (m.equals(upperMateria)) {
                repetido = true;
                break;
            }
        }
        if (!repetido) {
            listaMaterias.add(upperMateria);
        }
    }
    
    public boolean eliminarMateria(String materia) {
        String upperMateria = materia.toUpperCase();
        return listaMaterias.remove(upperMateria);
    }

    @Override
    public void cancelarClase(Clase clase) {
        for (Clase c : calendario) {
            if (clase.equals(c)) {
                calendario.remove(clase);
            }
        }
    }

    @Override
    public void update(int estudianteId, int tutorId, Clase clase, ClaseAccion claseAccion) {
        if (tutorId == this.id && claseAccion == ClaseAccion.CANCELAR) {
            this.cancelarClase(clase);
        }
    }

    @Override
    public List<Clase> filtrarCalendario(String nombreEstudiante, String materia, List<DayOfWeek> dias) {
        List<Clase> resultado = new ArrayList<>();
        for (Clase c : calendario) {
            boolean condition1;
            boolean condition2;
            boolean condition3;

            if (nombreEstudiante != null) {
                condition1 = nombreEstudiante.equals(c.getTutor().getNombre());
            } else {
                condition1 = true;
            }
            if (materia != null) {
                condition2 = materia.equalsIgnoreCase(c.getMateria());
            } else {
                condition2 = true;
            }
            if (dias != null) {
                condition3 = dias.contains(c.getHorario().getDia());
            } else {
                condition3 = true;
            }

            if (condition1 && condition2 && condition3) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public void agregarObserver(TutorObserver obs) {
        observadores.add(obs);
    }

    public void eliminarObserver(TutorObserver obs) {
        observadores.remove(obs);
    }

    private void notificarObservers() {
        for (TutorObserver obs : observadores) {
            obs.onTutorUpdated(this);
        }
    }

    public boolean agregarEstudiante(Estudiante e) {
        if (estudiantesAsignados.size() < maxEst) {
            estudiantesAsignados.add(e);
            notificarObservers();
            return true;
        }
        return false;
    }

    public boolean estaDisponible() {
        return estudiantesAsignados.size() < maxEst;
    }

    public int getCuposRestantes() {
        return maxEst - estudiantesAsignados.size();
    }

    public List<Estudiante> getEstudiantesAsignados() {
        return estudiantesAsignados;
    }
}