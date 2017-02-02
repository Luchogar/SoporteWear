package mx.grupogarcia.soportewear.rest.Deserializador;

import mx.grupogarcia.soportewear.models.Foto;
import mx.grupogarcia.soportewear.models.Mascota;
import mx.grupogarcia.soportewear.rest.JsonKeys;
import mx.grupogarcia.soportewear.rest.model.MiMascotaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Luis García on 03/01/2017.
 */

public class MiMascotaDeserializador implements JsonDeserializer<MiMascotaResponse> {
    @Override
    public MiMascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson=new Gson();
        MiMascotaResponse miMascotaResponse=gson.fromJson(json,MiMascotaResponse.class);
        JsonArray data=json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        Mascota miMascota=new Mascota();
        miMascota.setFotos(obtenerFotos(data));

        String nombre=data.get(0).getAsJsonObject().getAsJsonObject(JsonKeys.USER).get(JsonKeys.USER_FULLNAME).getAsString();
        String id=data.get(0).getAsJsonObject().getAsJsonObject(JsonKeys.USER).get(JsonKeys.USER_ID).getAsString();
        String urlProfile=data.get(0).getAsJsonObject().getAsJsonObject(JsonKeys.USER).get(JsonKeys.USER_PROFILE).getAsString();

        miMascota.setPicture(urlProfile);
        miMascota.setNombre(nombre);
        miMascota.setId(id);

        miMascotaResponse.setMiMascota(miMascota);
        return miMascotaResponse;
    }

    private ArrayList<Foto> obtenerFotos(JsonArray data) {
        ArrayList<Foto> fotos=new ArrayList<>();
        for (int i = 0; i <data.size() ; i++) {
            JsonObject item =data.get(i).getAsJsonObject();
            Foto auxFoto    =new Foto();

            String fecha    =item.get(JsonKeys.CREATED_AT).getAsString();
            String url      =item.getAsJsonObject(JsonKeys.MEDIA_IMAGES).getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION)
                                .get(JsonKeys.MEDIA_URL).getAsString();
            int likes       =item.getAsJsonObject(JsonKeys.MEDIA_LIKES).get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            auxFoto.setFecha(fecha);
            auxFoto.setVotos(likes);
            auxFoto.setUrl(url);
            fotos.add(auxFoto);
        }

        return fotos;
    }
}
