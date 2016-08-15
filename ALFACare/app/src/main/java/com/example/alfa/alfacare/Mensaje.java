package com.example.alfa.alfacare;

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
 * Created by 41470398 on 6/7/2016.
 */
public class Mensaje {
    public int idmensaje;
    public String mensaje;
    public int idchat;
    public int idusuario;
    public String usuario;
    private ArrayList<Mensaje> ParsearResultado(String JSONstr)
    {

        Mensaje devolver;
        ArrayList<Mensaje> lista = new ArrayList<Mensaje>();
        try {
            JSONArray JsonTurnos = new JSONArray(JSONstr);
            for (int i = 0; i < JsonTurnos.length(); i++)
            {
                devolver = new Mensaje();
                JSONObject JsonTurno = JsonTurnos.getJSONObject(i);
                devolver.idchat= JsonTurno.getInt("idchat");
                devolver.idmensaje = JsonTurno.getInt("idmensaje");
                devolver.idusuario = JsonTurno.getInt("idusuario");
                devolver.mensaje = JsonTurno.getString("mensaje");
                devolver.usuario = JsonTurno.getString("usuario");
                lista.add(devolver);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public ArrayList<Mensaje> TraerMensaje()
    {
        SinEstoNoFunca();
        ArrayList<Mensaje> devolver = new ArrayList<Mensaje>();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/TraerMensajes.php";
        JSONObject json = new JSONObject();

        try {
            json.put("idchat", idchat);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = null;
            response = client.newCall(request).execute();
            String JSONstr = response.body().string();
            devolver = ParsearResultado(JSONstr);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error", e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e("Error", e.getMessage());
        }
        return devolver;
    }
    public void NuevoMensaje()
    {
        SinEstoNoFunca();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/NuevoMensaje.php";
        JSONObject json = new JSONObject();
        try{
            json.put("mensaje", mensaje);
            json.put("idchat", idchat);
            json.put("idusuario", idusuario);
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
    //SINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCA
    private void SinEstoNoFunca()
    {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
}
