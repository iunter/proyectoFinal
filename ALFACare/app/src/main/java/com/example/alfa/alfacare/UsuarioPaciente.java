package com.example.alfa.alfacare;

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
 * Created by 41470398 on 6/7/2016.
 */
public class UsuarioPaciente {
    public int idusuario;
    public int idpaciente;

    private void SinEstoNoFunca()
    {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
    public void NuevoUsuarioPaciente()
    {
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/NuevoUsuarioPaciente.php";
        JSONObject json = new JSONObject();
        try{
            json.put("idusuario", idusuario);
            json.put("idpaciente", idpaciente);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
        } catch (JSONException e) {
            //e.printStackTrace();
            Log.e("JOTASON", "ERROR", e);
        } catch (IOException e) {
            //e.printStackTrace();
            Log.e("IOIOIOIO", "ERROR", e);
        }
    }
    public boolean Borrar() {
        SinEstoNoFunca();
        OkHttpClient client = new OkHttpClient();
        String url = "http://alfacare.esy.es/DB/BorrarUsuarioPaciente.php";
        JSONObject json = new JSONObject();
        try {
            json.put("idusuario", idusuario);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
