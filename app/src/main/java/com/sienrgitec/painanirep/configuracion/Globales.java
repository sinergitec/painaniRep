package com.sienrgitec.painanirep.configuracion;

import com.sienrgitec.painanirep.model.ctContacto;
import com.sienrgitec.painanirep.model.ctDomicilio;
import com.sienrgitec.painanirep.model.ctEvaluacion;
import com.sienrgitec.painanirep.model.ctPainani;
import com.sienrgitec.painanirep.model.ctUsuario;
import com.sienrgitec.painanirep.model.opClienteEvalua;
import com.sienrgitec.painanirep.model.opPedPainani;
import com.sienrgitec.painanirep.model.opPedPainaniDet;
import com.sienrgitec.painanirep.model.opPedido;
import com.sienrgitec.painanirep.model.opPedidoDet;
import com.sienrgitec.painanirep.model.opPedidoProveedor;
import com.sienrgitec.painanirep.model.opUbicaPainani;

import java.util.ArrayList;
import java.util.List;

public class Globales {

    //public static  String  URL = "http://192.168.1.102:8083/painal/rest/painalService/";

    public static  String  URL = "http://sinergitecdemo.ddns.net:8083/painal/rest/painalService/";


    public static ctUsuario   g_ctUsuario   = null;
    public static opPedPainani g_opPedPainani   = null;

    public static List<ctUsuario>       g_ctUsuarioList       = null;
    public static List<opPedPainani>    g_ctPedPainaniList    = null;
    public static List<opPedPainaniDet> g_ctPedPainaniDetList = null;
    public static List<opPedido>        g_opPedidoList        = null;
    public static List<opPedidoDet>     g_opPedidoDetList     = null;
    public static List<opPedidoProveedor> g_opPedidoProvtList = null;
    public static List<ctEvaluacion>     g_ctEvaluacionList   = null;

    public static ArrayList<ctDomicilio> ctDomicilioList = new ArrayList<>();
    public static ArrayList<ctPainani>   ctPainaniList   = new ArrayList<>();
    public static ArrayList<ctUsuario>   ctUsuarioList   = new ArrayList<>();
    public static ArrayList<ctContacto>  ctContactoList  = new ArrayList<>();
    public static ArrayList<opUbicaPainani>  opUbicaPainaniList  = new ArrayList<>();
    public static ArrayList<opPedPainani> opPedPainaniList = new ArrayList<>();
    public static ArrayList<opPedidoDet>  g_ctDetalleFinal = new ArrayList<opPedidoDet>();
    public static ArrayList<opClienteEvalua> opClienteEvaluaList = new  ArrayList<opClienteEvalua>();
}
