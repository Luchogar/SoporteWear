package mx.grupogarcia.soportewear.presenters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import mx.grupogarcia.soportewear.db.ConstructorMascotas;
import mx.grupogarcia.soportewear.fragments.IMiMascotaFragment;
import mx.grupogarcia.soportewear.models.Foto;
import mx.grupogarcia.soportewear.models.Mascota;
import mx.grupogarcia.soportewear.rest.ConstantsApi;
import mx.grupogarcia.soportewear.rest.Endpoints;
import mx.grupogarcia.soportewear.rest.adapter.RestAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Luis García on 03/01/2017.
 */

public class MiMascotaPresenter implements IMiMascotaPresenter {
    private IMiMascotaFragment vista;
    private ConstructorMascotas constructorMascota;
    private Context context;
    private ArrayList<Foto> fotos;
    private Mascota mascota;

    public MiMascotaPresenter(IMiMascotaFragment vista, Context context) {
        this.vista = vista;
        this.context = context;
        constructorMascota=new ConstructorMascotas(context);
        getMiMascotaRest();
    }

    public void getMiMascotaRest(){
        RestAdapter restAdapter=new RestAdapter();
        Gson gson=restAdapter.constructorMiMascotaDeserializador();
        Endpoints endpoints=restAdapter.establecerConexionInstagram(gson);
        SharedPreferences sp=context.getSharedPreferences("Cuenta",Context.MODE_PRIVATE);
        String miUsuario= sp.getString("Usuario","No existe");
        if(miUsuario=="No existe"){
            mascota=new Mascota();}
        else{
            int aux=-1;
            for (int i = 0; i <ConstantsApi.USUARIOS_SANDBOX_NOMBRE.length ; i++) {
                if(ConstantsApi.USUARIOS_SANDBOX_NOMBRE[i].equalsIgnoreCase(miUsuario.trim()))
                    aux=i;
            }
            String miUsuarioClave="";
            if(aux>=0)
                miUsuarioClave=ConstantsApi.USUARIOS_SANDBOX[aux];

                endpoints.getMiUsuario(miUsuarioClave)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((mascota)->{
                    this.mascota=mascota.getMiMascota();
                    getFotos();
                },e-> e.printStackTrace());
        }
    }
    @Override
    public void getFotos() {
      this.fotos=mascota.getFotos();
        showFotos();
    }

    @Override
    public void showFotos() {
        Log.i("Fotos",fotos.size()+"");
        vista.initAdapter(vista.createAdapter(fotos,mascota));
        vista.generateGridLayout();
    }

    @Override
    public Mascota getMiMascota() {
        return null;
    }

    @Override
    public Mascota getPet() {
        return mascota;
    }


}
