package mx.grupogarcia.soportewear.rest.model;

import mx.grupogarcia.soportewear.models.Mascota;

import java.util.ArrayList;

/**
 * Created by Luis García on 03/01/2017.
 */

public class MascotaResponse {
    private ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
