package org.example.Logic;

public class ConflicoDeHorarioException extends RuntimeException {
    public ConflicoDeHorarioException() {
        super("Ya existe una reserva dentro del horario deseado");
    }
}