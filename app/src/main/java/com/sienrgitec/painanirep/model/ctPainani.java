package com.sienrgitec.painanirep.model;
import java.io.Serializable;

public class ctPainani implements Serializable{
    private Integer iPainani;
    private String  cNombre;
    private String cApellidoPat;
    private String cApellidoMat;
    private Boolean lVehiculo;
    private String cMail;
    private String cWhattsApp;
    private String cFacebook;
    private String cTwitter;
    private String cObs;
    private String bImagen;
    private String cForo;
    private Integer iEdoPainani;
    private Integer iUnidad;
    private Double deVelProm;
    private String  dtCreado;
    private String dtModificado;
    private String cUsuCrea;
    private String cUsuModifica;





    public Integer getiUnidad() {
        return iUnidad;
    }

    public void setiUnidad(Integer iUnidad) {
        this.iUnidad = iUnidad;
    }

    public String getcApellidoMat() {
        return cApellidoMat;
    }

    public void setcApellidoMat(String cApellidoMat) {
        this.cApellidoMat = cApellidoMat;
    }



    public String getcApellidoPat() {
        return cApellidoPat;
    }

    public void setcApellidoPat(String cApellidoPat) {
        this.cApellidoPat = cApellidoPat;
    }

    public String getbImagen() {
        return bImagen;
    }

    public void setbImagen(String bImagen) {
        this.bImagen = bImagen;
    }

    private Object id;

    public Integer getiPainani() {
        return iPainani;
    }

    public String getcWhattsApp() {
        return cWhattsApp;
    }

    public void setcWhattsApp(String cWhattsApp) {
        this.cWhattsApp = cWhattsApp;
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

    public String getcNombre() {
        return cNombre;
    }

    public void setcNombre(String cNombre) {
        this.cNombre = cNombre;
    }

    public Boolean getlVehiculo() {
        return lVehiculo;
    }

    public void setlVehiculo(Boolean lVehiculo) {
        this.lVehiculo = lVehiculo;
    }

    public String getcMail() {
        return cMail;
    }

    public void setcMail(String cMail) {
        this.cMail = cMail;
    }

    public String getcFacebook() {
        return cFacebook;
    }

    public void setcFacebook(String cFacebook) {
        this.cFacebook = cFacebook;
    }

    public String getcTwitter() {
        return cTwitter;
    }

    public void setcTwitter(String cTwitter) {
        this.cTwitter = cTwitter;
    }

    public String getcObs() {
        return cObs;
    }

    public void setcObs(String cObs) {
        this.cObs = cObs;
    }

    public String getDtCreado() {
        return dtCreado;
    }

    public void setDtCreado(String dtCreado) {
        this.dtCreado = dtCreado;
    }

    public String getDtModificado() {
        return dtModificado;
    }

    public void setDtModificado(String dtModificado) {
        this.dtModificado = dtModificado;
    }

    public Integer getiEdoPainani() {
        return iEdoPainani;
    }

    public void setiEdoPainani(Integer iEdoPainani) {
        this.iEdoPainani = iEdoPainani;
    }

    public String getcUsuCrea() {
        return cUsuCrea;
    }

    public void setcUsuCrea(String cUsuCrea) {
        this.cUsuCrea = cUsuCrea;
    }

    public String getcUsuModifica() {
        return cUsuModifica;
    }

    public void setcUsuModifica(String cUsuModifica) {
        this.cUsuModifica = cUsuModifica;
    }

    public String getcForo() {
        return cForo;
    }

    public void setcForo(String cForo) {
        this.cForo = cForo;
    }
}
