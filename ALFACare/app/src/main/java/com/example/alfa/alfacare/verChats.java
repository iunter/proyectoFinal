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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class verChats extends AppCompatActivity {
    ListView lstChats;
    Adapter adapter;
    Chats chats = new Chats();
    int usuario;
    public static final String shalom = "mayonesa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_chats);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        usuario = bundle.getInt(verTurnos.hola);
        lstChats = (ListView)findViewById(R.id.lstChats);
        ArrayList<Chats> arrayList = new ArrayList<Chats>();
        arrayList = chats.TraerChats(usuario);
        adapter = new Adapter(arrayList,this);
        lstChats.setAdapter(adapter);
        lstChats.setOnItemClickListener(lstChats_Click);
    }
    private AdapterView.OnItemClickListener lstChats_Click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(verChats.this, chat.class);
            Bundle bundle = new Bundle();
            bundle.putInt(verTurnos.hola, adapter.getItem(i).idchat);
            bundle.putInt(verChats.shalom, usuario);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
    public class Adapter extends BaseAdapter {
        private ArrayList<Chats> Lista;
        private Context miContext;

        public Adapter(ArrayList<Chats> lst, Context context) {
            miContext = context;
            Lista = lst;
        }

        public int getCount() {
            return Lista.size();
        }

        public Chats getItem(int index) {
            Chats devolver;
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
            vistaDevolver = inflater.inflate(R.layout.celda, grupo, false);
            TextView txtAlgo;
            txtAlgo = (TextView) vistaDevolver.findViewById(R.id.txtCelda);
            if(usuario == getItem(index).idusuario1) {
                txtAlgo.setText(getItem(index).usuario2);
            }
            else
            {
                txtAlgo.setText(getItem(index).usuario1);
            }
            return vistaDevolver;
        }
    }
}
