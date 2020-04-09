package com.sienrgitec.painanirep.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.configuracion.Globales;

public class Login extends AppCompatActivity {
    public Globales globales;

    private static RequestQueue mRequestQueue;
    private String url = globales.URL;

    Button btnEntrar;
    TextView txtRegistro;
    EditText etNombreU;
    EditText etPasword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btnEntrar   = (Button)   findViewById(R.id.btnEntrar);
        txtRegistro = (TextView) findViewById(R.id.textRegistro);
        etNombreU   = (EditText) findViewById(R.id.etUsuLog);
        etPasword   = (EditText) findViewById(R.id.etPword);

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
        btnEntrar.setEnabled(false);
        final String password = etPasword.getText().toString();
        Log.i("passw", password);
        //Globales.g_ctEmpleado = null;


        if (etPasword.getText().toString().isEmpty()) {
            AlertDialog.Builder myBuild = new AlertDialog.Builder(Login.this);
            myBuild.setMessage("No se capturo el password del usuario");
            myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'> ERROR </font>"));
            myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });

            AlertDialog dialog = myBuild.create();
            dialog.show();
            return;
        }


    }
    public void NuevoUsuario(){
        startActivity(new Intent(Login.this, ValidaDir.class));
        finish();
    }
    public void MuestraMensaje(){

    }





}
