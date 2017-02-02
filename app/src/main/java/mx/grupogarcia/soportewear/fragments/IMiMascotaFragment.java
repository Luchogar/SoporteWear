package mx.grupogarcia.soportewear.fragments;

import mx.grupogarcia.soportewear.adapters.MiMascotaAdapter;
import mx.grupogarcia.soportewear.models.Foto;
import mx.grupogarcia.soportewear.models.Mascota;

import java.util.ArrayList;

/**
 * Created by Luis García on 03/01/2017.
 */

public interface IMiMascotaFragment {
    public void generateGridLayout();
    MiMascotaAdapter createAdapter(ArrayList<Foto> fotos, Mascota m);

    public void initAdapter(MiMascotaAdapter adapter);
}
