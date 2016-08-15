package com.example.alfa.alfacare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class iniciarSesion extends AppCompatActivity {
    EditText edtUsuario, edtPass;
    Button btnEnviar;
    Usuario miUsuario = new Usuario();
    public final static String hola2 = "aeaea";
    public final static String ajaj = "cholocheist";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        ObtenerReferencias();
        btnEnviar.setOnClickListener(btnEnviar_Click);
    }
    private View.OnClickListener btnEnviar_Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            miUsuario.usuario = edtUsuario.getText().toString();
            miUsuario = miUsuario.TraerUno();
            if (miUsuario.clave.equals(edtPass.getText().toString()))
            {
                Intent elintent = new Intent(iniciarSesion.this, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(verTurnos.hola, miUsuario.idpaciente);
                elintent.putExtras(bundle);
                startActivity(elintent);
            }
            else
            {
                Toast.makeText(iniciarSesion.this, "Usuario o Contraseña invalidos", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private void ObtenerReferencias()
    {
        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
    }
}
