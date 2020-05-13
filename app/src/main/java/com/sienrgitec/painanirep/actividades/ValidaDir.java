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
import android.view.WindowManager;
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
import com.sienrgitec.painanirep.model.ctDomicilio;

public class ValidaDir extends AppCompatActivity {

    public Globales globales;
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
    Button   btnCancelar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validadir);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        vcCalle  =   (EditText) findViewById(R.id.etCalle);
        vcNumE   =   (EditText) findViewById(R.id.etNumE);
        vcNumI   =   (EditText) findViewById(R.id.etNumI);
        vcEstado =   (EditText) findViewById(R.id.etEstado);
        vcPais   =   (EditText) findViewById(R.id.etPais);
        vcCP     =   (EditText) findViewById(R.id.etCP);
        vcColonia    = (EditText) findViewById(R.id.etColonia);
        vcDelegacion = (EditText) findViewById(R.id.etAlcaldia);

        btnValida   = (Button) findViewById(R.id.btnValida);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnValida.setEnabled(true);

        btnValida.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnValida.setEnabled(false);
                geoLocate(v);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //startActivity(new Intent(ValidaDir.this, Login.class));
                startActivity(new Intent(ValidaDir.this, Home.class));
                finish();
            }
        });

    }



    private void geoLocate(View view){

        if (vcCalle.getText().toString().isEmpty()) {
            btnValida.setEnabled(true);
            MuestraMensaje("Error", "No se capturó la calle .");
            return;
        }
        if (vcNumE.getText().toString().isEmpty()) {
            btnValida.setEnabled(true);
            MuestraMensaje("Error", "No se capturó el número exterior.");
            return;
        }
        if (vcEstado.getText().toString().isEmpty()) {
            btnValida.setEnabled(true);
            MuestraMensaje("Error", "No se capturó el estado.");
            return;
        }
        if (vcColonia.getText().toString().isEmpty()) {
            btnValida.setEnabled(true);
            MuestraMensaje("Error", "No se capturó nombre de la colonia.");
            return;
        }
        if (vcDelegacion.getText().toString().isEmpty()) {
            btnValida.setEnabled(true);
            MuestraMensaje("Error", "No se capturó el Nombre de la Alcaldía.");
            return;
        }
        if (vcCP.getText().toString().isEmpty()) {
            btnValida.setEnabled(true);
            MuestraMensaje("Error", "No se capturó el Código Postal.");
            return;
        }


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

    private void ValidaUbicacion(final double vdeLatitud , final double vdeLongitud){



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
                                btnValida.setEnabled(true);
                                MuestraMensaje("Error", Mensaje);
                                return;

                            } else {
                                ctDomicilio objNuevoDom = new ctDomicilio();
                                objNuevoDom.setDeLatitud(vdeLatitud);
                                objNuevoDom.setDeLongitud(vdeLongitud);
                                objNuevoDom.setcCalle(vcCalle.getText().toString());
                                objNuevoDom.setcNumExt(vcNumE.getText().toString());
                                objNuevoDom.setcNumInt(vcNumI.getText().toString());
                                objNuevoDom.setcColonia(vcColonia.getText().toString());
                                objNuevoDom.setcMpioDeleg(vcDelegacion.getText().toString());
                                objNuevoDom.setcEstado(vcEstado.getText().toString());
                                objNuevoDom.setcCP(vcCP.getText().toString());
                                objNuevoDom.setcPais(vcPais.getText().toString());
                                objNuevoDom.setcReferencia("");
                                objNuevoDom.setcObs("");
                                objNuevoDom.setlActivo(true);
                                objNuevoDom.setDtCreado(null);
                                objNuevoDom.getcUsuModifica();
                                objNuevoDom.setcUsuCrea("");

                                globales.ctDomicilioList.add(objNuevoDom);

                                nDialog.dismiss();
                                startActivity(new Intent(ValidaDir.this, CreaPainani.class));



                                finish();

                            }
                        } catch (JSONException e) {
                            btnValida.setEnabled(true);
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
                            nDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        btnValida.setEnabled(true);
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
                        nDialog.dismiss();
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
