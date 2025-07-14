package org.example.Logic;

import java.util.ArrayList;
import java.util.List;

public abstract class Perfil {
    protected String nombre;
    protected String correo;
    protected int id;
    protected List<Clase> calendario = new ArrayList<>();
    protected List<Horario> listaDisp = new ArrayList<>();

    public Perfil(String nombre, String correo, int id) {
        this.nombre = nombre;
        this.correo = correo;
        this.id = id;
    }

    /**
     * @return devuelve el nombre del perfil
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return devuelve el correo del perfil
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @return devuelve el numerio ID del perfil
     */
    public int getId() {
        return id;
    }

    /**
     * @return devuelve su lista de horario del perfil
     */
    public List<Horario> getListaDisp() {
        return listaDisp;
    }

    /**
     * @return devuelve el calendario del perfil
     */
    public List<Clase> getCalendario() {
        return calendario;
    }

    /**
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     *
     * @param horario que se quiere agregar a listaDisp
     * @throws ConflicoDeHorarioException superposición de horario con los que ya hay en listDisp
     */
    public void agregarHorario(Horario horario) throws ConflicoDeHorarioException {
        for (Horario h : listaDisp) {
            if (horario.conflictoTiempo(h)) {
                throw new ConflicoDeHorarioException();
            }
        }
        listaDisp.add(horario);
    }

    /**
     * Vaciá la lista listaDisp
     */
    public void limpiarHorarios() {
        listaDisp.clear();
    }

    /**
     * @return devuelve el nombre del perfil
     */
    @Override
    public String toString() {
        return nombre;
    }
}
