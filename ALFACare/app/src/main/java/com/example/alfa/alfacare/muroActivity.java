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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class muroActivity extends AppCompatActivity {
    EditText edtPublicacion;
    Button btnPublicar;
    ListView lstPublicaciones;
    Adapter adapter;
    Publicacion publicacion = new Publicacion();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muro);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        publicacion.idpaciente = Main2Activity.idPaciente;
        publicacion.idusuario = Main2Activity.idUsuario;
        ObtenerReferencias();
        adapter = new Adapter(publicacion.TraerPublicacion(), this);
        lstPublicaciones.setAdapter(adapter);
        btnPublicar.setOnClickListener(btnPublicar_Click);
    }
    private void ObtenerReferencias(){
        edtPublicacion = (EditText) findViewById(R.id.edtPublicacion);
        btnPublicar = (Button) findViewById(R.id.btnPublicar);
        lstPublicaciones = (ListView) findViewById(R.id.lstPublicaciones);
    }
    private View.OnClickListener btnPublicar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (edtPublicacion.length() > 0)
            {
                publicacion.mensaje = edtPublicacion.getText().toString();
                publicacion.NuevoPublicacion();
                adapter = new Adapter(publicacion.TraerPublicacion(), muroActivity.this);
                lstPublicaciones.setAdapter(adapter);
            }

        }
    };
    public class Adapter extends BaseAdapter {
        private ArrayList<Publicacion> Lista;
        private Context miContext;
        public Adapter (ArrayList<Publicacion> lst, Context context)
        {
            miContext = context;
            Lista = lst;
        }
        public int getCount()
        {
            return Lista.size();
        }
        public Publicacion getItem (int index)
        {
            Publicacion devolver;
            devolver = Lista.get(index);
            return devolver;
        }
        public long getItemId (int index)
        {
            return Lista.get(index).idpublicacion;
        }
        public View getView(int index, View vista, ViewGroup grupo)
        {
            View vistaDevolver;
            LayoutInflater inflater;
            inflater = (LayoutInflater)miContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vistaDevolver = inflater.inflate(R.layout.publicacioncelda, grupo, false);
            TextView txtAlgo, txtOtro;
            txtAlgo = (TextView)vistaDevolver.findViewById(R.id.txtNombrePublicacion);
            txtOtro = (TextView)vistaDevolver.findViewById(R.id.txtPublicacion);
            txtAlgo.setText(getItem(index).usuario);
            txtOtro.setText(getItem(index).mensaje);
            return vistaDevolver;
        }
    }

}
