package com.sienrgitec.painanirep.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        btnAvanzar.setEnabled(false);


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
}
