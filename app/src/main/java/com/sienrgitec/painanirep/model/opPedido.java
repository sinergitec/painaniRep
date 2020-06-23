package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class opPedido implements Serializable {
    private String  cCvePedido;
    private String  cUsuCrea;
    private String  cUsuModifica;
    private Double  deComision;
    private Double  deDescuento;
    private Double  deImporte;
    private Double  deImpuesto;
    private Double  dePorcComision;
    private Double  dePorcPropina;
    private Double  dePropina;
    private Double  deSubTotal;
    private Double  deTotalPiezas;
    private Double  deTiempoLlega;
    private Double  deTRecorrido;
    private Double  dePorcPago;
    private Double  deCargoPago;
    private String  dtAsignado;
    private String  dtCancelado;
    private String  dtCreado;
    private String  dtEntregado;
    private String  dtFecha;
    private String  dtLLegaCte;
    private String  dtModificado;
    private String  dtReasignado;
    private String  dtRegistrado;
    private Integer iCliente;
    private Integer iEstadoPedido;
    private Integer iNegocios;
    private Integer iPedido;
    private Integer iRazon;
    private Integer iUnidad;
    private Object id;


    public Double getDePorcPago() {
        return dePorcPago;
    }

    public void setDePorcPago(Double dePorcPago) {
        this.dePorcPago = dePorcPago;
    }

    public Double getDeCargoPago() {
        return deCargoPago;
    }

    public void setDeCargoPago(Double deCargoPago) {
        this.deCargoPago = deCargoPago;
    }

    public Double getDeTiempoLlega() {
        return deTiempoLlega;
    }

    public void setDeTiempoLlega(Double deTiempoLlega) {
        this.deTiempoLlega = deTiempoLlega;
    }

    public Double getDeTRecorrido() {
        return deTRecorrido;
    }

    public void setDeTRecorrido(Double deTRecorrido) {
        this.deTRecorrido = deTRecorrido;
    }

    public String getcCvePedido() {
        return cCvePedido;
    }

    public void setcCvePedido(String cCvePedido) {
        this.cCvePedido = cCvePedido;
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

    public Double getDeComision() {
        return deComision;
    }

    public void setDeComision(Double deComision) {
        this.deComision = deComision;
    }

    public Double getDeDescuento() {
        return deDescuento;
    }

    public void setDeDescuento(Double deDescuento) {
        this.deDescuento = deDescuento;
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

    public Double getDePorcComision() {
        return dePorcComision;
    }

    public void setDePorcComision(Double dePorcComision) {
        this.dePorcComision = dePorcComision;
    }

    public Double getDePorcPropina() {
        return dePorcPropina;
    }

    public void setDePorcPropina(Double dePorcPropina) {
        this.dePorcPropina = dePorcPropina;
    }

    public Double getDePropina() {
        return dePropina;
    }

    public void setDePropina(Double dePropina) {
        this.dePropina = dePropina;
    }

    public Double getDeSubTotal() {
        return deSubTotal;
    }

    public void setDeSubTotal(Double deSubTotal) {
        this.deSubTotal = deSubTotal;
    }

    public Double getDeTotalPiezas() {
        return deTotalPiezas;
    }

    public void setDeTotalPiezas(Double deTotalPiezas) {
        this.deTotalPiezas = deTotalPiezas;
    }

    public String getDtAsignado() {
        return dtAsignado;
    }

    public void setDtAsignado(String dtAsignado) {
        this.dtAsignado = dtAsignado;
    }

    public String getDtCancelado() {
        return dtCancelado;
    }

    public void setDtCancelado(String dtCancelado) {
        this.dtCancelado = dtCancelado;
    }

    public String getDtCreado() {
        return dtCreado;
    }

    public void setDtCreado(String dtCreado) {
        this.dtCreado = dtCreado;
    }

    public String getDtEntregado() {
        return dtEntregado;
    }

    public void setDtEntregado(String dtEntregado) {
        this.dtEntregado = dtEntregado;
    }

    public String getDtFecha() {
        return dtFecha;
    }

    public void setDtFecha(String dtFecha) {
        this.dtFecha = dtFecha;
    }

    public String getDtLLegaCte() {
        return dtLLegaCte;
    }

    public void setDtLLegaCte(String dtLLegaCte) {
        this.dtLLegaCte = dtLLegaCte;
    }

    public String getDtModificado() {
        return dtModificado;
    }

    public void setDtModificado(String dtModificado) {
        this.dtModificado = dtModificado;
    }

    public String getDtReasignado() {
        return dtReasignado;
    }

    public void setDtReasignado(String dtReasignado) {
        this.dtReasignado = dtReasignado;
    }

    public String getDtRegistrado() {
        return dtRegistrado;
    }

    public void setDtRegistrado(String dtRegistrado) {
        this.dtRegistrado = dtRegistrado;
    }

    public Integer getiCliente() {
        return iCliente;
    }

    public void setiCliente(Integer iCliente) {
        this.iCliente = iCliente;
    }

    public Integer getiEstadoPedido() {
        return iEstadoPedido;
    }

    public void setiEstadoPedido(Integer iEstadoPedido) {
        this.iEstadoPedido = iEstadoPedido;
    }

    public Integer getiNegocios() {
        return iNegocios;
    }

    public void setiNegocios(Integer iNegocios) {
        this.iNegocios = iNegocios;
    }

    public Integer getiPedido() {
        return iPedido;
    }

    public void setiPedido(Integer iPedido) {
        this.iPedido = iPedido;
    }

    public Integer getiRazon() {
        return iRazon;
    }

    public void setiRazon(Integer iRazon) {
        this.iRazon = iRazon;
    }

    public Integer getiUnidad() {
        return iUnidad;
    }

    public void setiUnidad(Integer iUnidad) {
        this.iUnidad = iUnidad;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
