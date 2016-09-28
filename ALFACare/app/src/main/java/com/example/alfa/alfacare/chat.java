package com.example.alfa.alfacare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class chat extends AppCompatActivity {
    ListView lstChat;
    Button btnEnviar;
    EditText edtMensaje;
    Mensaje mensaje = new Mensaje();
    Adapter adapter;
    ArrayList<Mensaje> list = new ArrayList<Mensaje>();
    public static int cont;
    public static AlarmManager alarmManager;
    public static PendingIntent pending;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ObtenerReferencias();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mensaje.idchat = bundle.getInt(verTurnos.hola);
        list = mensaje.TraerMensaje();
        mensaje.idusuario = bundle.getInt(verChats.shalom);
        adapter = new Adapter(list,this);
        lstChat.setAdapter(adapter);
        cont = adapter.getCount();
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent alarmIntent = new Intent(getApplicationContext(), MyIntentService.class);
            pending = PendingIntent.getService(getApplicationContext(), 0, alarmIntent, 0);
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + 3000, 60000, pending);
        btnEnviar.setOnClickListener(btnEnviar_Click);
    }
    private View.OnClickListener btnEnviar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (edtMensaje.length() > 0)
            {
                mensaje.mensaje = edtMensaje.getText().toString();
                mensaje.NuevoMensaje();
                list = mensaje.TraerMensaje();
                adapter = new Adapter(list, chat.this);
                lstChat.setAdapter(adapter);
                cont = adapter.getCount();
            }
        }
    };
    private void ObtenerReferencias()
    {
        lstChat = (ListView) findViewById(R.id.lstMensajes);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        edtMensaje = (EditText) findViewById(R.id.edtMensaje);
    }
    public class Adapter extends BaseAdapter {
        private Usuario user = new Usuario();
        private ArrayList<Mensaje> Lista;
        private Context miContext;

        public Adapter(ArrayList<Mensaje> lst, Context context) {
            miContext = context;
            Lista = lst;
        }

        public int getCount() {
            return Lista.size();
        }

        public Mensaje getItem(int index) {
            Mensaje devolver;
            devolver = Lista.get(index);
            return devolver;
        }

        public long getItemId(int index) {
            return Lista.get(index).idchat;
        }

        public View getView(int index, View vista, ViewGroup grupo) {
            View vistaDevolver;
            LayoutInflater inflater;
            inflater = (LayoutInflater) miContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            user.usuario = getItem(index).usuario;
            user = user.TraerUno();
            if (getItem(index).idusuario == mensaje.idusuario) {
                vistaDevolver = inflater.inflate(R.layout.mensajechatderecha, grupo, false);
            }
            else
            {
                vistaDevolver = inflater.inflate(R.layout.mensajechat, grupo, false);
            }
            TextView txtAlgo;
            TextView txtNada;
            txtAlgo = (TextView) vistaDevolver.findViewById(R.id.txtNombre);
            txtNada = (TextView) vistaDevolver.findViewById(R.id.txtMensaje);
            txtAlgo.setText(getItem(index).usuario);
            txtNada.setText(getItem(index).mensaje);
            return vistaDevolver;
        }
    }
}
