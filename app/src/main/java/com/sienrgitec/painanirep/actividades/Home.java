package com.sienrgitec.painanirep.actividades;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
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

import android.os.Vibrator;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.configuracion.ComparadorProv;
import com.sienrgitec.painanirep.configuracion.Globales;
import com.sienrgitec.painanirep.model.ctPainani;
import com.sienrgitec.painanirep.model.ctUsuario;
import com.sienrgitec.painanirep.model.opDispPainani;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

//import static com.sienrgitec.painanirep.actividades.Home.Constants.MY_DEFAULT_TIMEOUT;



public class Home extends AppCompatActivity  implements ComponentCallbacks2  {
    public Globales globales;
    private static RequestQueue mRequestQueue;
    private String url = globales.URL;
    private AdapterHome adapter;
    private AdapterPedXProv adapterPedXProv;
    private AdapterPainaniPed adapterPainaniPed;

    /**viewC*/
    ViewPager viewpager;
    AdapterViewPedDet aviewDet;
    List<opPedPainaniDet> models;

    public Integer viPedido = 0;
    public Integer viProveedor = 0;
    public Integer viPartidaProv = 0;
    private static  final int idUnica = 6192523;






    @Override
    protected  void onDestroy() {

        NotificationManager notificationManager = ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE));

        notificationManager.cancelAll();

        super.onDestroy();
    }



    ProgressBar progressBar;
    TextView txtDomCli,  tvEstatusP, tvRecibe, tvDomProv;
    Switch sEstatusP;
    NotificationCompat.Builder notificacion;
    Button btnLlegoP, btnSalidaP, btnFin, btnSalir, btnEstatus;
    ImageButton ibtnBuscarPed;


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
        btnSalir = (Button) findViewById(R.id.btnSalir);
        ibtnBuscarPed = (ImageButton) findViewById(R.id.ibtnBuscar);
        btnEstatus = (Button) findViewById(R.id.btnEstatus);


        tvRecibe = (TextView) findViewById(R.id.tvNombreRecibe);


        notificacion = new NotificationCompat.Builder(this);
        notificacion.setAutoCancel(true);

        tvEstatusP.setText("Disponible");
        btnFin = (Button) findViewById(R.id.btnFin);

        btnLlegoP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(viPedido ==  0){
                    MuestraMensaje("Error", "Debes seleccionar al menos un pedido");
                    return;
                }

                if(globales.g_opPedidoList.get(0).getiEstadoPedido().equals(6)){
                    MuestraMensaje("Error","Esta Accion no esta permitida. El pedido ya fue recolectado");
                    return;
                }


                MuestraMensaje("Aviso", "Asegurate de que los productos correspondan con el pedido");
                ActPedPainaniDet("Llega", viPedido,  viPartidaProv);
            }
        });
        btnSalidaP.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(viProveedor ==  0){
                    MuestraMensaje("Error", "Debes seleccionar al menos un pedido");
                    return;
                }

                if(globales.g_opPedidoList.get(0).getiEstadoPedido().equals(6 )){
                    MuestraMensaje("Error","Esta Accion no esta permitida. El pedido ya fue recolectado");
                    return;
                }

                ActPedPainaniDet("Salida", viPedido,  viPartidaProv);
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

        btnSalir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CerrarSesion(false, true);

                //Log.e("Home tendo pedidos", "folio" +  globales.g_opPedidoList.get(0).getiPedido());

            }
        });


        ibtnBuscarPed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                BuscarPedido();



            }
        });
        btnFin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                NotificationManager notificationManager = ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE));
                notificationManager.cancelAll();

                TerminarPedido();


            }
        });

        btnEstatus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(Home.this, ActEdoProceso.class));
                finish();

            }
        });



        BuscaCoordenadas();
        onTrimMemory(0x0000003c);

    }

   /* public class Constants {

        public static final int MY_DEFAULT_TIMEOUT = 5000;

        //...
    }*/

    public void BuscarPedido(){


        getmRequestQueue();

        String urlParams = String.format(url + "opPedPainani?ipiPainani=%1$s",  globales.g_opDispPList.get(0).getiPainani());

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


                            if (Error == true) {
                                sEstatusP.setEnabled(true);
                                MuestraMensaje("Aviso", Mensaje);
                                return;

                            } else {

                                JSONObject ds_opPedido = respuesta.getJSONObject("tt_opPedido");
                                JSONObject ds_opPedidoDet = respuesta.getJSONObject("tt_opPedidoDet");
                                JSONObject ds_opPedidoProv = respuesta.getJSONObject("tt_opPedidoProveedor");
                                JSONObject ds_opPedPainani = respuesta.getJSONObject("tt_opPedPainani");
                                JSONObject ds_opPedPainaniDet = respuesta.getJSONObject("tt_opPedPainaniDet");

                                JSONArray tt_opPedido = ds_opPedido.getJSONArray("tt_opPedido");
                                JSONArray tt_opPedidoDet = ds_opPedidoDet.getJSONArray("tt_opPedidoDet");
                                JSONArray tt_opPedidoProveedor = ds_opPedidoProv.getJSONArray("tt_opPedidoProveedor");
                                JSONArray tt_opPedPainani = ds_opPedPainani.getJSONArray("tt_opPedPainani");
                                JSONArray tt_opPedPainaniDet = ds_opPedPainaniDet.getJSONArray("tt_opPedPainaniDet");

                                globales.g_opPedidoList = Arrays.asList(new Gson().fromJson(tt_opPedido.toString(), opPedido[].class));
                                globales.g_opPedidoDetList = Arrays.asList(new Gson().fromJson(tt_opPedidoDet.toString(), opPedidoDet[].class));
                                globales.g_opPedidoProvtList = Arrays.asList(new Gson().fromJson(tt_opPedidoProveedor.toString(), opPedidoProveedor[].class));
                                globales.g_ctPedPainaniList = Arrays.asList(new Gson().fromJson(tt_opPedPainani.toString(), opPedPainani[].class));
                                globales.g_ctPedPainaniDetList = Arrays.asList(new Gson().fromJson(tt_opPedPainaniDet.toString(), opPedPainaniDet[].class));


                                //Collections.sort(globales.g_opPedidoProvtList, new ComparadorProv());

                                globales.g_opPedPainani = globales.g_ctPedPainaniList.get(0);

                                /****viepage *****/





                                final ListView lviewPedxProv = (ListView) findViewById(R.id.lvPedxProv);
                                ArrayList<opPedPainaniDet> arrayPedidoxProv = new ArrayList<opPedPainaniDet>(globales.g_ctPedPainaniDetList);
                                adapterPainaniPed = new AdapterPainaniPed( Home.this,arrayPedidoxProv );
                                lviewPedxProv.setAdapter(adapterPainaniPed);

                                lviewPedxProv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int iPartida, long l) {
                                        viPedido  = (globales.g_ctPedPainaniDetList.get(iPartida).getiPedido());
                                        viProveedor = (globales.g_ctPedPainaniDetList.get(iPartida).getiPedidoProv());
                                        viPartidaProv = (globales.g_ctPedPainaniDetList.get(iPartida).getiPartida());
                                        tvDomProv .setText(globales.g_ctPedPainaniDetList.get(iPartida).getcDirProveedor());
                                        tvDomProv .setText(globales.g_ctPedPainaniDetList.get(iPartida).getcDirProveedor());


                                        Log.e("valor pedido prov",  "partida es " + viPartidaProv);
                                        ConstruyeDet( viPedido,  viProveedor);
                                    }
                                });
                                txtDomCli.setText(globales.g_ctPedPainaniList.get(0).getcDirCliente());
                                tvRecibe.setText(globales.g_ctPedPainaniList.get(0).getcCliente());



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
                params.put("ipiPainani",globales.g_opDispPList.get(0).getiPainani().toString());
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

       /* jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

        mRequestQueue.add(jsonObjectRequest);

    }

    public void ActualizaEstadoP(final Boolean vlActivo){
        int viEstatus = 0;

        if(vlActivo == true){
            viEstatus = 1;
        }else {
            viEstatus = 2;
        }


        getmRequestQueue();

        String urlParams = String.format(url + "ActDispPainani?ipiPainani=%1$s&ipiEstado=%2$s",  globales.g_opDispPList.get(0).getiPainani(), viEstatus );

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
                                sEstatusP.setEnabled(true);
                                MuestraMensaje("Error", Mensaje);
                                return;

                            } else {

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
                params.put("ipiPainani",globales.g_opDispPList.get(0).getiPainani().toString());
                params.put("ipiEstado", "viEstatus");



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

        /*jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/


        mRequestQueue.add(jsonObjectRequest);
    }

    public void getmRequestQueue(){
        try{
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(getApplicationContext());
                //your code
            }
        }catch(Exception e){
            Log.d("Volley-->",e.toString());
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

    }

    public void CreaUbicacion(double vdeLatitud, double vdeLongitud){

        Log.e("carga coordenadas", "home crea ubicacion");

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

                            Boolean vlPedidos = respuesta.getBoolean("oplPedidos");
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
                                if(vlPedidos == true) {

                                    Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                    vibrator.vibrate(3000);

                                    globales.g_opPedPainani = globales.g_ctPedPainaniList.get(0);
                                    notificacion.setSmallIcon(R.mipmap.ic_launcher);
                                    notificacion.setTicker("Nuevo pedido");
                                    notificacion.setWhen(System.currentTimeMillis());
                                    notificacion.setContentTitle("Nuevo Pedido");
                                    notificacion.setContentText("tienes un nuevo pedido ");


                                    Intent intent = new Intent(Home.this,Home.class);

                                    PendingIntent pendingIntent = PendingIntent.getActivity(Home.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                                    notificacion.setContentIntent(pendingIntent);
                                    ///sonidos

                                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                                    nm.notify(idUnica,notificacion.build());


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
        /*jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
        mRequestQueue.add(jsonObjectRequest);

    }

    public void ConfirmaPedido() {

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
                    MuestraMensaje("Aviso", "El tiempo de respuesta se ha terminado. El pedido fue rechazado");
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

    public void ActualizaPedido(final Boolean vlAceptado){
        final ProgressDialog nDialog;
        nDialog = new ProgressDialog(getApplicationContext());
        nDialog.setMessage("Cargando...");
        nDialog.setTitle("Actualizando Pedido");
        nDialog.setIndeterminate(false);


        globales.opPedPainaniList.clear();

        opPedPainani objActualizaPed  = new opPedPainani();

        objActualizaPed.setcCvePedido  (globales.g_opPedPainani.getcCvePedido());
        objActualizaPed.setcDirCliente (globales.g_opPedPainani.getcDirCliente());
        objActualizaPed.setDtAvisado   (globales.g_opPedPainani.getDtAvisado());
        objActualizaPed.setDtContestado(globales.g_opPedPainani.getDtContestado());
        objActualizaPed.setDtFecha     (globales.g_opPedPainani.getDtFecha());
        objActualizaPed.setDtReasignado(globales.g_opPedPainani.getDtReasignado());
        objActualizaPed.setiAsignadoA  (globales.g_opPedPainani.getiAsignadoA());
        objActualizaPed.setiCliente    (globales.g_opPedPainani.getiCliente());
        objActualizaPed.setiPainani    (globales.g_opPedPainani.getiPainani());  //
        objActualizaPed.setiPedido     (globales.g_opPedPainani.getiPedido()); //
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

                                if (vlAceptado == false){
                                    startActivity(new Intent(Home.this, RazonesRechazo.class));
                                    finish();

                                }
                                /*Datos de entrega*/
                                txtDomCli.setText(globales.g_ctPedPainaniList.get(0).getcDirCliente());
                                tvRecibe.setText(globales.g_ctPedPainaniList.get(0).getcCliente());

                                /*Encabezado de pedido x Proveedor*/
                                Collections.sort(globales.g_ctPedPainaniDetList, new ComparadorProv());


                                final ListView lviewPedxProv = (ListView) findViewById(R.id.lvPedxProv);



                                ArrayList<opPedPainaniDet> arrayPedidoxProv = new ArrayList<opPedPainaniDet>(globales.g_ctPedPainaniDetList);
                                adapterPainaniPed = new AdapterPainaniPed( Home.this,arrayPedidoxProv );
                                lviewPedxProv.setAdapter(adapterPainaniPed);

                                lviewPedxProv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int iPartida, long l) {



                                        viPedido  = (globales.g_ctPedPainaniDetList.get(iPartida).getiPedido());
                                        viProveedor = (globales.g_ctPedPainaniDetList.get(iPartida).getiPedidoProv());
                                        viPartidaProv = (globales.g_ctPedPainaniDetList.get(iPartida).getiPartida());



                                        ConstruyeDet( viPedido,  viProveedor);
                                    }
                                });

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

        /*jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

        mRequestQueue.add(jsonObjectRequest);

    }

    public void ConstruyeDet(Integer viPedido, Integer viPedidoProv){


        globales.g_ctDetalleFinal.clear();
        for(opPedidoDet obj: globales.g_opPedidoDetList){

            if(obj.getiPedido().equals(viPedido) && obj.getiPedidoProv().equals(viPedidoProv)){

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

    public void ActPedPainaniDet(final String vcAccion, Integer iPedido, Integer iProvPart){
        Integer viPainani =   globales.g_ctUsuario.getiPersona();
        Log.e("variables recibidos", vcAccion + " " + iPedido + " "+ globales.g_ctUsuario.getiPersona() + " " + iProvPart);

        getmRequestQueue();
        String urlParams = String.format(url + "opPedPainaniDet?ipiPedido=%1$s&ipiPainani=%2$s&ipiPartida=%3$s&ipcAccion=%4$s", iPedido, viPainani,iProvPart, vcAccion );

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
                params.put("ipiPartida", "iProvPart");
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


       /* jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

        mRequestQueue.add(jsonObjectRequest);


    }

    public void CerrarSesion(final Boolean vlEstatus, final Boolean vlSalida){
        btnSalir.setEnabled(false);

        final ProgressDialog nDialog;
        nDialog = new ProgressDialog(Home.this);
        nDialog.setMessage("Cargando...");
        //nDialog.setTitle("Agregando Comisión");
        nDialog.setIndeterminate(false);

        /*int viEstatus = 0;

        if(vlEstatus == false){
            viEstatus = 2;
        } else{
            viEstatus = 1;
        }
        if(vlSalida == true){
            viEstatus = 2;
        }*/


        opDispPainani objComisionDispP = new opDispPainani();
        objComisionDispP.setiPainani(globales.g_opDispPList.get(0).getiPainani());
        objComisionDispP.setDtFecha(globales.g_opDispPList.get(0).getDtFecha());
        objComisionDispP.setiComision(globales.g_opDispPList.get(0).getiComision());
        objComisionDispP.setDtCheckIn(globales.g_opDispPList.get(0).getDtCheckIn());
        objComisionDispP.setiCheckIn(globales.g_opDispPList.get(0).getiCheckIn());
        objComisionDispP.setDtCheckOut(globales.g_opDispPList.get(0).getDtCheckOut());
        objComisionDispP.setiCheckOut(globales.g_opDispPList.get(0).getiCheckOut());
        objComisionDispP.setiEstadoProceso(0);
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
            jsonParams.put("iplFinal",vlSalida);
            jsonParams.put("ds_opDispPainani", jsonDataSet);

            //jsonParams.put("iplActivo", vlEstatus);

            jsonBody.put("request", jsonParams);

            Log.i("Response", jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            nDialog.dismiss();
            MuestraMensaje("Error", e.getMessage());
            btnSalir.setEnabled(true);
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




                            if (Error == true) {
                                nDialog.dismiss();
                                MuestraMensaje("Error" , Mensaje);
                                btnSalir.setEnabled(true);

                            } else {
                                if(vlSalida == true){
                                    finish();
                                    System.exit(0);
                                }else{
                                    MuestraMensaje("Alerta" , "Estatus Actualizado");
                                    btnSalir.setEnabled(true);
                                }

                            }

                        } catch (JSONException e) {
                            Log.i("Error JSONExcepcion", e.getMessage());

                            nDialog.dismiss();
                            Log.i("Error JSONExcepcion", e.getMessage());
                            MuestraMensaje("Error", "Error Conversión de Datos." + "\n " + e.getMessage());
                            btnSalir.setEnabled(true);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("Error", error.toString());
                        nDialog.dismiss();
                        MuestraMensaje("Error", error.toString());
                        btnSalir.setEnabled(true);
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

    public void TerminarPedido(){
        getmRequestQueue();

        String urlParams = String.format(url + "pedTitlaniAct?ipiUnidad=%1$s&ipiPedido=%2$s&ipcUsuario=%3$s", 1, globales.g_opPedPainani.getiPedido(), globales.g_ctUsuario.getcUsuario() );
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
                                btnFin.setEnabled(true);
                                MuestraMensaje("Error", Mensaje);
                                return;

                            } else {
                                MuestraMensaje("Aviso", "Pedido Finalizafo. Evalua Cliente");

                                startActivity(new Intent(Home.this, EvaluaCli.class));
                                finish();
                            }
                        } catch (JSONException e) {
                            btnFin.setEnabled(true);
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
                        btnFin.setEnabled(true);
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
                params.put("ipiUnidad","vdeLatitud");
                params.put("ipiPedido", "vdeLongitud");
                params.put("ipcUsuario", globales.g_ctUsuario.getcUsuario());



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

        /*jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_DEFAULT_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

        mRequestQueue.add(jsonObjectRequest);

    }


}
