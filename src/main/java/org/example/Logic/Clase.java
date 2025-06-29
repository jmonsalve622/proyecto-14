package org.example.Logic;
import java.time.*;

public class Clase {
    private String materia;
    private Tutor tutor;
    private Estudiante estudiante;
    private LocalDateTime inicio;
    private LocalDateTime fin;

    private boolean timeBetween(LocalDateTime x, LocalDateTime a, LocalDateTime b) {
        if ((x.isAfter(a) || x.isEqual(a)) && (x.isBefore(b) || x.isEqual(b))) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean conflictoClase(Clase otraClase) {
        if (timeBetween(this.inicio,otraClase.getInicio(),otraClase.getFin()) || timeBetween(this.fin,otraClase.getInicio(),otraClase.getFin()) || timeBetween(otraClase.getInicio(),this.inicio,this.fin) || timeBetween(otraClase.getFin(),this.inicio,this.fin)) {
            return true;
        }
        else {
            return false;
        }
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }
}

