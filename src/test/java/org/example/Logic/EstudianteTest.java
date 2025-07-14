package org.example.Logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class EstudianteTest {

    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        estudiante = new Estudiante("Cristobal", "cri@gmail.com", 9);
    }

    @Test
    void testAgregarClaseSinConflicto() throws ConflicoDeHorarioException {
        Tutor tutor = new Tutor("joaco", "joacopro@gmail.com", 2, 10000000, 1);
        Horario horario = new Horario(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(10, 0));
        Clase clase = new Clase("Matematicas", tutor, estudiante, horario);
        estudiante.agregarClase(clase);
        assertEquals(1, estudiante.getCalendario().size());
    }

    @Test
    void testAgregarClaseConConflicto() throws ConflicoDeHorarioException {
        Tutor tutor = new Tutor("Cristobal", "cri777@gmail.com", 2, 1, 100);
        Horario h1 = new Horario(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(10, 0));
        Horario h2 = new Horario(DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 30));
        estudiante.agregarClase(new Clase("Ciencias", tutor, estudiante, h1));
        Clase claseConflictiva = new Clase("Ingles", tutor, estudiante, h2);

        assertThrows(ConflicoDeHorarioException.class, () -> estudiante.agregarClase(claseConflictiva));
    }
}