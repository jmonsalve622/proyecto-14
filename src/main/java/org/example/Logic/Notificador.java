package org.example.Logic;

import java.util.ArrayList;
import java.util.List;

public class Notificador {
    private List<Observador> observadores = new ArrayList<>();
    
    public void agregarObservador(Observador o) {
        observadores.add(o);
    }

    public void eliminarObservador(Observador o) {
        observadores.remove(o);
    }

    public void notificarObservadores(int estudianteId, int tutorId, Clase clase, ClaseAccion claseAccion) {
        for (Observador o : observadores) {
            o.update(estudianteId, tutorId, clase, claseAccion);
        }
    }
}
