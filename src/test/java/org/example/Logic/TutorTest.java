package org.example.Logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TutorTest {

    private Tutor tutor;

    @BeforeEach
    void setUp() {
        tutor = new Tutor("Joaquin Monsalve", "Joaco@example.com", 135, 10000, 2);
    }

    @Test
    void testAgregarMateriaSinRepeticion() {
        tutor.agregarMateria("Matematicas");
        tutor.agregarMateria("matematicas");
        tutor.agregarMateria("mAtEMatIcaS");
        tutor.agregarMateria("Lenguaje");
        tutor.agregarMateria("lenguaje");
        tutor.agregarMateria("ciencia");
        assertEquals(3, tutor.getListaMaterias().size());
    }

    @Test
    void testAgregarYEliminarEstudiantes() {
        Estudiante e1 = new Estudiante("Cris", "cristo1@gmail.com", 3);
        Estudiante e2 = new Estudiante("Ignacio", "nacho2@gmail.com", 4);
        Estudiante e3 = new Estudiante("Sans", "saness3@gmail.com", 5);

        assertTrue(tutor.agregarEstudiante(e1));
        assertTrue(tutor.agregarEstudiante(e2));
        assertFalse(tutor.agregarEstudiante(e3));
    }

    @Test
    void testEstaDisponibleYGetCupos() {
        assertTrue(tutor.estaDisponible());
        assertEquals(2, tutor.getCuposRestantes());

        tutor.agregarEstudiante(new Estudiante("Cristobal", "cristo8221@gmail.com", 10));
        assertEquals(1, tutor.getCuposRestantes());
    }
}