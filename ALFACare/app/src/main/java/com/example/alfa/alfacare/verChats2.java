package com.example.alfa.alfacare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class verChats2 extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    ListView lstChats;
    Adapter adapter;
    Chats chats = new Chats();
    int usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_chats2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Toast.makeText(verChats2.this, "hola", Toast.LENGTH_SHORT).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        usuario = Main2Activity.idUsuario;
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
            Intent intent = new Intent(verChats2.this, chat.class);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ver_chats2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_turno) {
            Intent intent = new Intent(verChats2.this, verTurnos.class);
            Bundle bundle = new Bundle();
            bundle.putInt(verTurnos.hola, Main2Activity.idPaciente);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.nav_muro) {
            Intent intent = new Intent(verChats2.this, muroActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt(verTurnos.hola, Main2Activity.idPaciente);
            bundle.putInt(verChats.shalom, Main2Activity.idUsuario);
            intent.putExtras(bundle);
            startActivity(intent);

        } else if (id == R.id.nav_chat) {

        } else if (id == R.id.nav_paciente) {
            Intent intent = new Intent(verChats2.this, Main2Activity.class);
            Bundle bundle = new Bundle();
            bundle.putInt(verTurnos.hola, Main2Activity.idPaciente);
            bundle.putInt(verChats.shalom, Main2Activity.idUsuario);
            intent.putExtras(bundle);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
