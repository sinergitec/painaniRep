package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class ctRazones implements Serializable {
    private Integer iRazon;
    private String  cTipoRazon;
    private String  cRazon;
    private Boolean lActivo;
    private String  dtCreado;
    private String  cUsuCrea;
    private String  dtModifica;
    private String  cUsuModifica;
    private Object  id;

    public Integer getiRazon() {
        return iRazon;
    }

    public void setiRazon(Integer iRazon) {
        this.iRazon = iRazon;
    }

    public String getcTipoRazon() {
        return cTipoRazon;
    }

    public void setcTipoRazon(String cTipoRazon) {
        this.cTipoRazon = cTipoRazon;
    }

    public String getcRazon() {
        return cRazon;
    }

    public void setcRazon(String cRazon) {
        this.cRazon = cRazon;
    }

    public Boolean getlActivo() {
        return lActivo;
    }

    public void setlActivo(Boolean lActivo) {
        this.lActivo = lActivo;
    }

    public String getDtCreado() {
        return dtCreado;
    }

    public void setDtCreado(String dtCreado) {
        this.dtCreado = dtCreado;
    }

    public String getcUsuCrea() {
        return cUsuCrea;
    }

    public void setcUsuCrea(String cUsuCrea) {
        this.cUsuCrea = cUsuCrea;
    }

    public String getDtModifica() {
        return dtModifica;
    }

    public void setDtModifica(String dtModifica) {
        this.dtModifica = dtModifica;
    }

    public String getcUsuModifica() {
        return cUsuModifica;
    }

    public void setcUsuModifica(String cUsuModifica) {
        this.cUsuModifica = cUsuModifica;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
