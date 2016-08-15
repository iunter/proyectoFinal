package com.example.alfa.alfacare;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class seleccionHora extends AppCompatActivity {
    TimePicker tmpTime;
    Button btnEnviar;
    int dia;
    int mes;
    int year;
    int hora;
    int min;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_hora);
        Intent elintent = getIntent();
        Bundle bundle = elintent.getExtras();
        dia = bundle.getInt(turno.diaa);
        mes = bundle.getInt(turno.mess);
        year = bundle.getInt(turno.yearr);
        tmpTime = (TimePicker) findViewById(R.id.timePicker);
        btnEnviar = (Button) findViewById(R.id.button2);
        btnEnviar.setOnClickListener(btnEnviar_Click);
    }
    private View.OnClickListener btnEnviar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hora = tmpTime.getCurrentHour();
            min = tmpTime.getCurrentMinute();
            Date date = new GregorianCalendar(year,mes,dia).getTime();
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formatedDate = newFormat.format(date);
            Time time = new Time(hora,min,0);
            String tiempo = time.toString();
            Toast msg =  Toast.makeText(getApplicationContext(), formatedDate + "\n" + tiempo/*String.valueOf(hora) + " " + String.valueOf(min)*/, Toast.LENGTH_SHORT);
            msg.show();
        }
    };
}
