package org.example.Logic;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Estudiante extends Perfil implements AgregarClase, CancelarClase, Observador, FiltroCalendario {
    public Estudiante(String nombre, String correo, int id) {
        super(nombre, correo, id);
    }

    @Override
    public void agregarClase(Clase clase) throws ConflicoDeHorarioException {
        for (Clase c : calendario) {
            if (clase.getHorario().conflictoTiempo(c.getHorario())) {
                throw new ConflicoDeHorarioException();
            }
        }
        calendario.add(clase);
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
        if (estudianteId == this.id) {
            if (claseAccion == ClaseAccion.AGREGAR) {
                try {
                    this.agregarClase(clase);
                }
                catch (ConflicoDeHorarioException e) {
                    System.out.println("Conflicto de horario");
                }
            }
            else if (claseAccion == ClaseAccion.CANCELAR) {
                this.cancelarClase(clase);
            }
        }
    }

    @Override
    public List<Clase> filtrarCalendario(String nombreTutor, String materia, List<DayOfWeek> dias) {
        List<Clase> resultado = new ArrayList<>();
        for (Clase c : calendario) {
            boolean condition1;
            boolean condition2;
            boolean condition3;

            if (nombreTutor != null) {
                condition1 = nombreTutor.equals(c.getTutor().getNombre());
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

    @Override
    public String toString() {
        return getNombre();
    }
}
