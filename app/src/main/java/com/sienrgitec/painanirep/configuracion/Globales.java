package com.sienrgitec.painanirep.configuracion;

import com.sienrgitec.painanirep.model.ctComisiones;
import com.sienrgitec.painanirep.model.ctContacto;
import com.sienrgitec.painanirep.model.ctDomicilio;
import com.sienrgitec.painanirep.model.ctEstadoPainani;
import com.sienrgitec.painanirep.model.ctEstadoProceso;
import com.sienrgitec.painanirep.model.ctEvaluacion;
import com.sienrgitec.painanirep.model.ctPainani;
import com.sienrgitec.painanirep.model.ctRazones;
import com.sienrgitec.painanirep.model.ctUsuario;
import com.sienrgitec.painanirep.model.ctVehiculo;
import com.sienrgitec.painanirep.model.opClienteEvalua;
import com.sienrgitec.painanirep.model.opDispPainani;
import com.sienrgitec.painanirep.model.opPausaPainani;
import com.sienrgitec.painanirep.model.opPedPainani;
import com.sienrgitec.painanirep.model.opPedPainaniDet;
import com.sienrgitec.painanirep.model.opPedido;
import com.sienrgitec.painanirep.model.opPedidoDet;
import com.sienrgitec.painanirep.model.opPedidoProveedor;
import com.sienrgitec.painanirep.model.opUbicaPainani;

import java.util.ArrayList;
import java.util.List;

public class Globales {

    public static  String  URL = "http://192.168.1.102:8083/painal/rest/painalService/";
//public static  String  URL = "http://sinergitecdemo.ddns.net:8083/painal/rest/painalService/";

    public static final int MY_DEFAULT_TIMEOUT = 15000;

    public static Double vg_deLatitud, vg_deLongitud;


    public static ctUsuario   g_ctUsuario       = null;
    public static opPedPainani g_opPedPainani   = null;

    public static List<ctUsuario>       g_ctUsuarioList       = null;
    public static List<opPedPainani>    g_ctPedPainaniList    = null;
    public static List<opPedPainaniDet> g_ctPedPainaniDetList = null;
    public static List<opPedido>        g_opPedidoList        = null;
    public static List<opPedidoDet>     g_opPedidoDetList     = null;
    public static List<opPedidoProveedor> g_opPedidoProvtList = null;
    public static List<ctEvaluacion>    g_ctEvaluacionList    = null;
    public static List<ctComisiones>    g_ctComisionesList    = null;
    public static List<ctEstadoPainani> g_ctEdoPainaniList    = null;
    public static List<opDispPainani>   g_opDispPList         = null;
    public static List<ctRazones>       g_ctRazonesList       = null;
    public static List<ctEstadoProceso> g_ctEdoProcesoList    = null;
    public static List<ctVehiculo>      g_ctVehiculoList      = null;


    public static ArrayList<ctDomicilio>    ctDomicilioList     = new ArrayList<>();
    public static ArrayList<ctPainani>      ctPainaniList       = new ArrayList<>();
    public static ArrayList<ctUsuario>      ctUsuarioList       = new ArrayList<>();
    public static ArrayList<ctContacto>     ctContactoList      = new ArrayList<>();
    public static ArrayList<opUbicaPainani> opUbicaPainaniList  = new ArrayList<>();
    public static ArrayList<opPedPainani>   opPedPainaniList    = new ArrayList<>();
    public static ArrayList<opDispPainani>  opDispPainani       = new ArrayList<>();
    public static ArrayList<opPausaPainani> opPausaPainani      = new ArrayList<>();

    public static ArrayList<opClienteEvalua> opClienteEvaluaList = new ArrayList<opClienteEvalua>();
    public static ArrayList<opPedidoDet>     g_ctDetalleFinal    = new ArrayList<opPedidoDet>();
}
