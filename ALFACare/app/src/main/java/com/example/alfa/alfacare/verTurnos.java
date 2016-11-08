package com.example.alfa.alfacare;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class verTurnos extends AppCompatActivity {
    ListView lstTurnos;
    Adapter adapter;
    ImageButton imageButton;
    turnoClass turno = new turnoClass();
    public final static String hola = "mayonesa con pure";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_turnos);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        turno.idpaciente = bundle.getInt(verTurnos.hola);
        lstTurnos = (ListView) findViewById(R.id.listView);
        imageButton = (ImageButton) findViewById(R.id.btnTurnoNuevo);
        ArrayList<turnoClass> array = turno.TraerTurnos();
        adapter = new Adapter(array, this);
        lstTurnos.setAdapter(adapter);
        lstTurnos.setOnItemClickListener(lstTurnos_Click);
        imageButton.setOnClickListener(imageButton_Click);
    }
    private View.OnClickListener imageButton_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(verTurnos.this, com.example.alfa.alfacare.turno.class);
            startActivity(intent);

        }
    };
    private AdapterView.OnItemClickListener lstTurnos_Click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent elintent = new Intent(verTurnos.this, verUnTurno.class);
            Bundle bundle = new Bundle();
            bundle.putInt(hola, adapter.getItem(i).idturno);
            elintent.putExtras(bundle);
            startActivity(elintent);
        }
    };
    public class Adapter extends BaseAdapter{
        private ArrayList<turnoClass> Lista;
        private Context miContext;
        public Adapter (ArrayList<turnoClass> lst, Context context)
        {
            miContext = context;
            Lista = lst;
        }
        public int getCount()
        {
            return Lista.size();
        }
        public turnoClass getItem (int index)
        {
            turnoClass devolver;
            devolver = Lista.get(index);
            return devolver;
        }
        public long getItemId (int index)
        {
            return Lista.get(index).idturno;
        }
        public View getView(int index, View vista, ViewGroup grupo)
        {
            View vistaDevolver;
            LayoutInflater inflater;
            inflater = (LayoutInflater)miContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vistaDevolver = inflater.inflate(R.layout.celda, grupo, false);
            TextView txtAlgo;
            txtAlgo = (TextView)vistaDevolver.findViewById(R.id.txtCelda);
            txtAlgo.setText(getItem(index).medicoNombre + " " + getItem(index).medicoApellido + "\n" + getItem(index).Fecha + " " + getItem(index).Hora);
            return vistaDevolver;
        }
    }

}
