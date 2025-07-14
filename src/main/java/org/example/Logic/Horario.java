package org.example.Logic;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Horario {
    private DayOfWeek dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Horario(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    /**
     *
     * @param x, tiempo que se quiere comprobar si está entre a y b
     * @param a, tiempo inicial del rango comparable
     * @param b tiempo final del rango comparable
     * @return valor de verdad si x está entre a y b
     */
    private boolean timeBetween(LocalTime x, LocalTime a, LocalTime b) {
        return (x.isAfter(a) || x.equals(a)) && (x.isBefore(b) || x.equals(b));
    }

    /**
     *
     * @param otroHorario horario el cual se compara con la instancia que ocupa el método
     * @return valor de verdad si los horarios se superponen
     */
    public boolean conflictoTiempo(Horario otroHorario) {
        if (otroHorario.getDia().equals(this.dia)) {
            return timeBetween(this.horaInicio, otroHorario.getHoraInicio(), otroHorario.getHoraFin()) || timeBetween(this.horaFin, otroHorario.getHoraInicio(), otroHorario.getHoraFin()) || timeBetween(otroHorario.getHoraInicio(), this.horaInicio, this.horaFin) || timeBetween(otroHorario.getHoraFin(), this.horaInicio, this.horaFin);
        }
        else {
            return false;
        }
    }


    /**
     *
     * @return valor de verdad si horaFin va después de horaInicio
     */
    public boolean horarioValido() {
        return horaInicio.isBefore(horaFin);
    }

    /**
     *
     * @param otroHorario horario el cual se compara con la instancia que ocupa el método
     * @return valor de verdad si el horario que ocupa el método contiene al horario del argumento
     */
    public boolean contieneHorario(Horario otroHorario) {
        if (otroHorario.getDia().equals(this.dia)) {
            return timeBetween(otroHorario.horaInicio, this.horaInicio, this.horaFin) && timeBetween(otroHorario.horaFin, this.horaInicio, this.horaFin);
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
