package com.sienrgitec.painanirep.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

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
import com.sienrgitec.painanirep.model.ctEstadoProceso;
import com.sienrgitec.painanirep.model.ctRazones;
import com.sienrgitec.painanirep.model.opPausaPainani;
import com.sienrgitec.painanirep.model.opPedPainaniDet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.layout.simple_list_item_1;

public class ActEdoProceso extends AppCompatActivity {

    public Globales globales;
    public Integer  viEdoProceso = 0, viRazon = 0;
    public String   vcRazon = "", url = globales.URL;
    private static RequestQueue mRequestQueue;
    private AdapterRazones adapter;

    RadioGroup mRgAllEdoProc;

    RelativeLayout rlEstadoProc;
    TableRow       trRazones;
    Button         btnActProc;
    TableLayout    tlRazones;
    EditText       etOtraRazon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_edo_proceso);


        btnActProc   = (Button) findViewById(R.id.button);
        tlRazones    = (TableLayout) findViewById(R.id.Razones);
        mRgAllEdoProc = findViewById(R.id.rbEdoProceso);
        etOtraRazon   = findViewById(R.id.etOtraRazon);

        mRgAllEdoProc.setOrientation(LinearLayout.HORIZONTAL);
        int vxMod = 45;
        int vyMod = 0;
        int vCuantosMod = 0;

        btnActProc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnActProc.setEnabled(false);
                ActualizaProceso();
            }
        });

        final ListView lviewDetPed = (ListView) findViewById(R.id.lvRazones);
        ArrayList<ctRazones> arrayRazones = new ArrayList<ctRazones>(globales.g_ctRazonesList);
        adapter = new AdapterRazones(ActEdoProceso.this,arrayRazones );
        lviewDetPed.setAdapter(adapter);

        lviewDetPed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int iPartida, long l) {
                viRazon  = (globales.g_ctRazonesList.get(iPartida).getiRazon());
                vcRazon = (globales.g_ctRazonesList.get(iPartida).getcRazon());

                Log.e("razones ",  "seleccionadas " + viRazon + " vcRazon " + vcRazon);

            }
        });


        for (final ctEstadoProceso objctEstado : globales.g_ctEdoProcesoList) {

            vCuantosMod = vCuantosMod + 1;

            RadioButton rdbtnEdo = new RadioButton(this);

            Drawable d = getResources().getDrawable(R.drawable.radiob);
            rdbtnEdo.setText(objctEstado.getcEstadoPedido());
            rdbtnEdo.setBackgroundDrawable(d);
            rdbtnEdo.setWidth(140);
            rdbtnEdo.setHeight(60);
            rdbtnEdo.setX(vxMod);
            rdbtnEdo.setY(vyMod);

            if (vCuantosMod % 4 == 0) {
                vxMod = -300;
                vyMod = vyMod + 10;
            } else {
                vxMod = vxMod + 30;

            }

            rdbtnEdo.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    viEdoProceso =  objctEstado.getiEstadoProceso();
                    MuestraRazones(viEdoProceso);
                }
            });

            mRgAllEdoProc.addView(rdbtnEdo);
        }


    }
    public void MuestraRazones(Integer viEstado){
        if (viEstado == 3 || viEstado == 4){

            tlRazones.setVisibility(View.VISIBLE);
            btnActProc.setVisibility(View.VISIBLE);


        }else{
            tlRazones.setVisibility(View.INVISIBLE);
            btnActProc.setVisibility(View.INVISIBLE);

        }
    }
    public void ActualizaProceso(){
        globales.opPausaPainani.clear();


        Log.e("home---", "Actualizando porcesos");

        final ProgressDialog nDialog;
        nDialog = new ProgressDialog(ActEdoProceso.this);
        nDialog.setMessage("Cargando...");
        nDialog.setTitle("Creando Razones");
        nDialog.setIndeterminate(false);

        String vcObservaciones = "";

        if (viRazon == 99){
            vcObservaciones = "No se especificaron razones ";
        }



        opPausaPainani ObjopPausaP = new opPausaPainani();
        ObjopPausaP.setiPainani(globales.g_ctUsuario.getiPersona());
        ObjopPausaP.setiRazon(viRazon);
        ObjopPausaP.setcTipoRazon(vcRazon);
        ObjopPausaP.setiPartida(0);
        ObjopPausaP.setDtPausa(null);
        ObjopPausaP.setcObs(vcObservaciones);
        ObjopPausaP.setDtCreado(null);
        ObjopPausaP.setDtModifca(null);
        ObjopPausaP.setcUsuCrea(globales.g_ctUsuario.getcUsuario());
        ObjopPausaP.setcUsumodifca(globales.g_ctUsuario.getcUsuario());
        ObjopPausaP.setDtFecha("");
        ObjopPausaP.setiPedido(0);
        globales.opPausaPainani.add(ObjopPausaP);


        JSONObject jsonBody = new JSONObject();
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonDataSet = new JSONObject();

        final Gson gson = new Gson();
        String JS_opPausaP = gson.toJson(
                globales.opPausaPainani,
                new TypeToken<ArrayList<opPausaPainani>>() {
                }.getType());


        try {
            JSONArray opPausaPainaniJS   = new JSONArray(JS_opPausaP);
            jsonDataSet.put("tt_opPausaPainani",  opPausaPainaniJS);
            jsonParams.put("ds_PausaP", jsonDataSet);
            jsonBody.put("request", jsonParams);

            Log.i("Response", jsonBody.toString());

        } catch (JSONException e) {
            e.printStackTrace();
            //nDialog.dismiss();
            MuestraMensaje("Error", e.getMessage());
            btnActProc.setEnabled(true);
        }

        getmRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url + "opPausaP/", jsonBody, new Response.Listener<JSONObject>() {
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
                                btnActProc.setEnabled(true);

                            } else {
                                MuestraMensaje("Aviso", "Estao de Proceso Actualizado");
                                nDialog.dismiss();
                                startActivity(new Intent(ActEdoProceso.this, Home.class));
                                finish();

                            }


                        } catch (JSONException e) {
                            Log.i("Error JSONExcepcion", e.getMessage());

                            nDialog.dismiss();
                            Log.i("Error JSONExcepcion", e.getMessage());
                            MuestraMensaje("Error", "Error Conversión de Datos." + "\n " + e.getMessage());
                            btnActProc.setEnabled(true);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("Error", error.toString());
                        nDialog.dismiss();
                        MuestraMensaje("Error", error.toString());
                        btnActProc.setEnabled(true);
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
        AlertDialog.Builder myBuild = new AlertDialog.Builder(ActEdoProceso.this);
        myBuild.setMessage(vcMensaje);
        myBuild.setTitle(Html.fromHtml("<font color ='#FF0000'>" + vcTitulo +"</font>"));
        myBuild.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                btnActProc.setEnabled(true);

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
