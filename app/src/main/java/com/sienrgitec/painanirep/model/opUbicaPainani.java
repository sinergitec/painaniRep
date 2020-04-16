package com.sienrgitec.painanirep.model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class opUbicaPainani implements Serializable {
    private Double   deLatitud;
    private Double   deLongitud;
    private String   dtFecha;
    private String   dtFHora;
    private Integer  iHora;
    private Integer  iPainani;
    private Integer  iPartida;
    private Object   id;



    public Double getDeLatitud() {
        return deLatitud;
    }

    public void setDeLatitud(Double deLatitud) {
        this.deLatitud = (deLatitud) ;
    }

    public Double getDeLongitud() {
        return deLongitud;
    }

    public void setDeLongitud(Double deLongitud) {
        this.deLongitud = deLongitud;
    }

    public String getDtFecha() {
        return dtFecha;
    }

    public void setDtFecha(String dtFecha) {
        this.dtFecha = dtFecha;
    }

    public String getDtFHora() {
        return dtFHora;
    }

    public void setDtFHora(String dtFHora) {
        this.dtFHora = dtFHora;
    }

    public Integer getiHora() {
        return iHora;
    }

    public void setiHora(Integer iHora) {
        this.iHora = iHora;
    }

    public Integer getiPainani() {
        return iPainani;
    }

    public void setiPainani(Integer iPainani) {
        this.iPainani = iPainani;
    }

    public Integer getiPartida() {
        return iPartida;
    }

    public void setiPartida(Integer iPartida) {
        this.iPartida = iPartida;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
