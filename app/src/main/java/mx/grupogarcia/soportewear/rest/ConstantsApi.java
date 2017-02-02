package mx.grupogarcia.soportewear.rest;

/**
 * Created by Luis García on 03/01/2017.
 */

public final class ConstantsApi {
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "4353219524.e098ce0.52a1ee8bf4a947babd4dfb2b071aae1f";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String URL_POST_LIKE="media/{media-id}/likes";
    public static final String URL_DELETE_LIKE="media/{media-id}/likes"+KEY_ACCESS_TOKEN+ACCESS_TOKEN;


    //https://api.instagram.com/v1/media/{media-id}/likes
    //Cada usuario sandbox con su id corresponden en el indice de los arreglos;
    public static final String[] USUARIOS_SANDBOX={"4377271040","4302183848","4275789769"};
    public static final String[] USUARIOS_SANDBOX_NOMBRE={"alephcoursera","rockymartinez77","tortus111"};
    public static final String GET_MEDIA_USUARIO="users/{user-id}/media/recent/"+KEY_ACCESS_TOKEN+ACCESS_TOKEN;


    public static final String ROOT_URL_FIREBASE ="https://secure-ocean-18082.herokuapp.com/" ;
    public static final String KEY_FIREBASE_USUARIO ="registrer" ;
    public static final String URL_LIKE_FIREBASE="likess/";

}
