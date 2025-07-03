package org.example.Logic;

public class Estudiante extends Perfil implements Observador {
    public Estudiante(String nombre, String correo, int id) {
        super(nombre, correo, id);
    }

    public void crearReserva() {
        
    }

    @Override
    public void update(int estudianteId, int tutorId, Clase clase, ClaseAccion claseAccion) {
        if (estudianteId == this.id) {
            if (claseAccion == ClaseAccion.AGREGAR) {
                try {
                    this.calend.agregarClase(clase);
                }
                catch (ConflicoDeHorarioException e) {
                    System.out.println("Conflicto de horario");
                }
            }
            else if (claseAccion == ClaseAccion.CANCELAR) {
                for (Clase c : this.calend.getListaClases()) {
                    if (c.equals(clase)) {
                        this.calend.getListaClases().remove(clase);
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return getNombre();
    }
}
