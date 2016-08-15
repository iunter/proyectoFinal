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
 * Created by musi on 14/08/2016.
 */
public class Centro {
    public int idcentro;
    public String nombre;

    private ArrayList<Centro> ParsearResultado(String JSONstr)
    {

        Centro devolver;
        ArrayList<Centro> lista = new ArrayList<Centro>();
        try {
            JSONArray JsonTurnos = new JSONArray(JSONstr);
            for (int i = 0; i < JsonTurnos.length(); i++)
            {
                devolver = new Centro();
                JSONObject JsonTurno = JsonTurnos.getJSONObject(i);
                devolver.idcentro = JsonTurno.getInt("idcentro");
                devolver.nombre = JsonTurno.getString("nombre");
                lista.add(devolver);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public ArrayList<Centro> TraerCentro()
    {
        SinEstoNoFunca();
        ArrayList<Centro> devolver = new ArrayList<Centro>();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/TraerCentro.php";

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = null;
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
    //SINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCA
    private void SinEstoNoFunca()
    {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
}
