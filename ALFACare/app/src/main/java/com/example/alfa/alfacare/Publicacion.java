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
 * Created by musi on 15/08/2016.
 */
public class Publicacion {
    public int idpublicacion;
    public int idusuario;
    public int idpaciente;
    public String mensaje;
    public String usuario;

    private ArrayList<Publicacion> ParsearResultado(String JSONstr)
    {

        Publicacion devolver;
        ArrayList<Publicacion> lista = new ArrayList<Publicacion>();
        try {
            JSONArray JsonTurnos = new JSONArray(JSONstr);
            for (int i = 0; i < JsonTurnos.length(); i++)
            {
                devolver = new Publicacion();
                JSONObject JsonTurno = JsonTurnos.getJSONObject(i);
                devolver.idpublicacion= JsonTurno.getInt("idpublicacion");
                devolver.idusuario = JsonTurno.getInt("idusuario");
                devolver.idpaciente = JsonTurno.getInt("idpaciente");
                devolver.mensaje = JsonTurno.getString("mensaje");
                devolver.usuario = JsonTurno.getString("usuario");
                lista.add(devolver);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public ArrayList<Publicacion> TraerPublicacion()
    {
        SinEstoNoFunca();
        ArrayList<Publicacion> devolver = new ArrayList<Publicacion>();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/TraerPublicacion.php";
        JSONObject json = new JSONObject();

        try {
            json.put("idpaciente", idpaciente);
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

    public void NuevoPublicacion()
    {
        SinEstoNoFunca();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/NuevoPublicacion.php";
        JSONObject json = new JSONObject();
        try{
            json.put("idusuario", idusuario);
            json.put("idpaciente", idpaciente);
            json.put("mensaje", mensaje);
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
    //////////////////////////////////////////////////////////////////////////////////////
    private void SinEstoNoFunca()
    {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
}
