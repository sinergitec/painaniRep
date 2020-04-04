package com.sienrgitec.painanirep.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.sienrgitec.painanirep.R;

public class Login extends AppCompatActivity {

    Button btnEntrar;
    TextView txtRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btnEntrar   = (Button)   findViewById(R.id.btnEntrar);
        txtRegistro = (TextView) findViewById(R.id.textRegistro);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BuscarUsuario();
            }
        });

        txtRegistro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               NuevoUsuario();
            }
        });

    }

    public void BuscarUsuario(){
        Log.e("Login", "buscando usuario" );

    }
    public void NuevoUsuario(){
        startActivity(new Intent(Login.this, Registro.class));
        finish();
    }

    public void MuestraMensaje(){

    }




}
