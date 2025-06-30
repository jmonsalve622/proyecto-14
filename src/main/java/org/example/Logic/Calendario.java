package org.example.Logic;

import java.util.ArrayList;

public class Calendario {
    private ArrayList<Clase> listaClases = new ArrayList<>();

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

}
