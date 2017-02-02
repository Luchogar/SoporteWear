package mx.grupogarcia.soportewear.rest.Deserializador;

import mx.grupogarcia.soportewear.models.Mascota;
import mx.grupogarcia.soportewear.rest.JsonKeys;
import mx.grupogarcia.soportewear.rest.model.MascotaResponse;
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

public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {


    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson=new Gson();
        MascotaResponse mascotaResponse=gson.fromJson(json,MascotaResponse.class);
        JsonArray mascotaResponseData=json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        mascotaResponse.setMascotas(obtenerDatosDeJson(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<Mascota> obtenerDatosDeJson(JsonArray mascotaResponseData) {
            ArrayList<Mascota> mascotas=new ArrayList<>();
            for (int i = 0; i <mascotaResponseData.size() ; i++) {
                JsonObject contactoResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();

                JsonObject userJson     = contactoResponseDataObject.getAsJsonObject(JsonKeys.USER);
                String id               = userJson.get(JsonKeys.USER_ID).getAsString();
                String nombreCompleto   = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

                JsonObject imageJson            = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
                JsonObject stdResolutionJson    = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
                String urlFoto                  = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();
                String fecha                    = contactoResponseDataObject.get(JsonKeys.CREATED_AT).getAsString();
                String id_foto                  = contactoResponseDataObject.get(JsonKeys.FOTO_ID).getAsString();

                JsonObject likesJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
                int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

                Mascota m=new Mascota();
                m.setId(id);
                m.setNombre(nombreCompleto);
                m.setPicture(urlFoto);
                m.setVotos(likes);
                m.setId_foto(id_foto);
                m.setFecha(fecha);
                mascotas.add(m);
            }

        return mascotas;
    }


}
