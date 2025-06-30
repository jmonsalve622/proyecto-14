package org.example.Logic;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Horario {
    private DayOfWeek dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    private boolean timeBetween(LocalTime x, LocalTime a, LocalTime b) {
        return (x.isAfter(a) || x.equals(a)) && (x.isBefore(b) || x.equals(b));
    }

    public boolean conflictoTiempo(Horario otroHorario) {
        if (otroHorario.getDia().equals(this.dia)) {
            return timeBetween(this.horaInicio, otroHorario.getHoraInicio(), otroHorario.getHoraFin()) || timeBetween(this.horaFin, otroHorario.getHoraInicio(), otroHorario.getHoraFin()) || timeBetween(otroHorario.getHoraInicio(), this.horaInicio, this.horaFin) || timeBetween(otroHorario.getHoraFin(), this.horaInicio, this.horaFin);
        }
        else {
            return false;
        }
    }

    public DayOfWeek getDia() {
        return dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }
}
