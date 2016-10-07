package com.example.alfa.alfacare;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class PublicacionService extends IntentService {
    public PublicacionService(){
        super("MyIntentService");
    }
    private OkHttpClient cli=new OkHttpClient();
    @Override
    protected void onHandleIntent(Intent myintent) {
        SinEstoNoFunca();
        ArrayList<PubliNoti> lista = new ArrayList<PubliNoti>();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/NotificacionPublicacion1.php";
        JSONObject json = new JSONObject();
        try {
            json.put("usuario", Main2Activity.idUsuario);
            json.put("paciente", Main2Activity.idPaciente);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            String JSONstr = response.body().string();
            lista = ParsearResultado(JSONstr);
        } catch (IOException e) {
            //e.printStackTrace();
            Log.e("IOIOIOIO", "ERROR", e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(lista.size() > 0)
        {
            int acum = 0;
            for(int i = 0; i< lista.size(); i++)
            {
                acum += lista.get(i).cont;
            }
            Notification n  = null;
            Intent intent = new Intent(getApplicationContext(), muroActivity.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                        R.drawable.logo2);
                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent, 0);
                n = new Notification.Builder(getApplicationContext())
                        .setContentTitle("Hay " + acum + " nuevas publicaciones de " + String.valueOf(lista.size()) + " usuarios")
                        .setContentText("Ver publicaciones")
                        .setSmallIcon(R.drawable.logo2)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true)
                        .setLargeIcon(icon)
                        .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })

                        //.addAction(R.drawable.logoproyecto, "Call", pIntent)

                        .build();

            }


            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            notificationManager.notify(0, n);
            client = new OkHttpClient();
            url ="http://alfacare.esy.es/DB/NotificacionPublicacion2.php";
            JSONArray jsonArray = new JSONArray();

            try {
                for (int i = 0; i < lista.size(); i++)
                {
                    json = new JSONObject();
                    json.put("idpaciente",Main2Activity.idPaciente);
                    json.put("idusuario", Main2Activity.idUsuario);
                    jsonArray.put(i,json);
                }
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonArray.toString());
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Response response = client.newCall(request).execute();
            } catch (IOException e) {
                //e.printStackTrace();
                Log.e("IOIOIOIO", "ERROR", e);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private ArrayList<PubliNoti> ParsearResultado(String JSONstr)
    {

        PubliNoti devolver;
        ArrayList<PubliNoti> lista = new ArrayList<PubliNoti>();
        try {
            JSONArray JsonTurnos = new JSONArray(JSONstr);
            for (int i = 0; i < JsonTurnos.length(); i++)
            {
                devolver = new PubliNoti();
                JSONObject JsonTurno = JsonTurnos.getJSONObject(i);
                devolver.idusuario= JsonTurno.getInt("idchat");
                devolver.cont = JsonTurno.getInt("cont");
                lista.add(devolver);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
    private void SinEstoNoFunca()
    {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
}
