package org.example.Logic;

import java.util.ArrayList;

public class Calendario {
    private ArrayList<Clase> listaClases = new ArrayList<>();

    public Calendario() {

    }

    public void agregarClase(Clase clase) throws ConflicoDeHorarioException {
        for (Clase value : listaClases) {
            if (clase.getHorario().conflictoTiempo(value.getHorario())) {
                throw new ConflicoDeHorarioException();
            }
        }
        listaClases.add(clase);
    }

}
