package org.example.Logic;

import java.util.ArrayList;
import java.util.List;

public class Calendario {
    private List<Clase> listaClases = new ArrayList<>();

    public Calendario() {

    }

    public void agregarClase(Clase clase) throws ConflicoDeHorarioException {
        for (Clase c : listaClases) {
            if (clase.getHorario().conflictoTiempo(c.getHorario())) {
                throw new ConflicoDeHorarioException();
            }
        }
        listaClases.add(clase);
    }

    public void cancelarClase(Clase clase) {
        for (Clase c : listaClases) {
            if (clase.equals(c)) {
                listaClases.remove(clase);
            }
        }
    }

    public List<Clase> getListaClases() {
        return listaClases;
    }
}
