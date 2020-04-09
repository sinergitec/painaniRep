package com.sienrgitec.painanirep.configuracion;

import com.sienrgitec.painanirep.model.ctContacto;
import com.sienrgitec.painanirep.model.ctDomicilio;
import com.sienrgitec.painanirep.model.ctPainani;
import com.sienrgitec.painanirep.model.ctUsuario;

import java.util.ArrayList;

public class Globales {

    public static  String  URL = "http://192.168.1.102:8083/painal/rest/painalService/";



    public static ArrayList<ctDomicilio> ctDomicilioList = new ArrayList<>();
    public static ArrayList<ctPainani>   ctPainaniList   = new ArrayList<>();
    public static ArrayList<ctUsuario>   ctUsuarioList   = new ArrayList<>();
    public static ArrayList<ctContacto>  ctContactoList  = new ArrayList<>();
}
