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
 * Created by musi on 05/07/2016.
 */
public class Tipo {
    public int idTipo;
    public String desc;
    public int adm;

    private ArrayList<Tipo> ParsearResultado(String JSONstr)
    {
        Tipo devolver;
        ArrayList<Tipo> lista = new ArrayList<Tipo>();
        try {
            JSONArray JsonTurnos = new JSONArray(JSONstr);
            for (int i = 0; i < JsonTurnos.length(); i++)
            {
                devolver = new Tipo();
                JSONObject JsonTurno = JsonTurnos.getJSONObject(i);
                devolver.idTipo= JsonTurno.getInt("idtipo");
                devolver.desc = JsonTurno.getString("desc");
                devolver.adm = JsonTurno.getInt("adm");
                lista.add(devolver);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public ArrayList<Tipo> TraerTipo()
    {
        SinEstoNoFunca();
        ArrayList<Tipo> devolver = new ArrayList<Tipo>();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/TraerTipos.php";
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
    private void SinEstoNoFunca()
    {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
}
