package com.sienrgitec.painanirep.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.configuracion.Globales;
import com.sienrgitec.painanirep.model.ctComisiones;
import com.sienrgitec.painanirep.model.opDispPainani;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AsignaComision extends AppCompatActivity {

    public Globales globales;
    private static RequestQueue mRequestQueue;
    private String url = globales.URL;
    private EditText  etAporta;

    RadioGroup mRgAllButtons;


    Button btnAgregar;

    public Integer viComision = 0;
    public String vcComision = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asigna_comision);


        btnAgregar = (Button) findViewById(R.id.btnOk);
        mRgAllButtons = findViewById(R.id.radiogroup);
        etAporta = (EditText) findViewById(R.id.etOtraComision);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(viComision == 0){
                    MuestraMensaje("Error", "No has Seleccionado la Aportación");
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(AsignaComision.this);
                builder.setCancelable(true);
                builder.setTitle(Html.fromHtml("<font color ='#FF0000'> Agrega Aportacion </font>"));
                builder.setMessage("¿Deseas contribuir a la comunidad con este porcentaje: " + vcComision + " al finalizar el día  ?");
                builder.setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AgregarComision();
                            }
                });
	            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

            }
        });


        CreaBotonesCom();
    }

    public void CreaBotonesCom(){
        LinearLayout GridOrdenes = (LinearLayout) findViewById(R.id.LinearComision);

        /*for(final ctComisiones objComisiones: globales.g_ctComisionesList){
            Log.e("asigan comision ", objComisiones.getcComision());
            final Drawable d = getResources().getDrawable(R.drawable.btnporcentaje);
            final Button myButton = new Button(getBaseContext());
            //Personalizando botones
            myButton.setId(objComisiones.getiComision());
            myButton.setText(objComisiones.getDeValor() + "%");
            myButton.setTag(objComisiones.getiComision().toString());
            myButton.setBackgroundDrawable(d);
            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                        viComision = objComisiones.getiComision();
                        vcComision = objComisiones.getDeValor().toString();
                        Log.e("Selecciono", "comision " + viComision );
                        Drawable d = getResources().getDrawable(R.drawable.seleccporc);
                        myButton.setBackgroundDrawable(d);
                }
            });
            GridOrdenes.addView(myButton);
        }*/

        mRgAllButtons.setOrientation(LinearLayout.HORIZONTAL);

        int vxMod = 100;
        int vyMod = 0;
        int vCuantosMod = 0;



        //
        for(final ctComisiones objComisiones: globales.g_ctComisionesList){



            vCuantosMod = vCuantosMod + 1;

            RadioButton rdbtn = new RadioButton(this);

            if(objComisiones.getcComision().equals("OTRO")){
                rdbtn.setText("OTRO");
            }else {
                rdbtn.setText(objComisiones.getDeValor() + "%");
            }


            Drawable d = getResources().getDrawable(R.drawable.radiob);
            rdbtn.setBackgroundDrawable(d);
            rdbtn.setWidth(100);
            rdbtn.setHeight(60);
            rdbtn.setX(vxMod);
            rdbtn.setY(vyMod);


            if (vCuantosMod % 4 == 0) {
                vxMod = -300;
                vyMod = vyMod + 70;
            } else {
                vxMod = vxMod + 10;

            }



            rdbtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    viComision = objComisiones.getiComision();
                    vcComision = objComisiones.getDeValor().toString();
                    Log.e("Selecciono", "comision " + viComision );

                    if (objComisiones.getcComision().equals("OTRO")){
                        etAporta.setVisibility(View.VISIBLE);
                    }else{
                        etAporta.setVisibility(View.INVISIBLE);
                    }

                }
            });




            mRgAllButtons.addView(rdbtn);
        }



    }

    public void AgregarComision(){


        final ProgressDialog nDialog;
        nDialog = new ProgressDialog(AsignaComision.this);
        nDialog.setMessage("Cargando...");
        nDialog.setTitle("Creando Registro de Contribución");
        nDialog.setIndeterminate(false);


        if (etAporta.getText().toString().isEmpty()) {
            AlertDialog.Builder myBuild = new AlertDialog.Builder(AsignaComision.this);
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


        opDispPainani objComisionDispP = new opDispPainani();
        objComisionDispP.setiPainani(globales.g_opDispPList.get(0).getiPainani());
        objComisionDispP.setDtFecha(globales.g_opDispPList.get(0).getDtFecha());
        objComisionDispP.setiComision(viComision);
        objComisionDispP.setDtCheckIn(globales.g_opDispPList.get(0).getDtCheckIn());
        objComisionDispP.setiCheckIn(globales.g_opDispPList.get(0).getiCheckIn());
        objComisionDispP.setDtCheckOut(globales.g_opDispPList.get(0).getDtCheckOut());
        objComisionDispP.setiCheckOut(globales.g_opDispPList.get(0).getiCheckOut());
        objComisionDispP.setiEstadoProceso(globales.g_opDispPList.get(0).getiEstadoProceso());
        objComisionDispP.setDeUltLat(globales.g_opDispPList.get(0).getDeUltLat());
        objComisionDispP.setDeUltLong(globales.g_opDispPList.get(0).getDeUltLong());

        globales.opDispPainani.add(objComisionDispP);


        JSONObject jsonBody = new JSONObject();
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonDataSet = new JSONObject();

        final Gson gson = new Gson();

        String JS_opDispPainani = gson.toJson(
                globales.opDispPainani,
                new TypeToken<ArrayList<opDispPainani>>() {
                }.getType());



        try {
            JSONArray opDispPainaniJS   = new JSONArray(JS_opDispPainani);


            jsonDataSet.put("tt_opDispPainani",  opDispPainaniJS);
            jsonParams.put("ds_opDispPainani", jsonDataSet);


            jsonBody.put("request", jsonParams);


            Log.i("Response", jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            nDialog.dismiss();
            MuestraMensaje("Error", e.getMessage());
            btnAgregar.setEnabled(true);
        }


        getmRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url + "opDispPainani/", jsonBody, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta resrt->", "mensaje: " + respuesta.toString());

                            Boolean Error = respuesta.getBoolean("oplError");
                            String Mensaje = respuesta.getString("opcError");

                            //JSONObject ds_opdispPainani   = respuesta.getJSONObject("tt_opDispPainani");
                            //JSONArray tt_opDispPainani = ds_opdispPainani.getJSONArray("tt_opDispPainani");
                            //globales.g_opDispPList       = Arrays.asList(new Gson().fromJson(tt_opDispPainani.toString(), opDispPainani[].class));



                            if (Error == true) {
                                nDialog.dismiss();
                                MuestraMensaje("Error" , Mensaje);
                                btnAgregar.setEnabled(true);

                            } else {
                                MuestraMensaje("Aviso" , "Contribución del día Asignada");

                                startActivity(new Intent(AsignaComision.this, Home.class));
                                finish();
                            }

                        } catch (JSONException e) {
                            Log.i("Error JSONExcepcion", e.getMessage());

                            nDialog.dismiss();
                            Log.i("Error JSONExcepcion", e.getMessage());
                            MuestraMensaje("Error", "Error Conversión de Datos." + "\n " + e.getMessage());
                            btnAgregar.setEnabled(true);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("Error", error.toString());
                        nDialog.dismiss();
                        MuestraMensaje("Error", error.toString());
                        btnAgregar.setEnabled(true);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        mRequestQueue.add(jsonObjectRequest);

    }

    public void MuestraMensaje(String vcTitulo,  String vcMensaje){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(AsignaComision.this);
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
}
