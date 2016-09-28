package com.example.alfa.alfacare;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService2 extends IntentService {

    public MyIntentService2() {
        super("MyIntentService2");
    }
    public boolean refresh = false;
    private OkHttpClient cli=new OkHttpClient();
    @Override
    protected void onHandleIntent(Intent workintent) {
            SinEstoNoFunca();
            Usuario devolver = new Usuario();
            OkHttpClient client = new OkHttpClient();
            String url ="http://alfacare.esy.es/DB/NotificacionMensaje.php";
            JSONObject json = new JSONObject();
            try {
                json.put("cont", chat.cont);
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Response response = client.newCall(request).execute();
                String JSONstr = response.body().string();
                try {
                    JSONObject JSON = new JSONObject(JSONstr);
                    refresh = JSON.getBoolean("cambios");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                //e.printStackTrace();
                Log.e("IOIOIOIO", "ERROR", e);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(!refresh)
            {
                Notification n  = null;
                Intent intent = new Intent(getApplicationContext(), verChats.class);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                            R.drawable.logo2);
                    PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent, 0);
                    n = new Notification.Builder(getApplicationContext())
                            .setContentTitle("Nuevo Mensaje")
                            .setContentText("Ver Mensaje")
                            .setSmallIcon(R.drawable.logo2)
                            .setContentIntent(pIntent)
                            .setAutoCancel(true)
                            .setLargeIcon(icon)
                            //.addAction(R.drawable.logoproyecto, "Call", pIntent)

                            .build();

                }


                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                notificationManager.notify(0, n);
                //Toast.makeText(getApplicationContext(), "cambio", Toast.LENGTH_SHORT).show();
            }
        }
    private void SinEstoNoFunca()
    {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
}
