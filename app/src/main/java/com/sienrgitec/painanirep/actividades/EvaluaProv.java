package com.sienrgitec.painanirep.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.sienrgitec.painanirep.R;

public class EvaluaProv extends AppCompatActivity {

    Button btnEvaluaProv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evalua_prov);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        btnEvaluaProv = (Button) findViewById(R.id.btnEvaluaProv);

        btnEvaluaProv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MuestraMensaje("Aviso", "Hecho");
                CreaEvaluacion();
            }
        });
    }

    public void MuestraMensaje(String vcTitulo, String vcMensaje){

    }

    public void CreaEvaluacion(){
        startActivity(new Intent(EvaluaProv.this, Home.class));
        finish();
    }
}
