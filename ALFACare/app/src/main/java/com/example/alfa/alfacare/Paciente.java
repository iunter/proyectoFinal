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
 * Created by 41470398 on 5/7/2016.
 */
public class Paciente {
    public int idPaciente;
    public String nombre;
    public String apellido;
    public int dni;
    public int socio;
    public String foto;
    public int lat;
    public int longg;
    public int idobrasocial;
    public String obrasocial;
    /////////////////////////////////////////
    public Paciente TraerUno()
    {
        SinEstoNoFunca();
        Paciente devolver = new Paciente();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/TraerUnPaciente.php";
        JSONObject json = new JSONObject();
        try {
            json.put("idpaciente", idPaciente);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            String JSONstr = response.body().string();
            try {
                JSONObject JSON = new JSONObject(JSONstr);
                devolver.idPaciente = JSON.getInt("idpaciente");
                devolver.nombre =  JSON.getString("nombre");
                devolver.apellido = JSON.getString("apellido");
                devolver.dni = JSON.getInt("dni");
                devolver.socio = JSON.getInt("socio");
                devolver.foto = JSON.getString("foto");
                devolver.lat = JSON.getInt("lat");
                devolver.longg = JSON.getInt("longg");
                devolver.idobrasocial = JSON.getInt("idobrasocial");
                devolver.obrasocial = JSON.getString("obrasocial");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            //e.printStackTrace();
            Log.e("IOIOIOIO", "ERROR", e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return devolver;
    }
    public Paciente Modificar() {
        SinEstoNoFunca();
        Paciente devolver = new Paciente();
        OkHttpClient client = new OkHttpClient();
        String url = "http://alfacare.esy.es/DB/ModificarPaciente.php";
        JSONObject json = new JSONObject();
        try {
            json.put("idpaciente", idPaciente);
            json.put("nombre", nombre);
            json.put("apellido", apellido);
            json.put("dni", dni);
            json.put("socio", socio);
            json.put("foto", foto);
            json.put("lat", lat);
            json.put("longg", longg);
            json.put("idobrasocial", idobrasocial);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        devolver = TraerUno();
        return devolver;
    }
    private void SinEstoNoFunca()
    {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
}
