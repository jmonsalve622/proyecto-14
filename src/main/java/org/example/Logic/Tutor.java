package org.example.Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tutor extends Perfil {
    private int tarifa;
    private int maxEst;
    private List<String> listaMaterias = new ArrayList<>();
    private List<Horario> listaDisp = new ArrayList<>();

    public Tutor(String nombre, String correo, int id, int tarifa, int maxEst) {
        super(nombre, correo, id);
        this.tarifa = tarifa;
        this.maxEst = maxEst;
        //this.listaDisp = listaDisp;    /Deshabilitado hasta saber como hacerlo funcionar
    }

    public int getTarifa() {
        return tarifa;
    }

    public int getMaxEst() {
        return maxEst;
    }

    public List<String> getListaMaterias() {
        return listaMaterias;
    }

    public List<Horario> getListaDisp() {
        return listaDisp;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public void setMaxEst(int maxEst) {
        this.maxEst = maxEst;
    }

    public void agregarMateria(String materia) {
        boolean repetido = false;
        for (String m : listaMaterias) {
            if (materia.equals(m)) {
                repetido = true;
                break;
            }
        }
        if (!repetido) {
            listaMaterias.add(materia);
        }
    }

    public void agregarHorario(Horario horario) throws ConflicoDeHorarioException {
        for (Horario h : listaDisp) {
            if (horario.conflictoTiempo(h)) {
                throw new ConflicoDeHorarioException();
            }
            listaDisp.add(horario);
        }
    }
}