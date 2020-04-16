package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class opPedidoProveedor implements Serializable {
    private String cUsuCrea;
    private String cUsuModifica;
    private Double deImporte;
    private Double deImpuesto;
    private Double deSubTotal;
    private Double deTotalPzas;
    private String dtAvisado;
    private String dtContestado;
    private String dtCreado;
    private String dtFecha;
    private String dtModificado;
    private String dtPagado;
    private Integer iHora;
    private Integer iPedido;
    private Integer iPedidoProv;
    private Integer iProveedor;
    private Boolean lContestado;
    private Boolean lPagado;
    private Object id;


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

    public Double getDeImporte() {
        return deImporte;
    }

    public void setDeImporte(Double deImporte) {
        this.deImporte = deImporte;
    }

    public Double getDeImpuesto() {
        return deImpuesto;
    }

    public void setDeImpuesto(Double deImpuesto) {
        this.deImpuesto = deImpuesto;
    }

    public Double getDeSubTotal() {
        return deSubTotal;
    }

    public void setDeSubTotal(Double deSubTotal) {
        this.deSubTotal = deSubTotal;
    }

    public Double getDeTotalPzas() {
        return deTotalPzas;
    }

    public void setDeTotalPzas(Double deTotalPzas) {
        this.deTotalPzas = deTotalPzas;
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

    public String getDtCreado() {
        return dtCreado;
    }

    public void setDtCreado(String dtCreado) {
        this.dtCreado = dtCreado;
    }

    public String getDtFecha() {
        return dtFecha;
    }

    public void setDtFecha(String dtFecha) {
        this.dtFecha = dtFecha;
    }

    public String getDtModificado() {
        return dtModificado;
    }

    public void setDtModificado(String dtModificado) {
        this.dtModificado = dtModificado;
    }

    public String getDtPagado() {
        return dtPagado;
    }

    public void setDtPagado(String dtPagado) {
        this.dtPagado = dtPagado;
    }

    public Integer getiHora() {
        return iHora;
    }

    public void setiHora(Integer iHora) {
        this.iHora = iHora;
    }

    public Integer getiPedido() {
        return iPedido;
    }

    public void setiPedido(Integer iPedido) {
        this.iPedido = iPedido;
    }

    public Integer getiPedidoProv() {
        return iPedidoProv;
    }

    public void setiPedidoProv(Integer iPedidoProv) {
        this.iPedidoProv = iPedidoProv;
    }

    public Integer getiProveedor() {
        return iProveedor;
    }

    public void setiProveedor(Integer iProveedor) {
        this.iProveedor = iProveedor;
    }

    public Boolean getlContestado() {
        return lContestado;
    }

    public void setlContestado(Boolean lContestado) {
        this.lContestado = lContestado;
    }

    public Boolean getlPagado() {
        return lPagado;
    }

    public void setlPagado(Boolean lPagado) {
        this.lPagado = lPagado;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
