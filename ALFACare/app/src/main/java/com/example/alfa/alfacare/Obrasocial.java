package com.example.alfa.alfacare;

import android.os.StrictMode;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 41470398 on 6/7/2016.
 */
public class Obrasocial {
    public int idobrasocial;
    public String nombre;
    private void SinEstoNoFunca()
    {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
    private ArrayList<Obrasocial> ParsearResultado(String JSONstr)
    {

        Obrasocial devolver;
        ArrayList<Obrasocial> lista = new ArrayList<Obrasocial>();
        try {
            JSONArray JsonTurnos = new JSONArray(JSONstr);
            for (int i = 0; i < JsonTurnos.length(); i++)
            {
                devolver = new Obrasocial();
                JSONObject JsonTurno = JsonTurnos.getJSONObject(i);
                devolver.idobrasocial= JsonTurno.getInt("idobrasocial");
                devolver.nombre = JsonTurno.getString("nombre");
                lista.add(devolver);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public ArrayList<Obrasocial> TraerObrasocial()
    {
        SinEstoNoFunca();
        ArrayList<Obrasocial> devolver = new ArrayList<Obrasocial>();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/TraerObrasocial.php";
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String JSONstr = response.body().string();
            devolver = ParsearResultado(JSONstr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return devolver;
    }
}
