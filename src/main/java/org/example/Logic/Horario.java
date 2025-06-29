package org.example.Logic;

import java.time.LocalDateTime;

public class Horario {
    private LocalDateTime inicio;
    private LocalDateTime fin;

    private boolean timeBetween(LocalDateTime x, LocalDateTime a, LocalDateTime b) {
        return (x.isAfter(a) || x.isEqual(a)) && (x.isBefore(b) || x.isEqual(b));
    }

    public boolean conflictoTiempo(Horario otroHorario) {
        return timeBetween(this.inicio, otroHorario.getInicio(), otroHorario.getFin()) || timeBetween(this.fin, otroHorario.getInicio(), otroHorario.getFin()) || timeBetween(otroHorario.getInicio(), this.inicio, this.fin) || timeBetween(otroHorario.getFin(), this.inicio, this.fin);
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }
}
