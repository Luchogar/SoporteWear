package mx.grupogarcia.soportewear;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Luis García on 03/01/2017.
 */

public final class InstanceIdServiceFirebase extends FirebaseInstanceIdService {

    private final static String TAG="FIREBASE_PETAGRAM";

    @Override
    public void onTokenRefresh() {
        String tokenNuevo= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,tokenNuevo);
        SharedPreferences sp=getSharedPreferences("TOKEN_FIREBASE",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("TOKEN",tokenNuevo);
        editor.commit();
    }
}
