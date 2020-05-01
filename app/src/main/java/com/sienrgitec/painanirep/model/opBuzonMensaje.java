package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class opBuzonMensaje implements Serializable {
    private Integer iPersona;
    private Integer iTipoPersona;
    private Integer iPedido;
    private Integer iMensaje;
    private String  cMensaje;
    private String  cMensaje2;
    private String  cObs;
    private Boolean lLeido;
    private String  dtLeido;
    private String  dtCreado;
    private String  dtModificado;
    private String  cUsuCrea;
    private String  cUsuModifica;
    private Object id;

    public Integer getiPersona() {
        return iPersona;
    }

    public void setiPersona(Integer iPersona) {
        this.iPersona = iPersona;
    }

    public Integer getiTipoPersona() {
        return iTipoPersona;
    }

    public void setiTipoPersona(Integer iTipoPersona) {
        this.iTipoPersona = iTipoPersona;
    }

    public Integer getiPedido() {
        return iPedido;
    }

    public void setiPedido(Integer iPedido) {
        this.iPedido = iPedido;
    }

    public Integer getiMensaje() {
        return iMensaje;
    }

    public void setiMensaje(Integer iMensaje) {
        this.iMensaje = iMensaje;
    }

    public String getcMensaje() {
        return cMensaje;
    }

    public void setcMensaje(String cMensaje) {
        this.cMensaje = cMensaje;
    }

    public String getcMensaje2() {
        return cMensaje2;
    }

    public void setcMensaje2(String cMensaje2) {
        this.cMensaje2 = cMensaje2;
    }

    public String getcObs() {
        return cObs;
    }

    public void setcObs(String cObs) {
        this.cObs = cObs;
    }

    public Boolean getlLeido() {
        return lLeido;
    }

    public void setlLeido(Boolean lLeido) {
        this.lLeido = lLeido;
    }

    public String getDtLeido() {
        return dtLeido;
    }

    public void setDtLeido(String dtLeido) {
        this.dtLeido = dtLeido;
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

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
