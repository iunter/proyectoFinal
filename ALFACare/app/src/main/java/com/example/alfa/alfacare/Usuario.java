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
 * Created by 41470398 on 5/7/2016.
 */
public class Usuario {
    public int idUsuario;
    public String usuario;
    public String nombre;
    public String apellido;
    public int dni;
    public String foto;
    public String matricula;
    public int idTipo;
    public int idClave;
    public String clave;
    public int idpaciente;
    public String tipo;
    ///////////////////////////////////////////////////////////
    public Usuario TraerUno()
    {
        SinEstoNoFunca();
        Usuario devolver = new Usuario();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/TraerUnUsuario.php";
        JSONObject json = new JSONObject();
        try {
            json.put("usuario", usuario);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            String JSONstr = response.body().string();
            try {
                JSONObject JSON = new JSONObject(JSONstr);
                devolver.idUsuario = JSON.getInt("idusuario");
                devolver.usuario = JSON.getString("usuario");
                devolver.nombre =  JSON.getString("nombre");
                devolver.apellido = JSON.getString("apellido");
                devolver.idpaciente = JSON.getInt("idpaciente");
                devolver.dni = JSON.getInt("dni");
                devolver.foto = JSON.getString("foto");
                devolver.matricula = JSON.getString("matricula");
                devolver.idTipo = JSON.getInt("idtipo");
                devolver.clave = JSON.getString("clave");

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
    public Usuario TraerUnoDos()
    {
        SinEstoNoFunca();
        Usuario devolver = new Usuario();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/TraerUnUsuarioDos.php";
        JSONObject json = new JSONObject();
        try {
            json.put("usuario", usuario);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            String JSONstr = response.body().string();
            try {
                JSONObject JSON = new JSONObject(JSONstr);
                devolver.idUsuario = JSON.getInt("idusuario");
                devolver.usuario = JSON.getString("usuario");
                devolver.nombre =  JSON.getString("nombre");
                devolver.apellido = JSON.getString("apellido");
                devolver.dni = JSON.getInt("dni");
                devolver.foto = JSON.getString("foto");
                devolver.matricula = JSON.getString("matricula");
                devolver.idTipo = JSON.getInt("idtipo");

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
    public void NuevoUsuario()
    {
        SinEstoNoFunca();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/NuevoUsuario.php";
        JSONObject json = new JSONObject();
        try{
            json.put("usuario", usuario);
            json.put("nombre", nombre);
            json.put("apellido", apellido);
            json.put("dni", dni);
            json.put("foto", foto);
            json.put("matricula", matricula);
            json.put("idtipo", idTipo);
            json.put("idclave", 1);
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

    private ArrayList<Usuario> ParsearResultado(String JSONstr)
    {

        Usuario devolver;
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        try {
            JSONArray JsonTurnos = new JSONArray(JSONstr);
            for (int i = 0; i < JsonTurnos.length(); i++)
            {
                devolver = new Usuario();
                JSONObject JsonTurno = JsonTurnos.getJSONObject(i);
                devolver.idUsuario= JsonTurno.getInt("idusuario");
                devolver.usuario = JsonTurno.getString("usuario");
                devolver.dni = JsonTurno.getInt("dni");
                devolver.nombre = JsonTurno.getString("nombre");
                devolver.foto = JsonTurno.getString("foto");
                devolver.matricula = JsonTurno.getString("matricula");
                devolver.idTipo = JsonTurno.getInt("idtipo");
                devolver.idpaciente = JsonTurno.getInt("idpaciente");
                devolver.tipo = JsonTurno.getString("tipo");
                lista.add(devolver);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public ArrayList<Usuario> TraerUsuariosPaciente(int paciente)
    {
        SinEstoNoFunca();
        ArrayList<Usuario> devolver = new ArrayList<Usuario>();
        OkHttpClient client = new OkHttpClient();
        String url ="http://alfacare.esy.es/DB/TraerUsuariosPaciente.php";
        JSONObject json = new JSONObject();

        try {
            json.put("paciente", paciente);
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
    public boolean Borrar() {
        SinEstoNoFunca();
        OkHttpClient client = new OkHttpClient();
        String url = "http://alfacare.esy.es/DB/BorrarUsuario.php";
        JSONObject json = new JSONObject();
        try {
            json.put("idusuario", idUsuario);
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
    //////////////////////////////////////////////////////////////////////////////////////
    private void SinEstoNoFunca()
    {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
}
