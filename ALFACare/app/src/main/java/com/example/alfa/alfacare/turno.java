package com.example.alfa.alfacare;

import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class turno extends AppCompatActivity {
    int dia;
    int mes;
    int year;
    int hora;
    int min;
    //alguna forma de recibir el id del paciente?
    int paciente = 2;
    TimePicker tmpTime;
    DatePicker dtpfecha;
    Button btnCambiar;
    Spinner spnCentros;
    Spinner spnMedicos;
    verUnTurno.CentroAdapter centroAdapter;
    verUnTurno.MedicoAdapter medicoAdapter;
    Centro centro = new Centro();
    Medico medico = new Medico();
    public static final String diaa = "dia";
    public static final String mess = "mes";
    public static final String yearr = "year";
    public static final String bandel = "eaaa";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turno);
        dtpfecha = (DatePicker) findViewById(R.id.datePicker);
        btnCambiar = (Button) findViewById(R.id.button);
        spnCentros = (Spinner)findViewById(R.id.spinner);
        spnMedicos = (Spinner)findViewById(R.id.spinner2);
        tmpTime = (TimePicker) findViewById(R.id.timePicker2);
        centroAdapter = new verUnTurno.CentroAdapter(centro.TraerCentro(),this);
        medicoAdapter = new verUnTurno.MedicoAdapter(medico.TraerMedico(), this);
        spnCentros.setAdapter(centroAdapter);
        spnMedicos.setAdapter(medicoAdapter);
        btnCambiar.setOnClickListener(btnEnviar_Click);
    }
    private View.OnClickListener btnEnviar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dia = dtpfecha.getDayOfMonth();
            mes = dtpfecha.getMonth();
            year = dtpfecha.getYear();
            hora = tmpTime.getCurrentHour();
            min = tmpTime.getCurrentMinute();
            int centro = centroAdapter.getItem(spnCentros.getSelectedItemPosition()).idcentro;
            int medico = medicoAdapter.getItem(spnMedicos.getSelectedItemPosition()).idmedico;
            java.util.Date date = new GregorianCalendar(year,mes,dia).getTime();
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formatedDate = newFormat.format(date);
            Time time = new Time(hora,min,0);
            String tiempo = time.toString();
            Toast msg =  Toast.makeText(getApplicationContext(), formatedDate + "\n" + tiempo + "\n" + centro + "\n" + medico, Toast.LENGTH_LONG);
            msg.show();
            //////////////////////////////////////////////////////////
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            OkHttpClient client = new OkHttpClient();
            String url ="http://alfacare.esy.es/DB/NuevoTurno.php";
            JSONObject json = new JSONObject();
            try{
                json.put("Fecha", formatedDate);
                json.put("Hora", time);
                json.put("idpaciente", paciente);
                json.put("idcentro", centro);
                json.put("idmedico",medico);
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
            turnoClass sopapa = new turnoClass();
        }
    };
}

