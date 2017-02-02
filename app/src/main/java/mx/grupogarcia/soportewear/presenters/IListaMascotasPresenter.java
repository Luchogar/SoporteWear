package mx.grupogarcia.soportewear.presenters;

import mx.grupogarcia.soportewear.models.Mascota;

import java.util.ArrayList;


/**
 * Created by Luis García on 03/01/2017.
 */
public interface IListaMascotasPresenter {
    public void getMascotas();
    public void showMascotas(ArrayList<Mascota> mascotas);
    public void getFavoritos();
    public void getMascotasRest();
}
