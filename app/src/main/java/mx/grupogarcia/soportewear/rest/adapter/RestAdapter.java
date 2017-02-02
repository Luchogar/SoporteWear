package mx.grupogarcia.soportewear.rest.adapter;

import mx.grupogarcia.soportewear.rest.ConstantsApi;
import mx.grupogarcia.soportewear.rest.Deserializador.MascotaDeserializador;
import mx.grupogarcia.soportewear.rest.Deserializador.MiMascotaDeserializador;
import mx.grupogarcia.soportewear.rest.Endpoints;
import mx.grupogarcia.soportewear.rest.model.MascotaResponse;
import mx.grupogarcia.soportewear.rest.model.MiMascotaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Luis García on 03/01/2017.
 */

public class RestAdapter {

    public Endpoints establecerConexionInstagram(Gson gson){
        Retrofit retrofit=new Retrofit.Builder()
                            .baseUrl(ConstantsApi.ROOT_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
        return retrofit.create(Endpoints.class);
    }




    public Endpoints establecerConexionInstagram(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ConstantsApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(Endpoints.class);
    }

    public Endpoints establecerConexionFirebase(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ConstantsApi.ROOT_URL_FIREBASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Endpoints.class);
    }

    public Gson constructirDeserializador(){
        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(MascotaResponse.class,new MascotaDeserializador());
        return builder.create();
    }

    public Gson constructorMiMascotaDeserializador(){
        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(MiMascotaResponse.class,new MiMascotaDeserializador());
        return  builder.create();
    }



}
