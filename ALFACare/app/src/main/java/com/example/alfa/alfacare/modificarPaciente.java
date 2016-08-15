package com.example.alfa.alfacare;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class modificarPaciente extends AppCompatActivity {
    EditText edtNombre, edtApellido, edtDni, edtObraSocial, edtNumSocio;
    Button btnAnadir, btnModificar,btnVerChats;
    Spinner spnObrasocial;
    int id;
    String usuario;
    int idusuario;
    Paciente paciente = new Paciente();
    Obrasocial obra = new Obrasocial();
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_paciente);
        ObtenerReferencias();
        Intent elintent = getIntent();
        Bundle bundle = elintent.getExtras();
        id = bundle.getInt(verTurnos.hola);
        paciente.idPaciente = id;
        usuario = bundle.getString(iniciarSesion.hola2);
        paciente = paciente.TraerUno();
        idusuario = bundle.getInt(iniciarSesion.ajaj);
        edtDni.setText(String.valueOf(paciente.dni));
        edtNumSocio.setText(String.valueOf(paciente.socio));
        edtApellido.setText(paciente.apellido);
        edtNombre.setText(paciente.nombre);
        btnAnadir.setOnClickListener(btnAnadir_Click);
        ArrayList<Obrasocial> array = obra.TraerObrasocial();
        adapter = new Adapter(array, this);
        spnObrasocial.setAdapter(adapter);
        spnObrasocial.setOnItemSelectedListener(spnObrasocial_Click);
        btnModificar.setOnClickListener(btnModificar_Click);
        btnVerChats.setOnClickListener(btnVerChats_Click);
    }
    private View.OnClickListener btnVerChats_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent elintent = new Intent(modificarPaciente.this, verChats.class);
            Bundle bundle = new Bundle();
            bundle.putInt(verTurnos.hola, idusuario);
            elintent.putExtras(bundle);
            startActivity(elintent);
        }
    };
    private AdapterView.OnItemSelectedListener spnObrasocial_Click = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            obra = adapter.getItem(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
    private View.OnClickListener btnAnadir_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent elintent = new Intent(modificarPaciente.this, agregarFamiliar.class);
            Bundle bundle = new Bundle();
            bundle.putInt(verTurnos.hola, id);
            bundle.putString(iniciarSesion.hola2, usuario);
            elintent.putExtras(bundle);
            startActivity(elintent);
        }
    };
    private View.OnClickListener btnModificar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            paciente.nombre = edtNombre.getText().toString();
            paciente.apellido = edtApellido.getText().toString();
            paciente.dni = Integer.parseInt(edtDni.getText().toString());
            paciente.socio = Integer.parseInt(edtNumSocio.getText().toString());
            paciente.idobrasocial = obra.idobrasocial;
            paciente.Modificar();
            Toast.makeText(modificarPaciente.this, "Se modifico el paciente", Toast.LENGTH_SHORT).show();
        }
    };

    private void ObtenerReferencias() {
        edtApellido = (EditText) findViewById(R.id.edtApellido);
        edtDni = (EditText) findViewById(R.id.edtDni);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtNumSocio = (EditText) findViewById(R.id.edtNSocio);
        btnAnadir = (Button) findViewById(R.id.btnUsuario);
        btnModificar = (Button) findViewById(R.id.btnModPac);
        spnObrasocial = (Spinner) findViewById(R.id.spnObrasocial);
        btnVerChats = (Button) findViewById(R.id.btnVerChats);
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