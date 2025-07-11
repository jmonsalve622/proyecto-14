package org.example.Logic;

import java.time.DayOfWeek;
import java.util.List;

public interface FiltroCalendario {
    List<Clase> filtrarCalendario(String nombrePerfil, String materia, List<DayOfWeek> dias);
}
