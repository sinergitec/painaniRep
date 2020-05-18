package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class opPausaPainani implements Serializable {
    private Integer iPainani;
    private Integer iRazon;
    private String cTipoRazon;
    private Integer iPartida;
    private String dtPausa;
    private String cObs;
    private String dtCreado;
    private String cUsuCrea;
    private String dtModifca;
    private String cUsuModifca;
    private String dtFecha;
    private Object id;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }


    public Integer getiPainani() {
        return iPainani;
    }

    public void setiPainani(Integer iPainani) {
        this.iPainani = iPainani;
    }

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

    public Integer getiPartida() {
        return iPartida;
    }

    public void setiPartida(Integer iPartida) {
        this.iPartida = iPartida;
    }

    public String getDtPausa() {
        return dtPausa;
    }

    public void setDtPausa(String dtPausa) {
        this.dtPausa = dtPausa;
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

    public String getcUsuCrea() {
        return cUsuCrea;
    }

    public void setcUsuCrea(String cUsuCrea) {
        this.cUsuCrea = cUsuCrea;
    }

    public String getDtModifca() {
        return dtModifca;
    }

    public void setDtModifca(String dtModifca) {
        this.dtModifca = dtModifca;
    }

    public String getcUsuModifca() {
        return cUsuModifca;
    }

    public void setcUsuModifca(String cUsuModifca) {
        this.cUsuModifca = cUsuModifca;
    }

    public String getDtFecha() {
        return dtFecha;
    }

    public void setDtFecha(String dtFecha) {
        this.dtFecha = dtFecha;
    }
}
