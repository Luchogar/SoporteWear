package mx.grupogarcia.soportewear.fragments;

import mx.grupogarcia.adapters.MascotaAdapter;
import mx.grupogarcia.models.Mascota;

import java.util.ArrayList;

/**
 * Created by Luis García on 03/01/2017.
 */

public interface IListaMascotasFragment {
    public void generateVerticalLayout();
    public MascotaAdapter createAdapter(ArrayList<Mascota> mascotas);
    public void initAdapter(MascotaAdapter adapter);
}
