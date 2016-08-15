package com.example.alfa.alfacare;

import android.os.StrictMode;

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
 * Created by musi on 14/08/2016.
 */
public class Medico {
    public int idmedico;
    public String nombre;
    public String apellido;
    public int idcentro;
    public String centro;

    private ArrayList<Medico> ParsearResultado(String JSONstr)
    {

        Medico devolver;
        ArrayList<Medico> lista = new ArrayList<Medico>();
        try {
            JSONArray JsonTurnos = new JSONArray(JSONstr);
            for (int i = 0; i < JsonTurnos.length(); i++)
            {
                devolver = new Medico();
                JSONObject JsonTurno = JsonTurnos.getJSONObject(i);
                devolver.idmedico= JsonTurno.getInt("idmedico");
                devolver.nombre = JsonTurno.getString("nombre");
                devolver.apellido = JsonTurno.getString("apellido");
                devolver.idcentro = JsonTurno.getInt("idcentro");
                lista.add(devolver);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public ArrayList<Medico> TraerMedico()
    {
        SinEstoNoFunca();
        ArrayList<Medico> devolver = new ArrayList<Medico>();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/TraerMedico.php";

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
