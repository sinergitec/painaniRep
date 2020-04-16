package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class opPedPainaniDet implements Serializable {
    private String cDirProveedor;
    private String cNegocion;
    private String deImporte;
    private String deTotalPiezas;
    private String dtLlega;
    private String dtSale;
    private Integer iPainani;
    private Integer iPartida;
    private Integer iPedido;
    private Integer iProveedor;
    private Object id;

    public String getcDirProveedor() {
        return cDirProveedor;
    }

    public void setcDirProveedor(String cDirProveedor) {
        this.cDirProveedor = cDirProveedor;
    }

    public String getcNegocion() {
        return cNegocion;
    }

    public void setcNegocion(String cNegocion) {
        this.cNegocion = cNegocion;
    }

    public String getDeImporte() {
        return deImporte;
    }

    public void setDeImporte(String deImporte) {
        this.deImporte = deImporte;
    }

    public String getDeTotalPiezas() {
        return deTotalPiezas;
    }

    public void setDeTotalPiezas(String deTotalPiezas) {
        this.deTotalPiezas = deTotalPiezas;
    }

    public String getDtLlega() {
        return dtLlega;
    }

    public void setDtLlega(String dtLlega) {
        this.dtLlega = dtLlega;
    }

    public String getDtSale() {
        return dtSale;
    }

    public void setDtSale(String dtSale) {
        this.dtSale = dtSale;
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

    public Integer getiPedido() {
        return iPedido;
    }

    public void setiPedido(Integer iPedido) {
        this.iPedido = iPedido;
    }

    public Integer getiProveedor() {
        return iProveedor;
    }

    public void setiProveedor(Integer iProveedor) {
        this.iProveedor = iProveedor;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
