package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class opDispPainani implements Serializable {
    private Double  deUltLat;
    private Double  deUltLong;
    private String  dtCheckIn;
    private String  dtCheckOut;
    private String  dtFecha;
    private Integer iCheckIn;
    private Integer iCheckOut;
    private Integer iComision;
    private Integer iEstadoProceso;
    private Integer iPainani;
    private Integer iVehiculo;
    private Object  id;


    public Integer getiVehiculo() {
        return iVehiculo;
    }

    public void setiVehiculo(Integer iVehiculo) {
        this.iVehiculo = iVehiculo;
    }

    public Double getDeUltLat() {
        return deUltLat;
    }

    public void setDeUltLat(Double deUltLat) {
        this.deUltLat = deUltLat;
    }

    public Double getDeUltLong() {
        return deUltLong;
    }

    public void setDeUltLong(Double deUltLong) {
        this.deUltLong = deUltLong;
    }

    public String getDtCheckIn() {
        return dtCheckIn;
    }

    public void setDtCheckIn(String dtCheckIn) {
        this.dtCheckIn = dtCheckIn;
    }

    public String getDtCheckOut() {
        return dtCheckOut;
    }

    public void setDtCheckOut(String dtCheckOut) {
        this.dtCheckOut = dtCheckOut;
    }

    public String getDtFecha() {
        return dtFecha;
    }

    public void setDtFecha(String dtFecha) {
        this.dtFecha = dtFecha;
    }

    public Integer getiCheckIn() {
        return iCheckIn;
    }

    public void setiCheckIn(Integer iCheckIn) {
        this.iCheckIn = iCheckIn;
    }

    public Integer getiCheckOut() {
        return iCheckOut;
    }

    public void setiCheckOut(Integer iCheckOut) {
        this.iCheckOut = iCheckOut;
    }

    public Integer getiComision() {
        return iComision;
    }

    public void setiComision(Integer iComision) {
        this.iComision = iComision;
    }

    public Integer getiEstadoProceso() {
        return iEstadoProceso;
    }

    public void setiEstadoProceso(Integer iEstadoProceso) {
        this.iEstadoProceso = iEstadoProceso;
    }

    public Integer getiPainani() {
        return iPainani;
    }

    public void setiPainani(Integer iPainani) {
        this.iPainani = iPainani;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
