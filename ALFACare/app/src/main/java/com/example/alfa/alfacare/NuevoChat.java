package com.example.alfa.alfacare;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NuevoChat extends AppCompatActivity {
    Adapter adapter;
    ListView lstUsuarios;
    Usuario usuario = new Usuario();
    Chats chat = new Chats();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_chat);
        lstUsuarios = (ListView) findViewById(R.id.lstNuevoChat);
        adapter = new Adapter(Main2Activity.usuarios,this);
        lstUsuarios.setAdapter(adapter);
        lstUsuarios.setOnItemClickListener(lstUsuarios_Click);

    }
    private AdapterView.OnItemClickListener lstUsuarios_Click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            boolean bool = true;
            int j = 0;
            for (j = 0; j < verChats2.arrayList.size(); j++) {
                if (adapter.getItem(i).idUsuario == verChats2.arrayList.get(j).idusuario1 || adapter.getItem(i).idUsuario == verChats2.arrayList.get(j).idusuario2) {
                    bool = false;
                }
            }
                if (bool) {
                    chat.idusuario1 = Main2Activity.idUsuario;
                    chat.idusuario2 = adapter.getItem(i).idUsuario;
                    chat.NuevoChat();
                    chat.TraerUnoChats();
                    Intent intent = new Intent(NuevoChat.this, chat.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(verTurnos.hola, chat.idchat);
                    intent.putExtras(bundle);
                }
                else {
                    Toast.makeText(NuevoChat.this, "Ya existe un chat con este usuario", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(NuevoChat.this,chat.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(verTurnos.hola,verChats2.arrayList.get(j).idchat);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
        }
    };
    public class Adapter extends BaseAdapter {
        private ArrayList<Usuario> Lista;
        private Context miContext;

        public Adapter(ArrayList<Usuario> lst, Context context) {
            miContext = context;
            for (int i = 0; i < lst.size(); i++)
            {
                if (lst.get(i).idUsuario == Main2Activity.idUsuario)
                {
                    lst.remove(i);
                }
            }
            Lista = lst;
        }

        public int getCount() {
            return Lista.size();
        }

        public Usuario getItem(int index) {
            Usuario devolver;
            devolver = Lista.get(index);
            return devolver;
        }

        public long getItemId(int index) {
            return Lista.get(index).idUsuario;
        }

        public View getView(int index, View vista, ViewGroup grupo) {
            View vistaDevolver;
            LayoutInflater inflater;
            inflater = (LayoutInflater) miContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vistaDevolver = inflater.inflate(R.layout.celda, grupo, false);
            TextView txtAlgo;
            txtAlgo = (TextView) vistaDevolver.findViewById(R.id.txtCelda);
            txtAlgo.setText(getItem(index).usuario);
            return vistaDevolver;
        }
    }
}
