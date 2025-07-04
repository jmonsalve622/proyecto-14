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
    public List<Clase> filtarCalendario(List<Perfil> tutores, List<String> materias, List<DayOfWeek> dias) {
        List<Clase> resultado = new ArrayList<>();
        for (Clase c : calendario) {
            if (tutores.contains(c.getTutor()) && materias.contains(c.getMateria()) && dias.contains(c.getHorario().getDia())) {
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
