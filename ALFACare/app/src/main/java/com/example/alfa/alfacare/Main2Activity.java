package com.example.alfa.alfacare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static int idPaciente;
    public static int idUsuario;
    TextView txtNombre, txtDni, txtObra, txtSocio;
    ListView lstUsuarios;
    Adapter adapter;
    ImageButton btnNuevo;
    Usuario usuario = new Usuario();
    public static Paciente paciente = new Paciente();
    public static AlarmManager alarmManager;
    public static PendingIntent pending;
    public static AlarmManager alarmManager2;
    public static PendingIntent pending2;
    public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ObtenerReferencias();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        idPaciente = bundle.getInt(verTurnos.hola);
        idUsuario = bundle.getInt(verChats.shalom);
        paciente.idPaciente = idPaciente;
        paciente = paciente.TraerUno();
        txtNombre.setText(paciente.nombre + " " + paciente.apellido);
        txtDni.setText(String.valueOf(paciente.dni));
        txtObra.setText(paciente.obrasocial);
        txtSocio.setText(String.valueOf(paciente.socio));
        usuarios = usuario.TraerUsuariosPaciente(idPaciente);
        adapter = new Adapter(usuarios,this);
        lstUsuarios.setAdapter(adapter);
        btnNuevo.setOnClickListener(btnNuevo_Click);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(getApplicationContext(), MyIntentService.class);
        pending = PendingIntent.getService(getApplicationContext(), 0, alarmIntent, 0);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(), 30000, pending);
        alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent2 = new Intent(getApplicationContext(), PublicacionService.class);
        pending2 = PendingIntent.getService(getApplicationContext(), 0, alarmIntent2, 0);
        alarmManager2.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(), 30000, pending2);
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
        getMenuInflater().inflate(R.menu.main2, menu);
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
            Intent intent = new Intent(Main2Activity.this, verTurnos.class);
            Bundle bundle = new Bundle();
            bundle.putInt(verTurnos.hola, idPaciente);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.nav_muro) {
            Intent intent = new Intent(Main2Activity.this, muroActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt(verTurnos.hola, idPaciente);
            bundle.putInt(verChats.shalom, idUsuario);
            intent.putExtras(bundle);
            startActivity(intent);

        } else if (id == R.id.nav_chat) {
            Intent intent = new Intent(Main2Activity.this, verChats2.class);
            Bundle bundle = new Bundle();
            bundle.putInt(verTurnos.hola, idUsuario);
            intent.putExtras(bundle);
            startActivity(intent);

        } else if (id == R.id.nav_paciente) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ObtenerReferencias() {
        txtDni = (TextView) findViewById(R.id.txtDni);
        txtNombre = (TextView) findViewById(R.id.txtNomApe);
        txtObra = (TextView) findViewById(R.id.txtObra);
        txtSocio = (TextView) findViewById(R.id.txtSocio);
        lstUsuarios = (ListView) findViewById(R.id.lstUsuarios);
        btnNuevo =(ImageButton) findViewById(R.id.btnNuevo);
    }

    public class Adapter extends BaseAdapter {
        private ArrayList<Usuario> Lista;
        private Context miContext;

        public Adapter(ArrayList<Usuario> lst, Context context) {
            miContext = context;
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
            vistaDevolver = inflater.inflate(R.layout.celdausuarios, grupo, false);
            TextView txtAlgo;
            ImageButton btnEliminar,btnModificar;
            btnEliminar = (ImageButton) vistaDevolver.findViewById(R.id.btnEliminarUsu);
            btnModificar = (ImageButton) vistaDevolver.findViewById(R.id.btnModificarUsu);
            btnModificar.setTag(getItem(index).idUsuario);
            btnEliminar.setTag(getItem(index).idUsuario);
            btnEliminar.setOnClickListener(btnEliminar_Click);
            btnModificar.setOnClickListener(btnModificar_Click);
            txtAlgo = (TextView) vistaDevolver.findViewById(R.id.txtNomCelda);
            txtAlgo.setText(getItem(index).nombre + " " + getItem(index).apellido);
            return vistaDevolver;
        }
    }
    private View.OnClickListener btnEliminar_Click = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            new AlertDialog.Builder(Main2Activity.this)
                    .setTitle("Eliminar")
                    .setMessage("¿Esta seguro que quiere eliminar?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            usuario.idUsuario = (Integer) view.getTag();
                            UsuarioPaciente algo = new UsuarioPaciente();
                            algo.idusuario = (Integer) view.getTag();
                            Boolean hola = usuario.Borrar();
                            Boolean chau = algo.Borrar();
                            if (!hola || !chau)
                            {
                                Toast.makeText(Main2Activity.this, "No se ha podido eliminar", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Main2Activity.this, "Se ha eliminado con exito", Toast.LENGTH_SHORT).show();
                                ArrayList<Usuario> usuarios = usuario.TraerUsuariosPaciente(idPaciente);
                                adapter = new Adapter(usuarios,Main2Activity.this);
                                lstUsuarios.setAdapter(adapter);
                            }
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    };
    private View.OnClickListener btnModificar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(Main2Activity.this, "modificar", Toast.LENGTH_SHORT).show();
            /*Intent elintent = new Intent(Main2Activity.this, modificarPaciente.class);
            Bundle bundle = new Bundle();
            bundle.putInt(iniciarSesion.ajaj, adapter.getItem((Integer) view.getTag()).idUsuario);*/
        }
    };
    private View.OnClickListener btnNuevo_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Main2Activity.this, agregarFamiliar.class);
            Bundle bundle = new Bundle();
            bundle.putInt(verTurnos.hola, idPaciente);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}