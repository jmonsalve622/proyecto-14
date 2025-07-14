package org.example.Logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class PerfilTest {
    private Estudiante estudiante;

    @BeforeEach
    public void setUp() {
        estudiante = new Estudiante("Joaquin", "joaco@gmail.com", 1);
    }

    @Test
    public void testGetters() {
        assertEquals("Joaquin", estudiante.getNombre());
        assertEquals("joaco@gmail.com", estudiante.getCorreo());
        assertEquals(1, estudiante.getId());
    }

    @Test
    public void testSetters() {
        estudiante.setNombre("Cristobal");
        estudiante.setCorreo("cristobal@gmail.com");

        assertEquals("Cristobal", estudiante.getNombre());
        assertEquals("cristobal@gmail.com", estudiante.getCorreo());
    }

    @Test
    public void testAgregarHorarioSinConflicto() throws ConflicoDeHorarioException {
        Horario h1 = new Horario(DayOfWeek.MONDAY, LocalTime.of(1, 15), LocalTime.of(2, 0));
        estudiante.agregarHorario(h1);
        assertEquals(1, estudiante.getListaDisp().size());
    }

    @Test
    void testModificarNombreYCorreo() {
        estudiante.setNombre("Cristobal");
        estudiante.setCorreo("cristobal@gmail.com");
        assertEquals("Cristobal", estudiante.getNombre());
        assertEquals("cristobal@gmail.com", estudiante.getCorreo());
    }

    @Test
    void testAgregarHorarioConConflicto() throws ConflicoDeHorarioException {
        Horario h1 = new Horario(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 0));
        Horario h2 = new Horario(DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 30));
        estudiante.agregarHorario(h1);
        assertThrows(ConflicoDeHorarioException.class, () -> estudiante.agregarHorario(h2));
    }

    @Test
    public void testLimpiarHorarios() throws ConflicoDeHorarioException {
        Horario h1 = new Horario(DayOfWeek.TUESDAY, LocalTime.of(10, 15), LocalTime.of(11, 0));
        estudiante.agregarHorario(h1);
        assertEquals(1, estudiante.getListaDisp().size());

        estudiante.limpiarHorarios();
        assertTrue(estudiante.getListaDisp().isEmpty());
    }

    @Test
    public void testToString() {
        assertEquals("Joaquin", estudiante.toString());
    }
}