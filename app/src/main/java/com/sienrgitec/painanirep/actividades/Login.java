package com.sienrgitec.painanirep.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.sienrgitec.painanirep.model.ctComisiones;
import com.sienrgitec.painanirep.model.ctEstadoPainani;
import com.sienrgitec.painanirep.model.ctEstadoProceso;
import com.sienrgitec.painanirep.model.ctRazones;
import com.sienrgitec.painanirep.model.ctUsuario;
import com.sienrgitec.painanirep.model.ctVehiculo;
import com.sienrgitec.painanirep.model.opDispPainani;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {


    private static RequestQueue mRequestQueue;
    public Globales globales;
    public Double vdeLongitud, vdeLatitud;



    private String url = globales.URL;


    Button   btnEntrar;
    TextView txtRegistro, txtRecuperaPw;
    EditText etNombreU, etPasword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btnEntrar     = (Button)   findViewById(R.id.btnEntrar);
        txtRegistro   = (TextView) findViewById(R.id.textRegistro);
        txtRecuperaPw = (TextView) findViewById(R.id.txtRecuperaPW);
        etNombreU     = (EditText) findViewById(R.id.etUsuLog);
        etPasword     = (EditText) findViewById(R.id.etPword);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BuscarUsuario();
            }
        });

        txtRegistro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               NuevoUsuario();
            }
        });

        txtRecuperaPw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RecuperaPW();
            }
        });

        BuscaCoordenadas();
    }


    public void BuscaCoordenadas(){
        Log.e("Principal-->", "BUSCAcoORDENADAS");

        LocationManager locationManager = (LocationManager) Login.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener= new LocationListener(){
            public void onLocationChanged(Location location){
                globales.vg_deLatitud = location.getLatitude();
                globales.vg_deLongitud = location.getLongitude();

                Log.e("posision --> : ", "latitud " + globales.vg_deLatitud  + " " +  globales.vg_deLongitud);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            @Override
            public void onProviderEnabled(String provider){}
            @Override
            public void onProviderDisabled(String provider){}

        };
        int permissionChecks = ContextCompat.checkSelfPermission(Login.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,2000,0,locationListener);
        Toast.makeText(Login.this, "OBTENIENDO TU UBICACIÓN" , Toast.LENGTH_LONG).show();

    }







    public void BuscarUsuario(){
        btnEntrar.setEnabled(false);

        globales.g_ctComisionesList    = null;

        final String vcUsuLog = etNombreU.getText().toString();
        final String password = etPasword.getText().toString();

        if (etNombreU.getText().toString().isEmpty()) {
            AlertDialog.Builder myBuild = new AlertDialog.Builder(Login.this);
            myBuild.setMessage("No se capturo el nombre de usuario");
            myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'> ERROR </font>"));
            myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    btnEntrar.setEnabled(true);

                }
            });
            AlertDialog dialog = myBuild.create();
            dialog.show();
            return;
        }


        if (etPasword.getText().toString().isEmpty()) {
            AlertDialog.Builder myBuild = new AlertDialog.Builder(Login.this);
            myBuild.setMessage("No se capturo el password del usuario");
            myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'> ERROR </font>"));
            myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    btnEntrar.setEnabled(true);

                }
            });
            AlertDialog dialog = myBuild.create();
            dialog.show();
            return;
        }

        getmRequestQueue();

        String urlParams = String.format(url + "buscaPainani?ipcUsuario=%1$s&ipcPassword=%2$s&ipdeLatitud=%3$s&ipdeLongitud=%4$s", vcUsuLog, password, globales.vg_deLatitud,
                globales.vg_deLongitud );
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
                            Boolean lComision = respuesta.getBoolean("oplInicio");
                            JSONObject ds_ctUsuario       = respuesta.getJSONObject("tt_ctUsuario");
                            JSONObject ds_ctComisiones    = respuesta.getJSONObject("tt_ctComisiones");
                            JSONObject ds_ctEstadoPainani = respuesta.getJSONObject("tt_ctEstadoPainani");
                            JSONObject ds_ctRazones       = respuesta.getJSONObject("tt_ctRazones");
                            JSONObject ds_ctEdoProceso    = respuesta.getJSONObject("tt_ctEstadoProceso");
                            JSONObject ds_ctVehiculo      = respuesta.getJSONObject("tt_ctVehiculo");


                            JSONArray tt_ctUsuario       = ds_ctUsuario.getJSONArray("tt_ctUsuario");
                            JSONArray tt_ctComisiones    = ds_ctComisiones.getJSONArray("tt_ctComisiones");
                            JSONArray tt_ctEstadoPainani = ds_ctEstadoPainani.getJSONArray("tt_ctEstadoPainani");
                            JSONArray tt_ctRazones       = ds_ctRazones.getJSONArray("tt_ctRazones");
                            JSONArray tt_ctEstadoProceso = ds_ctEdoProceso.getJSONArray("tt_ctEstadoProceso");
                            JSONArray tt_ctVehiculo      = ds_ctVehiculo.getJSONArray("tt_ctVehiculo");

                            globales.g_ctUsuarioList     = Arrays.asList(new Gson().fromJson(tt_ctUsuario.toString(), ctUsuario[].class));
                            globales.g_ctComisionesList  = Arrays.asList(new Gson().fromJson(tt_ctComisiones.toString(), ctComisiones[].class));
                            globales.g_ctEdoPainaniList  = Arrays.asList(new Gson().fromJson(tt_ctEstadoPainani.toString(), ctEstadoPainani[].class));
                            globales.g_ctRazonesList     = Arrays.asList(new Gson().fromJson(tt_ctRazones.toString(), ctRazones[].class));
                            globales.g_ctEdoProcesoList  = Arrays.asList(new Gson().fromJson(tt_ctEstadoProceso.toString(), ctEstadoProceso[].class));
                            globales.g_ctVehiculoList    = Arrays.asList(new Gson().fromJson(tt_ctVehiculo.toString(), ctVehiculo[].class));



                            JSONObject ds_opdispPainani   = respuesta.getJSONObject("tt_opDispPainani");
                            JSONArray tt_opDispPainani = ds_opdispPainani.getJSONArray("tt_opDispPainani");
                            globales.g_opDispPList       = Arrays.asList(new Gson().fromJson(tt_opDispPainani.toString(), opDispPainani[].class));


                            if (Error == true) {
                                btnEntrar.setEnabled(true);
                                MuestraMensaje("Error", Mensaje);
                                return;

                            } else {
                                globales.g_ctUsuario = globales.g_ctUsuarioList.get(0);

                                if(lComision == true){
                                    startActivity(new Intent(Login.this, AsignaComision.class));
                                    finish();
                                }else {
                                    /*startActivity(new Intent(Login.this, Home.class));
                                    finish();*/

                                    Intent Home = new Intent(Login.this, Home.class);
                                    Home.putExtra("ipcEvaluado", "cliente");
                                    startActivity(Home);
                                    finish();

                                }
                            }
                        } catch (JSONException e) {
                            btnEntrar.setEnabled(true);
                            AlertDialog.Builder myBuild = new AlertDialog.Builder(Login.this);
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
                        btnEntrar.setEnabled(true);
                        // TODO: Handle error
                        Log.i("Error Respuesta", error.toString());
                        AlertDialog.Builder myBuild = new AlertDialog.Builder(Login.this);
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
                params.put("ipcUsuario", vcUsuLog);
                params.put("ipcPassword", password);
                params.put("ipdeLatitud",globales.vg_deLatitud.toString());
                params.put("ipdeLongitud",globales.vg_deLongitud.toString());



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

    public void NuevoUsuario(){
        startActivity(new Intent(Login.this, ValidaDir.class));
        finish();
    }

    public void RecuperaPW(){
        startActivity(new Intent(Login.this, RecuperaPW.class));
        finish();
    }

    public void MuestraMensaje(String vcTitulo,  String vcMensaje){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(Login.this);
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
