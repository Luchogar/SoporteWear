package mx.grupogarcia.soportewear.presenters;

import android.content.Context;

import mx.grupogarcia.soportewear.db.ConstructorMascotas;
import mx.grupogarcia.soportewear.fragments.IListaMascotasFragment;
import mx.grupogarcia.soportewear.models.Mascota;
import mx.grupogarcia.soportewear.models.MascotaAux;
import mx.grupogarcia.soportewear.rest.ConstantsApi;
import mx.grupogarcia.soportewear.rest.Endpoints;
import mx.grupogarcia.soportewear.rest.adapter.RestAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/
/**
 * Created by Luis García on 03/01/2017.
 */

public class ListaMascotasPresenter implements IListaMascotasPresenter{

    private IListaMascotasFragment vista;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public ListaMascotasPresenter(IListaMascotasFragment vista, Context context) {
        this.vista = vista;
        this.context = context;
        this.constructorMascotas=new ConstructorMascotas(context);
        //getMascotas();
        mascotas=new ArrayList<>();
    }

    public void getMascotasRest(){
        RestAdapter restAdapter=new RestAdapter();
        Gson gson=restAdapter.constructirDeserializador();
        Endpoints endpoints=restAdapter.establecerConexionInstagram(gson);
        for (int i = 0; i <ConstantsApi.USUARIOS_SANDBOX.length ; i++) {
            int finalI = i;
            endpoints.getUsuario(ConstantsApi.USUARIOS_SANDBOX[i])
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((mascota)->{
                        mascotas.addAll(mascota.getMascotas());
                        Collections.sort(mascotas);
                        showMascotas(mascotas);
                    },e->e.printStackTrace());
        }
    }




    @Override
    public void getMascotas() {
        this.mascotas=constructorMascotas.getMascotas();
        showMascotas(mascotas);
    }

    @Override
    public void showMascotas(ArrayList<Mascota>  mascotas) {
        vista.initAdapter(vista.createAdapter(mascotas));
        vista.generateVerticalLayout();
    }

    @Override
    public void getFavoritos() {
        RestAdapter restAdapter=new RestAdapter();
        Gson gson=restAdapter.constructirDeserializador();
        Endpoints endpoints=restAdapter.establecerConexionInstagram(gson);
        for (int i = 0; i <ConstantsApi.USUARIOS_SANDBOX.length ; i++) {
            int finalI = i;
            endpoints.getUsuario(ConstantsApi.USUARIOS_SANDBOX[i])
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((mascota)->{
                        mascotas.addAll(mascota.getMascotas());

                        showMascotas(ordenarPorVotos());
                    },e->e.printStackTrace());
        }
    }

private ArrayList<Mascota> ordenarPorVotos(){
    ArrayList<MascotaAux> aux=new ArrayList<>();
    for (int i = 0; i < mascotas.size(); i++) {
        MascotaAux m=new MascotaAux(mascotas.get(i));
        aux.add(m);
    }
    Collections.sort(aux);
    ArrayList<Mascota> aux2=new ArrayList<>();
    for (int i = 0; i <5 ; i++) {
        aux2.add(aux.get(i));
    }
    return aux2;
}

}

