package org.example.Logic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class ListaPerfilesTest {

    private ListaPerfiles listaPerfiles;
    private Estudiante estudiante1;
    private Tutor tutor1;
    private Horario horario1, horario2;

    @BeforeEach
    void setUp() {
        listaPerfiles = new ListaPerfiles();

        estudiante1 = new Estudiante("Juan", "juanitopro@gmail.com", 1);
        tutor1 = new Tutor("Chris", "cristobalin@hotmail.com", 2, 1000, 2);

        tutor1.getListaMaterias().add("Matemáticas");

        horario1 = new Horario(DayOfWeek.MONDAY, LocalTime.of(8,15), LocalTime.of(9,0));
        horario2 = new Horario(DayOfWeek.MONDAY, LocalTime.of(9,15), LocalTime.of(10,0));

        tutor1.getListaDisp().add(horario1);
        tutor1.getListaDisp().add(horario2);
    }

    @Test
    void testAgregarYBuscarEstudiante() {
        listaPerfiles.agregarPerfil(estudiante1);
        Estudiante encontrado = listaPerfiles.buscarEstudiante(1);
        assertNotNull(encontrado);
        assertEquals(1, encontrado.getId());

        Estudiante noEncontrado = listaPerfiles.buscarEstudiante(2);
        assertNull(noEncontrado);
    }

    @Test
    void testAgregarYBuscarTutor() {
        listaPerfiles.agregarPerfil(tutor1);
        Tutor encontrado = listaPerfiles.buscarTutor(2);
        assertNotNull(encontrado);
        assertEquals(2, encontrado.getId());

        Tutor noEncontrado = listaPerfiles.buscarTutor(3);
        assertNull(noEncontrado);
    }

    @Test
    void testBuscarClase() {
        listaPerfiles.agregarPerfil(tutor1);

        List<Horario> horariosBuscados = new ArrayList<>();
        horariosBuscados.add(new Horario(DayOfWeek.MONDAY, LocalTime.of(8,15), LocalTime.of(9,0)));

        List<Clase> clases = listaPerfiles.buscarClase("Matemáticas", horariosBuscados);

        assertFalse(clases.isEmpty());
        Clase clase = clases.get(0);
        assertEquals("Matemáticas", clase.getMateria());
        assertEquals(tutor1, clase.getTutor());
        assertEquals(horariosBuscados.get(0), clase.getHorario());
    }
}