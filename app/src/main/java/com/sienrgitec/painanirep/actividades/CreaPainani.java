package com.sienrgitec.painanirep.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.configuracion.Globales;
import com.sienrgitec.painanirep.model.ctPainani;

public class CreaPainani extends AppCompatActivity {
    public Globales    globales;

    ImageButton btnAvanzar;
    EditText    etNombre;
    EditText    etAP;
    EditText    etAM;
    EditText    etCorreo;
    EditText    etWApp;
    EditText    etFbook;
    EditText    etTwit;
    EditText    etObs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_painani);


        btnAvanzar = (ImageButton) findViewById(R.id.btnAdelante);
        etNombre   = (EditText)    findViewById(R.id.etNombre);
        etAP       = (EditText)    findViewById(R.id.etApellidoP);
        etAM       = (EditText)    findViewById(R.id.etApellidoM);
        etCorreo   = (EditText)    findViewById(R.id.etEmail);
        etWApp     = (EditText)    findViewById(R.id.etCelular);
        etFbook    = (EditText)    findViewById(R.id.etFBook);
        etTwit     = (EditText)    findViewById(R.id.etTwit);
        etObs      = (EditText)    findViewById(R.id.etObsP);

        btnAvanzar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                CreaUsuario();
            }
        });
    }

    public void CreaUsuario(){
        String vcMensaje = "";

        btnAvanzar.setEnabled(false);

        if (etNombre.getText().toString().isEmpty()) {
            btnAvanzar.setEnabled(true);
            vcMensaje = "No se ha capturado el Nombre.";
            MuestraMensaje(vcMensaje);
            return;
        }
        if (etAP.getText().toString().isEmpty()) {
            btnAvanzar.setEnabled(true);
            vcMensaje = "No se ha capturado el Apellido Paterno.";
            MuestraMensaje(vcMensaje);
            return;
        }
        if (etAM.getText().toString().isEmpty()) {
            btnAvanzar.setEnabled(true);
            vcMensaje = "No se ha capturado el Apellido Materno.";
            MuestraMensaje(vcMensaje);
            return;
        }
        if (etCorreo.getText().toString().isEmpty()) {
            btnAvanzar.setEnabled(true);
            vcMensaje = "No se ha capturado el correo electronico del usuario.";
            MuestraMensaje(vcMensaje);
            return;
        }
        if (etWApp.getText().toString().isEmpty()) {
            btnAvanzar.setEnabled(true);
            vcMensaje = "El Número telefonico del usuario no puede estar vacío.";
            MuestraMensaje(vcMensaje);
            return;
        }


        ctPainani ObjNvoPainani = new ctPainani();

        ObjNvoPainani.setcNombre(etNombre.getText().toString());
        ObjNvoPainani.setcApellidoPat(etAP.getText().toString());
        ObjNvoPainani.setcApellidoMat(etAM.getText().toString());
        ObjNvoPainani.setlVehiculo(true);
        ObjNvoPainani.setcMail(etCorreo.getText().toString());
        ObjNvoPainani.setcWhattsApp(etWApp.getText().toString());
        ObjNvoPainani.setcFacebook(etFbook.getText().toString());
        ObjNvoPainani.setcTwitter(etTwit.getText().toString());
        ObjNvoPainani.setcObs(etObs.getText().toString());
        ObjNvoPainani.setDtCreado(null);
        ObjNvoPainani.setDtModificado(null);
        ObjNvoPainani.setiEdoPainani(1);
        ObjNvoPainani.setcUsuCrea("");
        ObjNvoPainani.setcUsuModifica("");

        globales.ctPainaniList.add(ObjNvoPainani);

        startActivity(new Intent(CreaPainani.this, CreaContacto.class));
        finish();

    }
    public void MuestraMensaje(String vcError){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(getApplicationContext());
        myBuild.setMessage(vcError);
        myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'> ERROR </font>"));
        myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                btnAvanzar.setEnabled(true);

            }
        });
        AlertDialog dialog = myBuild.create();
        dialog.show();
    }
}
