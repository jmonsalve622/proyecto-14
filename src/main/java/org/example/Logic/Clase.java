package org.example.Logic;

public class Clase {
    private String materia;
    private Tutor tutor;
    private Estudiante estudiante;
    private Horario horario;

    public Clase(String materia, Tutor tutor, Estudiante estudiante, Horario horario) {
        this.materia = materia;
        this.tutor = tutor;
        this.estudiante = estudiante;
        this.horario = horario;
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
}
