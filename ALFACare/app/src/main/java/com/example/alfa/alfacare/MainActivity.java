package com.example.alfa.alfacare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnTurno;
    Button btnPaciente;
    Button btnListarTurno;
    Button btnUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTurno = (Button)findViewById(R.id.btnTurnos);
        btnPaciente = (Button)findViewById(R.id.btnPaciente);
        btnListarTurno = (Button)findViewById(R.id.btnListarTurno2);
        btnUsuarios = (Button) findViewById(R.id.btnInicioSesion);
        btnTurno.setOnClickListener(btnTurno_Click);
        btnPaciente.setOnClickListener(btnPaciente_Click);
        btnListarTurno.setOnClickListener(btnListarTurno_Click);
        btnUsuarios.setOnClickListener(btnUsuarios_Click);
    }
    private View.OnClickListener btnTurno_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent elintent = new Intent(MainActivity.this, turno.class);
            startActivity(elintent);
        }
    };
    private View.OnClickListener btnPaciente_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent elintent = new Intent(MainActivity.this, registrarPaciente.class);
            startActivity(elintent);
        }
    };
    private View.OnClickListener btnListarTurno_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent elintent = new Intent(MainActivity.this, verTurnos.class);
            startActivity(elintent);
        }
    };
    private View.OnClickListener btnUsuarios_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent elintent = new Intent(MainActivity.this, iniciarSesion.class);
            startActivity(elintent);
        }
    };
}
