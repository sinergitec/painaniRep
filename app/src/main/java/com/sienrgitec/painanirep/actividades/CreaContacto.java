package com.sienrgitec.painanirep.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.sienrgitec.painanirep.R;

public class CreaContacto extends AppCompatActivity {

    Button btnCreaRegistro;
    EditText etNombreUsu;
    EditText etPwReg;
    EditText etNombreC;
    EditText etApellidosC;
    EditText etObsC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_contacto);
    }
}
