package com.sienrgitec.painanirep.configuracion;

import com.sienrgitec.painanirep.model.ctContacto;
import com.sienrgitec.painanirep.model.ctDomicilio;
import com.sienrgitec.painanirep.model.ctPainani;
import com.sienrgitec.painanirep.model.ctUsuario;
import com.sienrgitec.painanirep.model.opUbicaPainani;

import java.util.ArrayList;
import java.util.List;

public class Globales {

    public static  String  URL = "http://192.168.1.102:8083/painal/rest/painalService/";


    public static ctUsuario   g_ctUsuario   = null;

    public static List<ctUsuario>        g_ctUsuarioList  = null;


    public static ArrayList<ctDomicilio> ctDomicilioList = new ArrayList<>();
    public static ArrayList<ctPainani>   ctPainaniList   = new ArrayList<>();
    public static ArrayList<ctUsuario>   ctUsuarioList   = new ArrayList<>();
    public static ArrayList<ctContacto>  ctContactoList  = new ArrayList<>();
    public static ArrayList<opUbicaPainani>  opUbicaPainaniList  = new ArrayList<>();
}
