package org.example.Logic;

import java.util.ArrayList;

public class Calendario {
    private ArrayList<Clase> calend = new ArrayList<>();

    public Calendario() {

    }

    public void agregarClase(Clase clase) throws ConflicoDeHorarioException {
        for (int i = 0; i < calend.size(); i++) {
            if (clase.conflictoClase(calend.get(i))) {
                throw new ConflicoDeHorarioException();
            }
        }
        calend.add(clase);
    }

}
