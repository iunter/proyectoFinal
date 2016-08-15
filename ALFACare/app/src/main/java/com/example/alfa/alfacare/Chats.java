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
public class Chats {
    public int idchat;
    public int idusuario1;
    public int idusuario2;
    public String usuario1;
    public String usuario2;
    private ArrayList<Chats> ParsearResultado(String JSONstr)
    {

        Chats devolver;
        ArrayList<Chats> lista = new ArrayList<Chats>();
        try {
            JSONArray JsonTurnos = new JSONArray(JSONstr);
            for (int i = 0; i < JsonTurnos.length(); i++)
            {
                devolver = new Chats();
                JSONObject JsonTurno = JsonTurnos.getJSONObject(i);
                devolver.idchat= JsonTurno.getInt("idchat");
                devolver.idusuario1 = JsonTurno.getInt("idusuario1");
                devolver.idusuario2 = JsonTurno.getInt("idusuario2");
                devolver.usuario1 = JsonTurno.getString("usuario1");
                devolver.usuario2 = JsonTurno.getString("usuario2");
                lista.add(devolver);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public ArrayList<Chats> TraerChats(int usuario)
    {
        SinEstoNoFunca();
        ArrayList<Chats> devolver = new ArrayList<Chats>();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/TraerChats.php";
        JSONObject json = new JSONObject();

        try {
            json.put("usuario", usuario);
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

    //SINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCASINESTONOFUNCA
    private void SinEstoNoFunca()
    {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
}
