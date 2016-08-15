package com.example.alfa.alfacare;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class registrarPaciente extends AppCompatActivity {
    EditText edtNombre; EditText edtApellido; EditText edtDni; EditText edtObrasocial; EditText edtNSocio; Button btnEnviar;
    Adapter adapter;
    Obrasocial obra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_paciente);
        ObtenerReferencias();
        btnEnviar.setOnClickListener(btnEnviar_Click);
    }
    private View.OnClickListener btnEnviar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (edtNombre.length()>0) {
                if (edtApellido.length() > 0) {
                    if (edtDni.length() > 0) {
                        if (EsUnInteger(edtDni.getText().toString())) {
                            if (edtObrasocial.length() > 0) {
                                if (edtNSocio.length() > 0) {
                                    if (EsUnInteger(edtNSocio.getText().toString())) {
                                        if (android.os.Build.VERSION.SDK_INT > 9) {
                                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                            StrictMode.setThreadPolicy(policy);
                                        }
                                        OkHttpClient client = new OkHttpClient();
                                        String url ="http://alfacare.esy.es/DB/AgregarPaciente.php";

                                        JSONObject json = new JSONObject();
                                        try {
                                            json.put("nombre", edtNombre.getText().toString());
                                            json.put("apellido", edtApellido.getText().toString());
                                            json.put("dni", edtDni.getText().toString());
                                            json.put("obrasocial", edtObrasocial.getText().toString());
                                            json.put("socio", edtNSocio.getText().toString());
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

                                        Toast msg = Toast.makeText(getApplicationContext(), "Paciente Registrado", Toast.LENGTH_LONG);
                                        msg.show();
                                    }
                                    else
                                    {
                                        Toast msg = Toast.makeText(getApplicationContext(), "Ingrese Un nº de socio numero", Toast.LENGTH_LONG);
                                        msg.show();
                                    }

                                }
                                else
                                {
                                    Toast msg = Toast.makeText(getApplicationContext(), "Ingrese un numero de socio", Toast.LENGTH_LONG);
                                    msg.show();
                                }
                            }
                            else
                            {
                                Toast msg = Toast.makeText(getApplicationContext(), "Ingrese una obra social", Toast.LENGTH_LONG);
                                msg.show();
                            }
                        }
                        else
                        {
                            Toast msg = Toast.makeText(getApplicationContext(), "Ingrese un DNI numero", Toast.LENGTH_LONG);
                            msg.show();
                        }
                    }
                    else
                    {
                        Toast msg = Toast.makeText(getApplicationContext(), "Ingrese un DNI", Toast.LENGTH_LONG);
                        msg.show();
                    }
                }
                else {
                    Toast msg = Toast.makeText(getApplicationContext(), "Ingrese un Apellido", Toast.LENGTH_LONG);
                    msg.show();
                }
            }
            else
            {
                Toast msg = Toast.makeText(getApplicationContext(), "Ingrese un Nombre", Toast.LENGTH_LONG);
                msg.show();
            }
            /*Intent elintent = new Intent(registrarPaciente.this, turno.class);
            startActivity(elintent);*/
        }
    };
    private void ObtenerReferencias(){
        edtNombre = (EditText)findViewById(R.id.editText);
        edtApellido = (EditText)findViewById(R.id.editText2);
        edtDni = (EditText)findViewById(R.id.editText3);
        edtObrasocial = (EditText)findViewById(R.id.editText4);
        edtNSocio = (EditText)findViewById(R.id.editText5);
        btnEnviar = (Button)findViewById(R.id.button);
    }
    private boolean EsUnInteger(String strValor){
        try{
            Integer.parseInt(strValor);
        }
        catch (NumberFormatException e){
            return  false;
        }
        catch (NullPointerException e){
            return false;
        }
        return true;
    }
    public class Adapter extends BaseAdapter {
        private ArrayList<Obrasocial> Lista;
        private Context miContext;

        public Adapter(ArrayList<Obrasocial> lst, Context context) {
            miContext = context;
            Lista = lst;
        }

        public int getCount() {
            return Lista.size();
        }

        public Obrasocial getItem(int index) {
            Obrasocial devolver;
            devolver = Lista.get(index);
            return devolver;
        }

        public long getItemId(int index) {
            return Lista.get(index).idobrasocial;
        }

        public View getView(int index, View vista, ViewGroup grupo) {
            View vistaDevolver;
            LayoutInflater inflater;
            inflater = (LayoutInflater) miContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vistaDevolver = inflater.inflate(R.layout.celda, grupo, false);
            TextView txtAlgo;
            txtAlgo = (TextView) vistaDevolver.findViewById(R.id.txtCelda);
            txtAlgo.setText(getItem(index).nombre);
            return vistaDevolver;
        }
    }
}
