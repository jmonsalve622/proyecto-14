package org.example.Logic;
import java.util.List;

public class TutorFactory extends PeriflFactory {
    public Perfil crearPerfil(String nombre, String correo, int tarifa, int maxEst) {
        this.incId();
        return new Tutor(nombre, correo, this.id, tarifa, maxEst);
    }
}
