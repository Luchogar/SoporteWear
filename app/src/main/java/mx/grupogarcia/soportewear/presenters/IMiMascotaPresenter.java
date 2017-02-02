package mx.grupogarcia.soportewear.presenters;

import mx.grupogarcia.soportewear.models.Mascota;

/**
 * Created by Luis García on 03/01/2017.
 */

public interface IMiMascotaPresenter {
    public void getFotos();
    public void showFotos();
    public Mascota getMiMascota();

    Mascota getPet();
}
