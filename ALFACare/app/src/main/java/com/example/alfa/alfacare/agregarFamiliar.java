package com.example.alfa.alfacare;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class agregarFamiliar extends AppCompatActivity {
    EditText edtNombre, edtApellido,edtDni;
    Spinner spnTipos;
    Button btnEnviar;
    Tipo tipo = new Tipo();
    Usuario user = new Usuario();
    Adapter adapter;
    Tipo tipo2 = new Tipo();
    int idpaciente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_familiar);
        ObtenerReferencias();
        Intent elintent = getIntent();
        Bundle bundle = elintent.getExtras();
        idpaciente = bundle.getInt(verTurnos.hola);
        ArrayList<Tipo> array = tipo.TraerTipo();
        adapter = new Adapter(array, this);
        spnTipos.setAdapter(adapter);
        btnEnviar.setOnClickListener(btnEnviar_Click);
        spnTipos.setOnItemSelectedListener(spnTipos_Click);
    }
    private void ObtenerReferencias()
    {
        edtApellido = (EditText) findViewById(R.id.edtApellido);
        edtDni = (EditText) findViewById(R.id.edtDni);
        edtNombre = (EditText)findViewById(R.id.edtNombre);
        spnTipos = (Spinner) findViewById(R.id.spnTipos);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
    }
    private AdapterView.OnItemSelectedListener spnTipos_Click = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tipo2 = adapter.getItem(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
    private View.OnClickListener btnEnviar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            user.usuario = edtNombre.getText().toString() + "_user";
            user.nombre = edtNombre.getText().toString();
            user.apellido = edtApellido.getText().toString();
            user.dni = Integer.parseInt(edtDni.getText().toString());
            user.foto = "sin foto";
            user.matricula = "sin matricula";
            user.idTipo = tipo2.idTipo;
            user.NuevoUsuario();
            UsuarioPaciente uspas = new UsuarioPaciente();
            user = user.TraerUnoDos();
            uspas.idusuario = user.idUsuario;
            uspas.idpaciente = idpaciente;
            uspas.NuevoUsuarioPaciente();
            Toast.makeText(agregarFamiliar.this, "Familiar Agregado", Toast.LENGTH_SHORT).show();
        }
    };
    public class Adapter extends BaseAdapter {
        private ArrayList<Tipo> Lista;
        private Context miContext;
        public Adapter (ArrayList<Tipo> lst, Context context)
        {
            miContext = context;
            Lista = lst;
        }
        public int getCount()
        {
            return Lista.size();
        }
        public Tipo getItem (int index)
        {
            Tipo devolver;
            devolver = Lista.get(index);
            return devolver;
        }
        public long getItemId (int index)
        {
            return Lista.get(index).idTipo;
        }
        public View getView(int index, View vista, ViewGroup grupo)
        {
            View vistaDevolver;
            LayoutInflater inflater;
            inflater = (LayoutInflater)miContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vistaDevolver = inflater.inflate(R.layout.celda, grupo, false);
            TextView txtAlgo;
            txtAlgo = (TextView)vistaDevolver.findViewById(R.id.txtCelda);
            txtAlgo.setText(getItem(index).desc);
            return vistaDevolver;
        }
    }
}
