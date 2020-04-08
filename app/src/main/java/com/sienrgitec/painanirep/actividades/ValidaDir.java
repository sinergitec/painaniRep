package com.sienrgitec.painanirep.actividades;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import android.widget.Button;
import android.widget.EditText;


import com.android.volley.toolbox.StringRequest;
import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.configuracion.Globales;


import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class ValidaDir extends AppCompatActivity {

    private Globales globales;
    private  static RequestQueue mRequestQueue;
    private StringRequest mStringRequest;

    private String url = globales.URL;

    EditText vcCalle;
    EditText vcNumE;
    EditText vcNumI;
    EditText vcColonia;
    EditText vcDelegacion;
    EditText vcEstado;
    EditText vcPais;
    EditText vcCP;
    Button   btnValida;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validadir);

        vcCalle  =   (EditText) findViewById(R.id.etCalle);
        vcNumE   =   (EditText) findViewById(R.id.etNumE);
        vcNumI   =   (EditText) findViewById(R.id.etNumI);
        vcEstado =   (EditText) findViewById(R.id.etEstado);
        vcPais   =   (EditText) findViewById(R.id.etPais);
        vcCP     =   (EditText) findViewById(R.id.etCP);
        vcColonia    = (EditText) findViewById(R.id.etColonia);
        vcDelegacion = (EditText) findViewById(R.id.etAlcaldia);

        btnValida = (Button) findViewById(R.id.btnValida);

        btnValida.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnValida.setEnabled(false);
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

                ValidaUbicacion(address.getLatitude(), address.getLongitude());
            }

        } catch (IOException e){
            Log.e("Reg--->", "e " + e);
        }
    }

    private void hideSoftKeyboard(View view){
        InputMethodManager imn = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imn.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    private void ValidaUbicacion(double vdeLatitud , final double vdeLongitud){

        final ProgressDialog nDialog;
        nDialog = new ProgressDialog(ValidaDir.this);
        nDialog.setMessage("Cargando...");
        nDialog.setTitle("Valida Domicilio");
        nDialog.setIndeterminate(false);
        nDialog.show();




        getmRequestQueue();

        String urlParams = String.format(url + "validaUbicacion?ipdeLatitud=%1$s&ipdeLongitud=%2$s", vdeLatitud, vdeLongitud );

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlParams, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta--->", respuesta.toString());

                            String Mensaje = respuesta.getString("opcMensaje");
                            Boolean Error = respuesta.getBoolean("oplError");


                            if (Error == true) {
                                MuestraMensaje("Error", Mensaje);
                                return;

                            } else {
                                nDialog.dismiss();
                                startActivity(new Intent(ValidaDir.this, CreaPainani.class));
                                finish();

                            }
                        } catch (JSONException e) {

                            AlertDialog.Builder myBuild = new AlertDialog.Builder(ValidaDir.this);
                            myBuild.setMessage("Error en la conversión de Datos. Vuelva a Intentar. " + e);
                            myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'> ERROR CONVERSION </font>"));
                            myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();

                                }
                            });
                            AlertDialog dialog = myBuild.create();
                            dialog.show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("Error Respuesta", error.toString());
                        AlertDialog.Builder myBuild = new AlertDialog.Builder(ValidaDir.this);
                        myBuild.setMessage("No se pudo conectar con el servidor. Vuelva a Intentar. " + error.toString());
                        myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'> ERROR RESPUESTA </font>"));
                        myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog dialog = myBuild.create();
                        dialog.show();
                    }
                }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ipdeLatitud", "vdeLatitud");
                params.put("ipdeLongitud", "vdeLongitud");


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        // Access the RequestQueue through your singleton class.
        mRequestQueue.add(jsonObjectRequest);


    }

    public void getmRequestQueue(){
        try{
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(getApplicationContext());
                //your code
            }
        }catch(Exception e){
            Log.d("Volley",e.toString());
        }
    }

    public void MuestraMensaje(String vcTitulo, String vcMensaje){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(ValidaDir.this);
        myBuild.setMessage(vcMensaje);
        myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'>" + vcTitulo +"</font>"));
        myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                return;

            }
        });
        AlertDialog dialog = myBuild.create();
        dialog.show();
        return;

    }
}
