package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class ctEvaluacion implements Serializable {

    private String cEvalua;
    private String cTipo;
    private String cUsuCrea;
    private String cUsuModifica;
    private String dtCreado;
    private String dtModificado;
    private Integer iEvalua;
    private Integer iPunto;
    private Boolean lActivo;
    private Object id;

    public String getcEvalua() {
        return cEvalua;
    }

    public void setcEvalua(String cEvalua) {
        this.cEvalua = cEvalua;
    }

    public String getcTipo() {
        return cTipo;
    }

    public void setcTipo(String cTipo) {
        this.cTipo = cTipo;
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

    public Integer getiEvalua() {
        return iEvalua;
    }

    public void setiEvalua(Integer iEvalua) {
        this.iEvalua = iEvalua;
    }

    public Integer getiPunto() {
        return iPunto;
    }

    public void setiPunto(Integer iPunto) {
        this.iPunto = iPunto;
    }

    public Boolean getlActivo() {
        return lActivo;
    }

    public void setlActivo(Boolean lActivo) {
        this.lActivo = lActivo;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
