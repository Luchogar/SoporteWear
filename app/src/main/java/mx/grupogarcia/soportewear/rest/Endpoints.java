package mx.grupogarcia.soportewear.rest;



import mx.grupogarcia.soportewear.rest.model.LikeResponse;
import mx.grupogarcia.soportewear.rest.model.MascotaResponse;
import mx.grupogarcia.soportewear.rest.model.MiMascotaResponse;
import mx.grupogarcia.soportewear.rest.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Luis García on 03/01/2017.
 */

public interface Endpoints  {
    @GET(ConstantsApi.URL_GET_RECENT_MEDIA_USER)
    Observable<MascotaResponse> getRecentMedia();
    @GET(ConstantsApi.GET_MEDIA_USUARIO)
    Observable<MascotaResponse>getUsuario(@Path("user-id") String id);
    @GET(ConstantsApi.GET_MEDIA_USUARIO)
    Observable<MiMascotaResponse>getMiUsuario(@Path("user-id") String id);

    @FormUrlEncoded
    @POST(ConstantsApi.URL_POST_LIKE)
    Call<LikeResponse> darLike(@Field("access_token") String access, @Path("media-id") String media_id);
    @DELETE(ConstantsApi.URL_DELETE_LIKE)
    Call<LikeResponse> quitarLike(@Path("media-id") String media_id);



    @FormUrlEncoded
    @POST(ConstantsApi.KEY_FIREBASE_USUARIO)
    Call<UsuarioResponse>registrarUsuario(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram);
    @FormUrlEncoded
    @POST(ConstantsApi.URL_LIKE_FIREBASE)
    Call<UsuarioResponse> likeFirebase(@Field("id_foto") String id_foto,
                                       @Field("id_usuario_instagram") String id_usuario_instagram,
                                       @Field("id_dispositivo") String id_dispositivo,
                                       @Path("propietario") String propietario);

}
