package com.sienrgitec.painanirep.actividades;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.sienrgitec.painanirep.model.ctEvaluacion;
import com.sienrgitec.painanirep.model.ctUsuario;
import com.sienrgitec.painanirep.model.opClienteEvalua;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EvaluaCli extends AppCompatActivity {
    public Globales globales;
    private static RequestQueue mRequestQueue;
    private String url = globales.URL, vcPersona;
    private AdapterEvaluacion adapter;

    public String  vcTipo   = "";
    public Integer viEvalua = 0, viPunto  = 0, viPersona = 0;
    public Float  vdeCalificacion;


    TextView txtFecha, txtPedidoId, txtPedido, txtCliId, txtCliente, txtObsEvalua, txtValor;
    RatingBar ratingBar;

    Button   btnEvaluaCli, btnOk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evalua_cli);


        Intent i = getIntent();
        vcPersona =  i.getStringExtra("ipcPersona");
        vcTipo    = i.getStringExtra("ipcEvaluacion");
        viPersona = i.getIntExtra("ipiPersona", 0);


        Log.e("Evaluacion ","vipersona " + viPersona);

        txtFecha    = (TextView) findViewById(R.id.tvFecha);
        txtCliente  = (TextView) findViewById(R.id.tvCli);
        txtCliId    = (TextView) findViewById(R.id.tvCliId);
        txtPedido   = (TextView) findViewById(R.id.tvPed);
        txtPedidoId = (TextView) findViewById(R.id.tvPedID);
        txtObsEvalua = (TextView) findViewById(R.id.etObsEvalua);

        btnEvaluaCli = (Button)  findViewById(R.id.btnEvalua);
        btnOk        = (Button)  findViewById(R.id.btnOk);
        ratingBar    = (RatingBar) findViewById(R.id.ratingBar);

        txtFecha.setText(globales.g_opPedPainani.getDtFecha());
        //txtCliId.setText(vcPersona);
        txtPedidoId.setText(globales.g_opPedPainani.getiPedido().toString());

        btnOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(viEvalua ==  0){
                    MuestraMensaje("Error", "Debes seleccionar un parametro para evaluar");
                    return;
                }
                MuestraMensaje("Aviso", "Hecho");
                CreaEvaluacion();
            }
        });
        btnEvaluaCli.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FinalizarEvaluacion();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                vdeCalificacion = rating;
            }
        });

        getmRequestQueue();
        String urlParams = String.format(url + "ctEvaluacion?iplActivo=%1$s&ipcTipo=%2$s", true, vcTipo );
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlParams, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta--->", respuesta.toString());

                            String Mensaje = respuesta.getString("opcError");
                            Boolean Error = respuesta.getBoolean("oplError");
                            JSONObject ds_Evaluacion = respuesta.getJSONObject("tt_ctEvaluacion");

                            JSONArray tt_ctEvaluacion  = ds_Evaluacion.getJSONArray("tt_ctEvaluacion");

                            globales.g_ctEvaluacionList     = Arrays.asList(new Gson().fromJson(tt_ctEvaluacion.toString(), ctEvaluacion[].class));


                            if (Error == true) {
                                MuestraMensaje("Error", Mensaje);
                                return;

                            } else {

                                final ListView lviewEvalua = (ListView) findViewById(R.id.lvEvaluacion);
                                ArrayList<ctEvaluacion> arrayctEvaluacion = new ArrayList<ctEvaluacion>(globales.g_ctEvaluacionList);
                                adapter = new AdapterEvaluacion(EvaluaCli.this,arrayctEvaluacion );
                                lviewEvalua.setAdapter(adapter);


                                lviewEvalua.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int iPartida, long l) {
                                        viEvalua  = (globales.g_ctEvaluacionList.get(iPartida).getiEvalua());
                                        viPunto   = (globales.g_ctEvaluacionList.get(iPartida).getiPunto());
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            AlertDialog.Builder myBuild = new AlertDialog.Builder(EvaluaCli.this);
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
                        AlertDialog.Builder myBuild = new AlertDialog.Builder(EvaluaCli.this);
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
                params.put("iplActivo", "true");
                params.put("ipcTipo", vcTipo);



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
        AlertDialog.Builder myBuild = new AlertDialog.Builder(EvaluaCli.this);
        myBuild.setMessage(vcMensaje);
        myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'>" + vcTitulo +"</font>"));
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

    public void CreaEvaluacion(){
        Log.e("Crea Evaluacion--> ","viPersona " + viPersona);

        opClienteEvalua objNvaEvaluacion = new opClienteEvalua();
        objNvaEvaluacion.setiPedido(globales.g_opPedPainani.getiPedido());
        objNvaEvaluacion.setiPersona(viPersona);
        objNvaEvaluacion.setiPunto(viPunto);
        objNvaEvaluacion.setiEvalua(viEvalua);
        objNvaEvaluacion.setiTipoPersona(0);
        objNvaEvaluacion.setcTipo(vcTipo);
        objNvaEvaluacion.setcValor(vdeCalificacion.toString() + "0");
        objNvaEvaluacion.setcObs(txtObsEvalua.getText().toString());
        objNvaEvaluacion.setcUsuCrea(globales.g_ctUsuario.getcUsuario());
        objNvaEvaluacion.setDtCreado("");
        objNvaEvaluacion.setcUsuModifica(globales.g_ctUsuario.getcUsuario());
        objNvaEvaluacion.setDtModificado(null);
        objNvaEvaluacion.setDtFecha(globales.g_opPedPainani.getDtFecha());

        globales.opClienteEvaluaList.add(objNvaEvaluacion);
    }

    public void FinalizarEvaluacion(){

        final ProgressDialog nDialog;
        nDialog = new ProgressDialog(getApplicationContext());
        nDialog.setMessage("Cargando...");
        nDialog.setTitle("Guarda Ubicaion");
        nDialog.setIndeterminate(false);

        JSONObject jsonBody = new JSONObject();
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonDataSet = new JSONObject();




        final Gson gson = new Gson();
        String JS_opClienteEvalua = gson.toJson(
                globales.opClienteEvaluaList,
                new TypeToken<ArrayList<opClienteEvalua>>() {
                }.getType());



        try {
            JSONArray opEvaluaJS   = new JSONArray(JS_opClienteEvalua);
            jsonDataSet.put("tt_opClienteEvalua",  opEvaluaJS);
            jsonParams.put("ds_NvaEvaluacion", jsonDataSet);
            jsonParams.put("ipcPersona", vcPersona);
            jsonBody.put("request", jsonParams);

            Log.i("Response", jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            nDialog.dismiss();
            MuestraMensaje("Error", e.getMessage());
            //btnCreaRegistro.setEnabled(true);
        }

        //mRequestQueue = Volley.newRequestQueue(comanda.this);
        getmRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url + "opClienteEvalua/", jsonBody, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta resrt->", "mensaje: " + respuesta.toString());

                            Boolean Error = respuesta.getBoolean("oplError");
                            String Mensaje = respuesta.getString("opcError");
                            if (Error == true) {
                                nDialog.dismiss();
                                MuestraMensaje("Error" , Mensaje);
                                //btnCreaRegistro.setEnabled(true);

                            } else {
                                MuestraMensaje("Aviso", "La evaluación fue exitosa");
                                //startActivity(new Intent(EvaluaCli.this, EvaluaProv.class));

                                globales.opClienteEvaluaList.clear();
                                startActivity(new Intent(EvaluaCli.this, Home.class));
                                finish();

                            }

                        } catch (JSONException e) {
                            Log.i("Error JSONExcepcion", e.getMessage());

                            nDialog.dismiss();
                            Log.i("Error JSONExcepcion", e.getMessage());
                            MuestraMensaje("Error", "Error Conversión de Datos." + "\n " + e.getMessage());
                            //btnCreaRegistro.setEnabled(true);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("Error", error.toString());
                        nDialog.dismiss();
                        MuestraMensaje("Error", error.toString());
                        //btnCreaRegistro.setEnabled(true);
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

}
