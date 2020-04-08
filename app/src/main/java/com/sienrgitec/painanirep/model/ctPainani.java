package com.sienrgitec.painanirep.model;
import java.io.Serializable;

public class ctPainani implements Serializable{
    private Integer iPainani;
    private String cNombre;
    private String cApellidoP;
    private String cApellidoM;
    private Boolean lVehiculo;
    private String cMail;
    private String cWhatsApp;
    private String cFacebook;
    private String cTwitter;
    private String cObs;
    private String dtCreado;
    private String dtModificado;
    private Integer iEdoPainani;
    private String cUsuCrea;
    private String cUsuModifica;
    private Object id;

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

    public String getcNombre() {
        return cNombre;
    }

    public void setcNombre(String cNombre) {
        this.cNombre = cNombre;
    }

    public String getcApellidoP() {
        return cApellidoP;
    }

    public void setcApellidoP(String cApellidoP) {
        this.cApellidoP = cApellidoP;
    }

    public String getcApellidoM() {
        return cApellidoM;
    }

    public void setcApellidoM(String cApellidoM) {
        this.cApellidoM = cApellidoM;
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

    public String getcWhatsApp() {
        return cWhatsApp;
    }

    public void setcWhatsApp(String cWhatsApp) {
        this.cWhatsApp = cWhatsApp;
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


}
