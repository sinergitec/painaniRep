package com.sienrgitec.painanirep.actividades;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Build;
import android.os.Bundle;

import android.os.Handler;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

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
import com.sienrgitec.painanirep.model.ctPainani;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import java.util.Map;


public class Home extends AppCompatActivity {
    public Globales globales;
    private static RequestQueue mRequestQueue;
    private String url = globales.URL;
    private AdapterHome adapter;
    private AdapterPedXProv adapterPedXProv;

    public Integer viPedido = 0;
    public Integer viProveedor = 0;
    public Integer viPedidoProv;
    private static  final int idUnica = 6192523;




    TextView txtDomCli;
    Button   btnLlegoP;
    Button   btnSalidaP;
    ProgressBar progressBar;
    TextView tvEstatusP;
    Switch sEstatusP;
    NotificationCompat.Builder notificacion;

    public static  List<opPedidoDet> listapedido = null;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtDomCli  = (TextView) findViewById(R.id.tvNombreDom);
        btnLlegoP  = (Button) findViewById(R.id.btnLlegoP);
        btnSalidaP = (Button) findViewById(R.id.btnSalidaP);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.getIndeterminateDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        tvEstatusP = (TextView) findViewById(R.id.tvDescEst);
        sEstatusP  = (Switch)   findViewById(R.id.switch1);

        notificacion = new NotificationCompat.Builder(this);
        notificacion.setAutoCancel(true);

        tvEstatusP.setText("Disponible");

        btnLlegoP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(viPedido ==  0){
                    MuestraMensaje("Error", "Debes seleccionar al menos un pedido");
                    return;
                }
                MuestraMensaje("Aviso", "Asegurate de que los productos correspondan con el pedido");
                ActPedPainaniDet("Llega", viPedido,  viProveedor);
            }
        });
        btnSalidaP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(viProveedor ==  0){
                    MuestraMensaje("Error", "Debes seleccionar al menos un pedido");
                    return;
                }
                ActPedPainaniDet("Salida", viPedido,  viProveedor);
                MuestraMensaje("Aviso", "Hecho");
            }
        });

        sEstatusP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(view.getId()==R.id.switch1){
                    if(view.getId()==R.id.switch1){
                        if(sEstatusP.isChecked()){
                            tvEstatusP.setText("Disponible");
                            ActualizaEstadoP(true);
                        }else {
                            tvEstatusP.setText("No Disponible");
                            ActualizaEstadoP(false);
                        }
                    }

                }
            }
        });

        BuscaCoordenadas();
        onTrimMemory(0x0000003c);

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

    public void BuscaCoordenadas(){
        /**busca coordenadas**/
        LocationManager locationManager = (LocationManager) Home.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener= new LocationListener(){
            public void onLocationChanged(Location location){

                CreaUbicacion(location.getLatitude() , location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            @Override
            public void onProviderEnabled(String provider){}
            @Override
            public void onProviderDisabled(String provider){}

        };
        int permissionCheck = ContextCompat.checkSelfPermission(Home.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,30000,0,locationListener);
        /*********************/
    }

    public void CreaUbicacion(double vdeLatitud, double vdeLongitud){

        globales.g_ctPedPainaniList = null;
        DecimalFormat df = new DecimalFormat("0.00000000000");
        double vdeFLatitud = Double.parseDouble(df.format(vdeLatitud));
        double vdeFLongitud = Double.parseDouble(df.format(vdeLongitud));


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

    public void ConfirmaPedido() {
        notificacion.setSmallIcon(R.mipmap.ic_launcher);
        notificacion.setTicker("Nuevo pedido");
        notificacion.setWhen(System.currentTimeMillis());
        notificacion.setContentTitle("Nuevo Pedido");
        notificacion.setContentText("tienes un nuevo pedido ");


        Intent intent = new Intent(Home.this,Home.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(Home.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notificacion.setContentIntent(pendingIntent);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(idUnica,notificacion.build());



        progressBar.setVisibility(View.VISIBLE);
        final AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setCancelable(true);
        builder.setTitle(Html.fromHtml("<font color ='#FF0000'> Tienes un nuevo pedido </font>"));
        builder.setMessage("¿Aceptar Pedido?");
        builder.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActualizaPedido(true);

                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActualizaPedido(false);

                progressBar.setVisibility(View.INVISIBLE);
            }
        });


        final AlertDialog alert = builder.create();
        alert.show();

        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alert.isShowing()) {
                    alert.dismiss();
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.e("Home ...", "cierre de alert");
                    ActualizaPedido(false);
                }
            }
        };
        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });
        handler.postDelayed(runnable, 15000);
    }

    public void ConstruyeDet(Integer viPedido, Integer viPedidoProv){

        globales.g_ctDetalleFinal.clear();
        for(opPedidoDet obj: globales.g_opPedidoDetList){
            if(obj.getiPedido().equals(viPedido) && obj.getiPedProv().equals(viPedidoProv)){

                opPedidoDet objFinal = new opPedidoDet();
                objFinal.setcDescripcion(obj.getcDescripcion());
                objFinal.setDeCantidad(obj.getDeCantidad());
                globales.g_ctDetalleFinal.add(objFinal);




            }
        }

        final ListView lviewDetPed = (ListView) findViewById(R.id.lvDetalle);
        ArrayList<opPedidoDet> arrayPedidoDet = new ArrayList<opPedidoDet>(globales.g_ctDetalleFinal);
        adapter = new AdapterHome(Home.this,arrayPedidoDet );
        lviewDetPed.setAdapter(adapter);

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
                                        viPedidoProv = (globales.g_opPedidoProvtList.get(iPartida).getiPedidoProv());

                                        ConstruyeDet( viPedido,  viPedidoProv);
                                    }
                                });


                                /*final ListView lviewDetPed = (ListView) findViewById(R.id.lvDetalle);
                                ArrayList<opPedidoDet> arrayPedidoDet = new ArrayList<opPedidoDet>(globales.g_opPedidoDetList);
                                adapter = new AdapterHome(Home.this,arrayPedidoDet );
                                lviewDetPed.setAdapter(adapter);*/
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

    public void ActualizaEstadoP(final Boolean vlEstatus){
        sEstatusP.setEnabled(false);
        getmRequestQueue();
        String urlParams = String.format(url + "ctPainani?iplActivo=%1$s&ipiPainani=%2$s", vlEstatus, globales.g_ctUsuario.getiPersona());

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
                                sEstatusP.setEnabled(true);
                                MuestraMensaje("Aviso", "Estatus Actualizado");

                            }
                        } catch (JSONException e) {
                            sEstatusP.setEnabled(true);
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
                        sEstatusP.setEnabled(true);
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
                params.put("iplActivo", "vlEstatus");
                params.put("ipiPainani", globales.g_ctUsuario.getiPersona().toString());


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


}
