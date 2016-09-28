package com.example.alfa.alfacare;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by 41470398 on 14/9/2016.
 */
public class MyIntentService extends IntentService{
    public MyIntentService(){
        super("MyIntentService");
    }
    public boolean refresh;
    private OkHttpClient cli=new OkHttpClient();
    @Override
    protected void onHandleIntent(final Intent workIntent) {
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
}
