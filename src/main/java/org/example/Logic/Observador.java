package org.example.Logic;

public interface Observador {
    void update(int estudianteId, int tutorId, Clase clase, ClaseAccion claseAccion);
}
