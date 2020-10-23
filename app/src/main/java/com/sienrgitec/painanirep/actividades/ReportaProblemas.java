package com.sienrgitec.painanirep.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import com.sienrgitec.painanirep.model.ctContacto;
import com.sienrgitec.painanirep.model.ctDomicilio;
import com.sienrgitec.painanirep.model.ctPainani;
import com.sienrgitec.painanirep.model.ctUsuario;
import com.sienrgitec.painanirep.model.opBuzonMensaje;
import com.sienrgitec.painanirep.model.opPerfilCli;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportaProblemas extends AppCompatActivity {

    private EditText etProblema, etObs;
    private Button   btnCancelar, btnReportar;
    private Integer viPedido;
    private static RequestQueue mRequestQueue;
    public Globales globales;
    private String url = globales.URL;
    public static ArrayList<opBuzonMensaje>      opNvoProblemaList       = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporta_problemas);

        Intent i = getIntent();
        viPedido =  i.getIntExtra("ipiPedido",0);

        etProblema  = (EditText) findViewById(R.id.etProblema);
        etObs       = (EditText) findViewById(R.id.etObs);
        btnCancelar = (Button)   findViewById(R.id.btnCancel);
        btnReportar = (Button)   findViewById(R.id.btnReportar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Cancelar();
            }
        });

        btnReportar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ReportarProblema();
            }
        });
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

    public void MuestraMensaje(String vcTitulo,  String vcMensaje){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(ReportaProblemas.this);
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

    public void Cancelar(){
        Intent Home = new Intent(ReportaProblemas.this, Home.class);
        Home.putExtra("ipcEvaluado", "cliente");
        startActivity(Home);
        finish();
    }

    public void ReportarProblema(){

        final ProgressDialog nDialog;
        nDialog = new ProgressDialog(ReportaProblemas.this);
        nDialog.setMessage("Cargando...");
        nDialog.setTitle("Generando Reporte");
        nDialog.setIndeterminate(false);

        btnReportar.setEnabled(false);

        if (etProblema.getText().toString().isEmpty()) {
            MuestraMensaje("ERROR","Para poder continuar debes especificar que problema tienes");
            return;
        }

        opBuzonMensaje ObjNvoMensaje = new opBuzonMensaje();

        ObjNvoMensaje.setiPersona(globales.g_ctUsuario.getiPersona());
        ObjNvoMensaje.setiTipoPersona(globales.g_ctUsuario.getiTipoPersona());
        ObjNvoMensaje.setiPedido(viPedido);
        ObjNvoMensaje.setiMensaje(0);
        ObjNvoMensaje.setcTipo("PROBLEMA");
        ObjNvoMensaje.setcMensaje(etProblema.getText().toString());
        ObjNvoMensaje.setcMensaje2("");
        ObjNvoMensaje.setcObs(etObs.getText().toString());
        ObjNvoMensaje.setlLeido(false);
        ObjNvoMensaje.setDtLeido(null);
        ObjNvoMensaje.setDtCreado(null);
        ObjNvoMensaje.setDtModificado(null);
        ObjNvoMensaje.setcUsuCrea(globales.g_ctUsuario.getcUsuario());
        ObjNvoMensaje.setcUsuModifica(globales.g_ctUsuario.getcUsuario());

        opNvoProblemaList.add(ObjNvoMensaje);


        JSONObject jsonBody = new JSONObject();
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonDataSet = new JSONObject();

        final Gson gson = new Gson();
        String JS_opBuzonMensaje = gson.toJson(
                opNvoProblemaList,
                new TypeToken<ArrayList<ctPainani>>() {
                }.getType());

        try {
            JSONArray opBuzonMensaje   = new JSONArray(JS_opBuzonMensaje);

            jsonDataSet.put("tt_opBuzonMensajes",  opBuzonMensaje);

            jsonParams.put("ds_Buzon", jsonDataSet);
            jsonBody.put("request", jsonParams);

            Log.i("Response", jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            //nDialog.dismiss();
            MuestraMensaje("Error", e.getMessage());
            btnReportar.setEnabled(true);
        }


        getmRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url + "reportarProblema/", jsonBody, new Response.Listener<JSONObject>() {
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
                                btnReportar.setEnabled(true);

                            } else {
                                opNvoProblemaList.clear();
                                MuestraMensaje("AVISO", "Tu reporte se generó exitosamente. Pronto un operador se pondrá en contacto contigo");
                                nDialog.dismiss();
                                /*Intent Home = new Intent(ReportaProblemas.this, Home.class);
                                Home.putExtra("ipcEvaluado", "cliente");
                                startActivity(Home);
                                finish();*/
                            }

                        } catch (JSONException e) {
                            Log.i("Error JSONExcepcion", e.getMessage());
                            nDialog.dismiss();
                            Log.i("Error JSONExcepcion", e.getMessage());
                            MuestraMensaje("Error", "Error Conversión de Datos." + "\n " + e.getMessage());
                            btnReportar.setEnabled(true);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("Error", error.toString());
                        nDialog.dismiss();
                        MuestraMensaje("Error", error.toString());
                        btnReportar.setEnabled(true);
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