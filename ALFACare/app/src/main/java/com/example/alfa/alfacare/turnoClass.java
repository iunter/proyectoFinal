package com.example.alfa.alfacare;

import android.os.AsyncTask;
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
import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 41470398 on 22/6/2016.
 */
public class turnoClass implements Serializable{
    public int idturno;
    public String Fecha;
    public String Hora;
    public int idpaciente;
    public int idcentro;
    public String medicoNombre;
    public String medicoApellido;
    public int idmedico;

    public turnoClass(int pId, String pFecha, String pHora, int pIdpaciente, int pIdcentro)
    {
        idturno = pId;
        Fecha = pFecha;
        Hora = pHora;
        idpaciente = pIdpaciente;
        idcentro = pIdcentro;
    }
    public turnoClass ()
    {

    }
    public turnoClass Modificar() {
        SinEstoNoFunca();
        turnoClass devolver = new turnoClass();
        OkHttpClient client = new OkHttpClient();
        String url = "http://alfacare.esy.es/DB/ModificarTurno.php";
        JSONObject json = new JSONObject();
        try {
            json.put("idturno", idturno);
            json.put("Fecha", Fecha);
            json.put("Hora", Hora);
            json.put("idpaciente", idpaciente);
            json.put("idcentro", idcentro);
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
    public boolean Borrar() {
        SinEstoNoFunca();
        OkHttpClient client = new OkHttpClient();
        String url = "http://alfacare.esy.es/DB/BorrarTurno.php";
        JSONObject json = new JSONObject();
        try {
            json.put("idturno", idturno);
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

    public turnoClass TraerUno()
    {
        SinEstoNoFunca();
        turnoClass devolver = new turnoClass();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/TraerUnTurno.php";
        JSONObject json = new JSONObject();
        try {
            json.put("idturno", idturno);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            String JSONstr = response.body().string();
            try {
                JSONObject JSON = new JSONObject(JSONstr);
                devolver.idturno = JSON.getInt("Id");
                devolver.Fecha =  JSON.getString("Fecha");
                devolver.Hora = JSON.getString("Hora");
                devolver.idpaciente = JSON.getInt("idpaciente");
                devolver.idcentro = JSON.getInt("idcentro");
                devolver.medicoNombre = JSON.getString("medicoNombre");
                devolver.medicoApellido = JSON.getString("medicoApellido");
                devolver.idmedico = JSON.getInt("idmedico");

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
    private ArrayList<turnoClass> ParsearResultado(String JSONstr)
    {

        turnoClass devolver;
        ArrayList<turnoClass> lista = new ArrayList<turnoClass>();
        try {
            JSONArray JsonTurnos = new JSONArray(JSONstr);
            for (int i = 0; i < JsonTurnos.length(); i++)
            {
                devolver = new turnoClass();
                JSONObject JsonTurno = JsonTurnos.getJSONObject(i);
                devolver.idturno= JsonTurno.getInt("Id");
                devolver.Fecha = JsonTurno.getString("Fecha");
                devolver.Hora = JsonTurno.getString("Hora");
                devolver.idpaciente = JsonTurno.getInt("idpaciente");
                devolver.idcentro = JsonTurno.getInt("idcentro");
                devolver.medicoNombre = JsonTurno.getString("medicoNombre");
                devolver.medicoApellido = JsonTurno.getString("medicoApellido");
                lista.add(devolver);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public ArrayList<turnoClass> TraerTurnos()
    {
        SinEstoNoFunca();
        ArrayList<turnoClass> devolver = new ArrayList<turnoClass>();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/TraerTurno.php";
        JSONObject json = new JSONObject();

        try {
            json.put("idpaciente", idpaciente);

            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
            Request request = new Request.Builder()
                    .post(body)
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
    private void adsda()
    {
        new TurnoTask().execute("s");
    }
    public class TurnoTask extends AsyncTask<String,Void,ArrayList<turnoClass>>
    {
        @Override
        protected ArrayList<turnoClass> doInBackground(String... params) {
            String url = params[0];
            SinEstoNoFunca();
            ArrayList<turnoClass> devolver = new ArrayList<turnoClass>();
            OkHttpClient client = new OkHttpClient();
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

        ArrayList<turnoClass> ParsearResultado(String JSONstr) throws JSONException
        {

            turnoClass devolver;
            ArrayList<turnoClass> lista = new ArrayList<turnoClass>();
            try {
                JSONArray JsonTurnos = new JSONArray(JSONstr);
                for (int i = 0; i < JsonTurnos.length(); i++)
                {
                    devolver = new turnoClass();
                    JSONObject JsonTurno = JsonTurnos.getJSONObject(i);
                    devolver.idturno= JsonTurno.getInt("Id");
                    devolver.Fecha = JsonTurno.getString("Fecha");
                    devolver.Hora = JsonTurno.getString("Hora");
                    devolver.idpaciente = JsonTurno.getInt("idpaciente");
                    devolver.idcentro = JsonTurno.getInt("idcentro");
                    devolver.medicoNombre = JsonTurno.getString("medicoNombre");
                    devolver.medicoApellido = JsonTurno.getString("medicoApellido");
                    lista.add(devolver);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return lista;

        }
    }

}
