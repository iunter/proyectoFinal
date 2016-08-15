package com.example.alfa.alfacare;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class verUnTurno extends AppCompatActivity {
    char abm = ' ';
    int id;
    turnoClass turno = new turnoClass();
    Centro centro = new Centro();
    Medico medico = new Medico();
    MedicoAdapter medicoAdapter;
    CentroAdapter centroAdapter;
    DatePicker dtpFecha;
    TimePicker tmpHora;
    Spinner spnCentro, spnMedico;
    Button btnModificar, btnBorrar, btnAceptar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_un_turno);
        ObtenerReferencias();
        Intent elintent = getIntent();
        Bundle bundle = elintent.getExtras();
        id = bundle.getInt(verTurnos.hola);
        Refresh();
        btnModificar.setOnClickListener(btnModificar_Click);
        btnBorrar.setOnClickListener(btnBorrar_Click);
        btnCancelar.setOnClickListener(btnCancelar_Click);
        btnAceptar.setOnClickListener(btnAceptar_Click);
    }

    View.OnClickListener btnModificar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            btnAceptar.setClickable(true);
            btnCancelar.setClickable(true);
            abm = 'M';
        }
    };
    View.OnClickListener btnBorrar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            btnAceptar.setClickable(true);
            btnCancelar.setClickable(true);
            abm = 'B';
        }
    };
    View.OnClickListener btnCancelar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            btnAceptar.setClickable(false);
            btnCancelar.setClickable(false);
            abm = ' ';
        }
    };
    View.OnClickListener btnAceptar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (abm) {
                case 'M':
                    int year = dtpFecha.getYear();
                    int mes = dtpFecha.getMonth();
                    int dia = dtpFecha.getDayOfMonth();
                    int hora = tmpHora.getCurrentHour();
                    int min = tmpHora.getCurrentMinute();
                    java.util.Date date = new GregorianCalendar(year, mes, dia).getTime();
                    SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formatedDate = newFormat.format(date);
                    Time time = new Time(hora, min, 0);
                    String tiempo = time.toString();
                    turno.Fecha = formatedDate;
                    turno.Hora = tiempo;
                    turno.idcentro = 1;
                    turno = turno.Modificar();
                    Refresh();
                    Toast.makeText(verUnTurno.this, "Modificado", Toast.LENGTH_SHORT).show();
                    break;
                case 'B':
                    if (turno.Borrar()) {
                        Toast.makeText(verUnTurno.this, "Borrado", Toast.LENGTH_SHORT).show();
                        Intent elintent = new Intent(verUnTurno.this, verTurnos.class);
                        startActivity(elintent);
                    } else {
                        Toast.makeText(verUnTurno.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    Toast.makeText(verUnTurno.this, "Seleccione una opcion", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void ObtenerReferencias() {
        dtpFecha = (DatePicker) findViewById(R.id.dtpMod);
        tmpHora = (TimePicker) findViewById(R.id.tmpMod);
        spnCentro = (Spinner) findViewById(R.id.spnCentro);
        spnMedico = (Spinner) findViewById(R.id.spnMedico);
        btnAceptar = (Button) findViewById(R.id.btnAceptar);
        btnBorrar = (Button) findViewById(R.id.btnBorrar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnModificar = (Button) findViewById(R.id.btnModificar);
    }

    private void Refresh() {
        turno.idturno = id;
        turno = turno.TraerUno();
        String[] vec = new String[3];
        String[] vec2 = new String[3];
        vec = turno.Fecha.split("-");
        vec2 = turno.Hora.split(":");
        int year = Integer.parseInt(vec[0]);
        int month = Integer.parseInt(vec[1]);
        int day = Integer.parseInt(vec[2]);
        int hour = Integer.parseInt(vec2[0]);
        int minute = Integer.parseInt(vec2[1]);
        dtpFecha.updateDate(year, month - 1, day);
        tmpHora.setCurrentHour(hour);
        tmpHora.setCurrentMinute(minute);
        medico.idmedico = turno.idmedico;
        medicoAdapter = new MedicoAdapter(medico.TraerMedico(), this);
        spnMedico.setAdapter(medicoAdapter);
        for (int i = 0; i < medicoAdapter.getCount(); i++) {
            if (medicoAdapter.getItemId(i) == medico.idmedico) {
                spnMedico.setSelection(i);
            }
        }
        centro.idcentro = medico.idcentro;
        centroAdapter = new CentroAdapter(centro.TraerCentro(), this);
        spnCentro.setAdapter(centroAdapter);
        for (int i = 0; i < centroAdapter.getCount(); i++) {
            if (centroAdapter.getItemId(i) == centro.idcentro) {
                spnCentro.setSelection(i);
            }
        }
    }

    public static class MedicoAdapter extends BaseAdapter {
        private ArrayList<Medico> Lista;
        private Context miContext;

        public MedicoAdapter(ArrayList<Medico> lst, Context context) {
            miContext = context;
            Lista = lst;
        }

        public int getCount() {
            return Lista.size();
        }

        public Medico getItem(int index) {
            Medico devolver;
            devolver = Lista.get(index);
            return devolver;
        }

        public long getItemId(int index) {
            return Lista.get(index).idmedico;
        }

        public View getView(int index, View vista, ViewGroup grupo) {
            View vistaDevolver;
            LayoutInflater inflater;
            inflater = (LayoutInflater) miContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vistaDevolver = inflater.inflate(R.layout.celda, grupo, false);
            TextView txtAlgo;
            txtAlgo = (TextView) vistaDevolver.findViewById(R.id.txtCelda);
            txtAlgo.setText(getItem(index).nombre + " " + getItem(index).apellido);
            return vistaDevolver;
        }


    }

    public static class CentroAdapter extends BaseAdapter {
        private ArrayList<Centro> Lista;
        private Context miContext;

        public CentroAdapter(ArrayList<Centro> lst, Context context) {
            miContext = context;
            Lista = lst;
        }

        public int getCount() {
            return Lista.size();
        }

        public Centro getItem(int index) {
            Centro devolver;
            devolver = Lista.get(index);
            return devolver;
        }

        public long getItemId(int index) {
            return Lista.get(index).idcentro;
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