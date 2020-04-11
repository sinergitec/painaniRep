package com.sienrgitec.painanirep.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreaContacto extends AppCompatActivity {
    public Globales globales;
    private static RequestQueue mRequestQueue;
    private String url = globales.URL;

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

        btnCreaRegistro = (Button) findViewById(R.id.btnFinReg);
        etNombreUsu     = (EditText) findViewById(R.id.etUsuario);
        etPwReg         = (EditText) findViewById(R.id.etUsuario);
        etNombreC       = (EditText) findViewById(R.id.etNombreC);
        etApellidosC    = (EditText) findViewById(R.id.etApellidosC);
        etObsC          = (EditText) findViewById(R.id.etObsC);

        btnCreaRegistro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CreaRegistro();
            }
        } );
    }
    public void CreaRegistro(){
        btnCreaRegistro.setEnabled(false);

        if (etNombreUsu.getText().toString().isEmpty()) {
            MuestraMensaje("Error", "El nombre de Usuario no puede estar vacío.");
        }
        if (etPwReg.getText().toString().isEmpty()) {
            MuestraMensaje("Error", "El password de Usuario no puede estar vacío.");
        }
        final ProgressDialog nDialog;
        nDialog = new ProgressDialog(CreaContacto.this);
        nDialog.setMessage("Cargando...");
        nDialog.setTitle("Guarda Orden");
        nDialog.setIndeterminate(false);


        ctUsuario ObjctUsuario = new ctUsuario();
        ObjctUsuario.setcUsuario(etNombreUsu.getText().toString());
        ObjctUsuario.setcPassword(etPwReg.getText().toString());
        ObjctUsuario.setiPersona(0);
        ObjctUsuario.setiTipoPersona(0);
        ObjctUsuario.setlActivo(true);
        ObjctUsuario.setDtCreado(null);
        ObjctUsuario.setDtModificado(null);
        ObjctUsuario.setcUsuarioC("");
        globales.ctUsuarioList.add(ObjctUsuario);



        ctContacto ObjctContacto = new ctContacto();
        ObjctContacto.setiPersona(0);
        ObjctContacto.setiContacto(0);
        ObjctContacto.setiTipoPersona(0);
        ObjctContacto.setiTipoContacto(0);
        ObjctContacto.setcNombre(etNombreC.getText().toString());
        ObjctContacto.setcApellidos(etApellidosC.getText().toString());
        ObjctContacto.setcObs(etObsC.getText().toString());
        ObjctContacto.setlActivo(true);
        ObjctContacto.setDtCreado(null);
        ObjctContacto.setDtModificado(null);
        ObjctContacto.setcUsuCrea("");
        ObjctContacto.setcUsuModifica("");
        globales.ctContactoList.add(ObjctContacto);

        JSONObject jsonBody = new JSONObject();
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonDataSet = new JSONObject();




        final Gson gson = new Gson();
        String JS_ctPainani = gson.toJson(
                globales.ctPainaniList,
                new TypeToken<ArrayList<ctPainani>>() {
                }.getType());

        String JS_ctDomicilio = gson.toJson(
                globales.ctDomicilioList,
                new TypeToken<ArrayList<ctDomicilio>>() {
                }.getType());

        String JS_ctContacto = gson.toJson(
                globales.ctContactoList,
                new TypeToken<ArrayList<ctContacto>>() {
                }.getType());

        String JS_ctUsuario = gson.toJson(
                globales.ctUsuarioList,
                new TypeToken<ArrayList<ctUsuario>>() {
                }.getType());




        try {
            JSONArray ctPainaniJS   = new JSONArray(JS_ctPainani);
            JSONArray ctDomicilioJS = new JSONArray(JS_ctDomicilio);
            JSONArray ctContactoJS  = new JSONArray(JS_ctContacto);
            JSONArray ctUsuarioJS   = new JSONArray(JS_ctUsuario);

            jsonDataSet.put("tt_ctPainani",  ctPainaniJS);
            jsonDataSet.put("tt_ctDomicilio",ctDomicilioJS);
            jsonDataSet.put("tt_ctContacto", ctContactoJS);
            jsonDataSet.put("tt_ctUsuario",  ctUsuarioJS);


            jsonParams.put("ds_ctPainani", jsonDataSet);
            jsonBody.put("request", jsonParams);

            Log.i("Response", jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            nDialog.dismiss();
            MuestraMensaje("Error", e.getMessage());
            btnCreaRegistro.setEnabled(true);
        }

        //mRequestQueue = Volley.newRequestQueue(comanda.this);
        getmRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url + "/ctCreaPainai/", jsonBody, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta resrt->", "mensaje: " + respuesta.toString());

                            Boolean Error = respuesta.getBoolean("oplError");
                            String Mensaje = respuesta.getString("opcMensaje");
                            if (Error == true) {
                                nDialog.dismiss();
                                MuestraMensaje("Error" , Mensaje);
                                btnCreaRegistro.setEnabled(true);

                            } else {
                                globales.ctPainaniList.clear();
                                globales.ctDomicilioList.clear();
                                globales.ctContactoList.clear();
                                globales.ctUsuarioList.clear();

                                finish();
                            }


                        } catch (JSONException e) {
                            Log.i("Error JSONExcepcion", e.getMessage());

                            nDialog.dismiss();
                            Log.i("Error JSONExcepcion", e.getMessage());
                            MuestraMensaje("Error", "Error Conversión de Datos." + "\n " + e.getMessage());
                            btnCreaRegistro.setEnabled(true);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("Error", error.toString());
                        nDialog.dismiss();
                        MuestraMensaje("Error", error.toString());
                        btnCreaRegistro.setEnabled(true);
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
        AlertDialog.Builder myBuild = new AlertDialog.Builder(CreaContacto.this);
        myBuild.setMessage(vcMensaje);
        myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'>" + vcTitulo +"</font>"));
        myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                btnCreaRegistro.setEnabled(true);

            }
        });
        AlertDialog dialog = myBuild.create();
        dialog.show();
        return;

    }
}
