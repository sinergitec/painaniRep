package com.sienrgitec.painanirep.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.sienrgitec.painanirep.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Registro extends AppCompatActivity {
    EditText vcCalle;
    EditText vcNumE;
    EditText vcNumI;
    EditText vcColonia;
    EditText vcDelegacion;
    EditText vcEstado;
    EditText vcPais;
    EditText vcCP;
    Button   btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        vcCalle  =   (EditText) findViewById(R.id.etCalle);
        vcNumE   =   (EditText) findViewById(R.id.etNumE);
        vcNumI   =   (EditText) findViewById(R.id.etNumI);
        vcEstado =   (EditText) findViewById(R.id.etEstado);
        vcPais   =   (EditText) findViewById(R.id.etPais);
        vcCP     =   (EditText) findViewById(R.id.etCP);
        vcColonia    = (EditText) findViewById(R.id.etColonia);
        vcDelegacion = (EditText) findViewById(R.id.etAlcaldia);

        btnRegistro = (Button) findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                geoLocate(v);
            }
        });

    }

    private void geoLocate(View view){
        String vcDireccion = vcCalle.getText().toString() + " " + vcNumE.getText().toString() + " " + vcColonia.getText().toString() + " " + vcDelegacion.getText().toString()
                + " " + vcCP.getText().toString() + " " + vcPais.getText().toString();

        hideSoftKeyboard(view);

        String locationName= vcDireccion;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try{
            List<Address>addressList = geocoder.getFromLocationName(locationName,1);
            if(addressList.size()>0){
                Address address=addressList.get(0);
                Log.e("Reg--->", "latitude " + address.getLatitude() + " longitud " + address.getLongitude());
            }

        } catch (IOException e){

        }
    }

    private void hideSoftKeyboard(View view){
        InputMethodManager imn = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imn.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }
}
