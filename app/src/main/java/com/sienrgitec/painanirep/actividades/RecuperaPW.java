package com.sienrgitec.painanirep.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.configuracion.Globales;
import com.sienrgitec.painanirep.model.ctUsuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RecuperaPW extends AppCompatActivity {
    public Globales globales;
    private static RequestQueue mRequestQueue;
    private String url = globales.URL;

    Button btnRecPW;
    EditText etRecEmail;
    EditText etRecTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_pw);

        btnRecPW = (Button) findViewById(R.id.btnRecPW);
        etRecEmail = (EditText) findViewById(R.id.etRecEmail);
        etRecTelefono = (EditText) findViewById(R.id.etRecTelefono);

        btnRecPW.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                RecuperaPW();
            }
        });
    }

    public void RecuperaPW(){
        btnRecPW.setEnabled(false);

        if (etRecEmail.getText().toString().isEmpty()) {
            MuestraMensaje("Error", "El correo de Usuario no puede estar vacío.");
        }
        if (etRecTelefono.getText().toString().isEmpty()) {
            MuestraMensaje("Error", "El numero de celular de Usuario no puede estar vacío.");
        }

        getmRequestQueue();

        getmRequestQueue();
        String urlParams = String.format(url + "PwordPainani?ipcEmail=%1$s&ipcTelefono=%2$s", etRecEmail.getText().toString(), etRecTelefono.getText() );

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlParams, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta--->", respuesta.toString());

                            Boolean Error = respuesta.getBoolean("oplError");
                            String Mensaje = respuesta.getString("opcError");
                            JSONObject ds_ctUsuario = respuesta.getJSONObject("tt_ctUsuario");

                            JSONArray tt_ctUsuario  = ds_ctUsuario.getJSONArray("tt_ctUsuario");

                            globales.g_ctUsuarioList     = Arrays.asList(new Gson().fromJson(tt_ctUsuario.toString(), ctUsuario[].class));


                            if (Error == true) {
                                btnRecPW.setEnabled(true);
                                MuestraMensaje("Error", Mensaje);
                                return;

                            } else {
                                for(ctUsuario objusuario : globales.g_ctUsuarioList){
                                    Log.e("recpass", "pass" + objusuario.getcPassword());
                                }
                                btnRecPW.setEnabled(true);
                                MuestraMensaje("Aviso", "Bienvenido" );


                            }
                        } catch (JSONException e) {
                            btnRecPW.setEnabled(true);
                            AlertDialog.Builder myBuild = new AlertDialog.Builder(RecuperaPW.this);
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
                        btnRecPW.setEnabled(true);
                        // TODO: Handle error
                        Log.i("Error Respuesta", error.toString());
                        AlertDialog.Builder myBuild = new AlertDialog.Builder(RecuperaPW.this);
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
                params.put("ipcUsuario", etRecEmail.getText().toString());
                params.put("ipcPassword", etRecTelefono.getText().toString());


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

    public void MuestraMensaje(String vcTitulo, String vcMensaje){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(RecuperaPW.this);
        myBuild.setMessage(vcMensaje);
        myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'>" + vcTitulo +"</font>"));
        myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                btnRecPW.setEnabled(true);

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
