package mx.grupogarcia.soportewear;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


/**
 * Created by Luis García on 03/01/2017.
 */

public class NotificationService extends FirebaseMessagingService {
    private static final String TAG="NOTIFICACION_FIREBASE";
    private static final int REQUEST_CODE=0;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG,remoteMessage.getNotification().getTitle());
        notificacion(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());


    }

    private void notificacion(String mensaje,String titulo){
        Intent i=new Intent(this,MainActivity.class);
        i.putExtra("NOTIFICACION",titulo);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,REQUEST_CODE,i,PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notBuilder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.cat_footprint_48)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText(mensaje)
                .setSound(defaultSound)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,notBuilder.build());

    }
}
