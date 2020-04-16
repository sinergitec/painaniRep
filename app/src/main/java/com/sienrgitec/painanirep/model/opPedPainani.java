package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class opPedPainani implements Serializable {
    private String  cCvePedido;
    private String  cDirCliente;
    private String  dtAvisado;
    private String  dtContestado;
    private String  dtFecha;
    private String  dtReasignado;
    private Integer iAsignadoA;
    private Integer iCliente;
    private Integer iPainani;
    private Integer iPedido;
    private Boolean lAceptado;
    private Boolean lContestado;
    private Boolean lReasignado;
    private Object id;

    public String getcCvePedido() {
        return cCvePedido;
    }

    public void setcCvePedido(String cCvePedido) {
        this.cCvePedido = cCvePedido;
    }

    public String getcDirCliente() {
        return cDirCliente;
    }

    public void setcDirCliente(String cDirCliente) {
        this.cDirCliente = cDirCliente;
    }

    public String getDtAvisado() {
        return dtAvisado;
    }

    public void setDtAvisado(String dtAvisado) {
        this.dtAvisado = dtAvisado;
    }

    public String getDtContestado() {
        return dtContestado;
    }

    public void setDtContestado(String dtContestado) {
        this.dtContestado = dtContestado;
    }

    public String getDtFecha() {
        return dtFecha;
    }

    public void setDtFecha(String dtFecha) {
        this.dtFecha = dtFecha;
    }

    public String getDtReasignado() {
        return dtReasignado;
    }

    public void setDtReasignado(String dtReasignado) {
        this.dtReasignado = dtReasignado;
    }

    public Integer getiAsignadoA() {
        return iAsignadoA;
    }

    public void setiAsignadoA(Integer iAsignadoA) {
        this.iAsignadoA = iAsignadoA;
    }

    public Integer getiCliente() {
        return iCliente;
    }

    public void setiCliente(Integer iCliente) {
        this.iCliente = iCliente;
    }

    public Integer getiPainani() {
        return iPainani;
    }

    public void setiPainani(Integer iPainani) {
        this.iPainani = iPainani;
    }

    public Integer getiPedido() {
        return iPedido;
    }

    public void setiPedido(Integer iPedido) {
        this.iPedido = iPedido;
    }

    public Boolean getlAceptado() {
        return lAceptado;
    }

    public void setlAceptado(Boolean lAceptado) {
        this.lAceptado = lAceptado;
    }

    public Boolean getlContestado() {
        return lContestado;
    }

    public void setlContestado(Boolean lContestado) {
        this.lContestado = lContestado;
    }

    public Boolean getlReasignado() {
        return lReasignado;
    }

    public void setlReasignado(Boolean lReasignado) {
        this.lReasignado = lReasignado;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }




}
