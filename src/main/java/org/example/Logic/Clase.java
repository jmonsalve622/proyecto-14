package org.example.Logic;

import java.time.Duration;

public class Clase {
    private String materia;
    private Tutor tutor;
    private Estudiante estudiante;
    private Horario horario;
    private Duration duracion;
    private int valor;

    public Clase(String materia, Tutor tutor, Estudiante estudiante, Horario horario) {
        this.materia = materia;
        this.tutor = tutor;
        this.estudiante = estudiante;
        this.horario = horario;
        duracion = Duration.between(horario.getHoraInicio(), horario.getHoraFin());
        valor = (int) duracion.toHours() * tutor.getTarifa();
    }

    public String getMateria() {
        return materia;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Horario getHorario() {
        return horario;
    }

    public Duration getDuracion() {
        return duracion;
    }

    public int getValor() {
        return valor;
    }
}
