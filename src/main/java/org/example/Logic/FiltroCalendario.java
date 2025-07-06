package org.example.Logic;

import java.time.DayOfWeek;
import java.util.List;

public interface FiltroCalendario {
    List<Clase> filtrarCalendario(List<Perfil> perfiles, List<String> materias, List<DayOfWeek> dias);
}
