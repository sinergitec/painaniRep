package com.sienrgitec.painanirep.actividades;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
import com.sienrgitec.painanirep.model.ctUsuario;
import com.sienrgitec.painanirep.model.opPedPainani;
import com.sienrgitec.painanirep.model.opPedPainaniDet;
import com.sienrgitec.painanirep.model.opPedido;
import com.sienrgitec.painanirep.model.opPedidoDet;
import com.sienrgitec.painanirep.model.opPedidoProveedor;
import com.sienrgitec.painanirep.model.opUbicaPainani;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends AppCompatActivity {
    public Globales globales;
    private static RequestQueue mRequestQueue;
    private String url = globales.URL;
    private AdapterHome adapter;
    private AdapterPedXProv adapterPedXProv;

    public Integer viPedido;
    public Integer viPainani;
    public Integer viProveedor;

    TextView txtDomCli;
    Button   btnLlegoP;
    Button   btnSalidaP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtDomCli  = (TextView) findViewById(R.id.tvNombreDom);
        btnLlegoP  = (Button) findViewById(R.id.btnLlegoP);
        btnSalidaP = (Button) findViewById(R.id.btnSalidaP);


        btnLlegoP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActPedPainaniDet("Llega", viPedido,  viProveedor);

            }
        });
        btnSalidaP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActPedPainaniDet("Salida", viPedido,  viProveedor);

            }
        });


        locationStart();



    }

    private void locationStart() {
        Log.e("home", "location");
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 30000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);

    }

    public class Localizacion implements LocationListener {
        Home mainActivity;
        public Home getMainActivity() {
            return mainActivity;
        }
        public void setMainActivity(Home mainActivity) {
            this.mainActivity = mainActivity;
        }
        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion
            loc.getLatitude();
            loc.getLongitude();

            CreaUbicacion( loc.getLatitude(), loc.getLongitude());

            this.mainActivity.setLocation(loc);
        }
        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado

        }
        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado

        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }

    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void CreaUbicacion(double vdeLatitud, double vdeLongitud){
        //locationStart();
        globales.g_ctPedPainaniList = null;

        DecimalFormat df = new DecimalFormat("0.00000000000");


        double vdeFLatitud = Double.parseDouble(df.format(vdeLatitud));
        double vdeFLongitud = Double.parseDouble(df.format(vdeLongitud));


        Log.e("vdeFLongitud", "vdeFLongitud " + vdeFLongitud);

        final ProgressDialog nDialog;
        nDialog = new ProgressDialog(getApplicationContext());
        nDialog.setMessage("Cargando...");
        nDialog.setTitle("Guarda Ubicaion");
        nDialog.setIndeterminate(false);

        globales.opUbicaPainaniList.clear();

        opUbicaPainani opCreaUbicacion = new opUbicaPainani();
        opCreaUbicacion.setiPartida(0);
        opCreaUbicacion.setiPainani(globales.g_ctUsuario.getiPersona());
        opCreaUbicacion.setDtFecha(null);
        opCreaUbicacion.setDtFHora(null);
        opCreaUbicacion.setDeLatitud(vdeFLatitud);
        opCreaUbicacion.setDeLongitud(vdeFLongitud);
        opCreaUbicacion.setiHora(0);

        globales.opUbicaPainaniList.add(opCreaUbicacion);


        JSONObject jsonBody = new JSONObject();
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonDataSet = new JSONObject();

        final Gson gson = new Gson();
        String JS_opUbicacion = gson.toJson(
                globales.opUbicaPainaniList,
                new TypeToken<ArrayList<opUbicaPainani>>() {
                }.getType());


        try {
            JSONArray ctUbicacionJS   = new JSONArray(JS_opUbicacion);
            jsonDataSet.put("tt_opUbicaPainani",  ctUbicacionJS);
            jsonParams.put("ds_Ubicacion", jsonDataSet);
            jsonBody.put("request", jsonParams);

            Log.i("Response", jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            nDialog.dismiss();
            MuestraMensaje("Error", e.getMessage());

        }

        getmRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url + "opUbicaPainani/", jsonBody, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta resrt->", "mensaje: " + respuesta.toString());

                            Boolean Error = respuesta.getBoolean("oplError");
                            String Mensaje = respuesta.getString("opcError");

                            JSONObject ds_opPedPainani    = respuesta.getJSONObject("tt_opPedPainani");
                            JSONObject ds_opPedPainaniDet = respuesta.getJSONObject("tt_opPedPainaniDet");

                            JSONArray tt_opPedPainani      = ds_opPedPainani.getJSONArray("tt_opPedPainani");
                            JSONArray tt_opPedPainaniDet   = ds_opPedPainaniDet.getJSONArray("tt_opPedPainaniDet");

                            globales.g_ctPedPainaniList    = Arrays.asList(new Gson().fromJson(tt_opPedPainani.toString(), opPedPainani[].class));
                            globales.g_ctPedPainaniDetList = Arrays.asList(new Gson().fromJson(tt_opPedPainaniDet.toString(), opPedPainaniDet[].class));

                            if (Error == true) {
                                nDialog.dismiss();
                                MuestraMensaje("Error" , Mensaje);

                            } else {

                                if(!globales.g_ctPedPainaniList.isEmpty()) {
                                    globales.g_opPedPainani = globales.g_ctPedPainaniList.get(0);
                                    ConfirmaPedido();

                                }
                            }

                        } catch (JSONException e) {
                            Log.i("Error JSONExcepcion", e.getMessage());

                            nDialog.dismiss();
                            Log.i("Error JSONExcepcion", e.getMessage());
                            MuestraMensaje("Error", "Error Conversión de Datos." + "\n " + e.getMessage());

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

    public void MuestraMensaje(String vcTitulo, String vcMensaje){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(Home.this);
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

    public void ConfirmaPedido() {
        //SystemClock.sleep(20000);

        Log.e("home-->", "busca pedidos");
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setCancelable(true);
        builder.setTitle(Html.fromHtml("<font color ='#FF0000'> Tienes un nuevo pedido </font>"));
        builder.setMessage("¿Aceptar Pedido?");
        builder.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActualizaPedido(true);
                    }
                });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActualizaPedido(false);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void ActualizaPedido(Boolean vlAceptado){
        final ProgressDialog nDialog;
        nDialog = new ProgressDialog(getApplicationContext());
        nDialog.setMessage("Cargando...");
        nDialog.setTitle("Actualizando Pedido");
        nDialog.setIndeterminate(false);


        opPedPainani objActualizaPed  = new opPedPainani();

        objActualizaPed.setcCvePedido  (globales.g_opPedPainani.getcCvePedido());
        objActualizaPed.setcDirCliente (globales.g_opPedPainani.getcDirCliente());
        objActualizaPed.setDtAvisado   (globales.g_opPedPainani.getDtAvisado());
        objActualizaPed.setDtContestado(globales.g_opPedPainani.getDtContestado());
        objActualizaPed.setDtFecha     (globales.g_opPedPainani.getDtFecha());
        objActualizaPed.setDtReasignado(globales.g_opPedPainani.getDtReasignado());
        objActualizaPed.setiAsignadoA  (globales.g_opPedPainani.getiAsignadoA());
        objActualizaPed.setiCliente    (globales.g_opPedPainani.getiCliente());
        objActualizaPed.setiPainani    (globales.g_opPedPainani.getiPainani());
        objActualizaPed.setiPedido     (globales.g_opPedPainani.getiPedido());
        objActualizaPed.setlAceptado   (vlAceptado);
        objActualizaPed.setlContestado (true);
        objActualizaPed.setlReasignado (false);

        globales.opPedPainaniList.add(objActualizaPed);

        JSONObject jsonBody = new JSONObject();
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonDataSet = new JSONObject();

        final Gson gson = new Gson();
        String JS_opPedPainani = gson.toJson(
                globales.opPedPainaniList,
                new TypeToken<ArrayList<opPedPainani>>() {
                }.getType());



        try {
            JSONArray opPedPainaniJS   = new JSONArray(JS_opPedPainani);
            jsonDataSet.put("tt_opPedPainani",  opPedPainaniJS);

            jsonParams.put("ds_opPedido", jsonDataSet);
            jsonBody.put("request", jsonParams);

            Log.i("Response", jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            nDialog.dismiss();
            MuestraMensaje("Error", e.getMessage());

        }

        getmRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, url + "opPedPainani/", jsonBody, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta resrt->", "mensaje: " + respuesta.toString());

                            Boolean Error = respuesta.getBoolean("oplError");
                            String Mensaje = respuesta.getString("opcError");

                            JSONObject ds_opPedido          = respuesta.getJSONObject("tt_opPedido");
                            JSONObject ds_opPedidoProveedor = respuesta.getJSONObject("tt_opPedidoProveedor");
                            JSONObject ds_opPedidoDet       = respuesta.getJSONObject("tt_opPedidoDet");

                            JSONArray tt_opPedido           = ds_opPedido.getJSONArray("tt_opPedido");
                            JSONArray tt_opPedidoProveedor  = ds_opPedidoProveedor.getJSONArray("tt_opPedidoProveedor");
                            JSONArray tt_opPedidoDet        = ds_opPedidoDet.getJSONArray("tt_opPedidoDet");



                            globales.g_opPedidoList      = Arrays.asList(new Gson().fromJson(tt_opPedido.toString(), opPedido[].class));
                            globales.g_opPedidoProvtList = Arrays.asList(new Gson().fromJson(tt_opPedidoProveedor.toString(), opPedidoProveedor[].class));
                            globales.g_opPedidoDetList   = Arrays.asList(new Gson().fromJson(tt_opPedidoDet.toString(), opPedidoDet[].class));



                            if (Error == true) {
                                nDialog.dismiss();
                                MuestraMensaje("Error" , Mensaje);

                            } else {
                                /*Datos de entrega*/
                                txtDomCli.setText(globales.g_ctPedPainaniList.get(0).getcDirCliente());


                                /*Encabezado de pedido x Proveedor*/
                                final ListView lviewPedxProv = (ListView) findViewById(R.id.lvPedxProv);
                                ArrayList<opPedidoProveedor> arrayPedidoxProv = new ArrayList<opPedidoProveedor>(globales.g_opPedidoProvtList);
                                adapterPedXProv = new AdapterPedXProv(Home.this,arrayPedidoxProv );
                                lviewPedxProv.setAdapter(adapterPedXProv);


                                lviewPedxProv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int iPartida, long l) {
                                        Log.i("Home -->", " Detalle presiono " + iPartida  );


                                        viPedido  = (globales.g_opPedidoProvtList.get(iPartida).getiPedido());
                                        viProveedor = (globales.g_opPedidoProvtList.get(iPartida).getiProveedor());
                                    }
                                });


                                final ListView lviewDetPed = (ListView) findViewById(R.id.lvDetalle);
                                ArrayList<opPedidoDet> arrayPedidoDet = new ArrayList<opPedidoDet>(globales.g_opPedidoDetList);
                                adapter = new AdapterHome(Home.this,arrayPedidoDet );
                                lviewDetPed.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            Log.i("Error JSONExcepcion", e.getMessage());

                            nDialog.dismiss();
                            Log.i("Error JSONExcepcion", e.getMessage());
                            MuestraMensaje("Error", "Error Conversión de Datos." + "\n " + e.getMessage());

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

    public void ActPedPainaniDet(final String vcAccion, Integer iPedido, Integer iProveedor){
        Integer viPainani =   globales.g_ctUsuario.getiPersona();
        Log.e("variables recibidos", vcAccion + " " + iPedido + " "+ globales.g_ctUsuario.getiPersona() + " " +iProveedor);

        getmRequestQueue();
        String urlParams = String.format(url + "opPedPainaniDet?ipiPedido=%1$s&ipiPainani=%2$s&ipiProveedor=%3$s&ipcAccion=%4$s", iPedido, viPainani,iProveedor, vcAccion );

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PUT, urlParams, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject respuesta = response.getJSONObject("response");
                            Log.i("respuesta--->", respuesta.toString());

                            String Mensaje = respuesta.getString("opcError");
                            Boolean Error = respuesta.getBoolean("oplError");





                            if (Error == true) {

                                MuestraMensaje("Error", Mensaje);
                                return;

                            } else {




                            }
                        } catch (JSONException e) {

                            AlertDialog.Builder myBuild = new AlertDialog.Builder(Home.this);
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
                        AlertDialog.Builder myBuild = new AlertDialog.Builder(Home.this);
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
                params.put("ipiPedido", "iPedido");
                params.put("ipiPainani", "viPainani");
                params.put("ipiProveedor", "iProveedor");
                params.put("ipcAccion", vcAccion);


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
}
