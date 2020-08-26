package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class ctVehiculo implements Serializable {

    private Object id;
    private Integer iVehiculo;
    private Integer iPainani;
    private Integer iTipoVehiculo;
    private Boolean lPropio;
    private Boolean lLicencia;
    private Boolean lActivo;
    private String  cLicencia;
    private String  cInfoVehiculo;
    private Double  dePesoTotal;
    private Double  deValorTotal;
    private String  dtCreado;
    private String  dtModificado;
    private String  cUsuCrea;
    private String  cUsuModifica;
    private String  cVehiculo;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Integer getiVehiculo() {
        return iVehiculo;
    }

    public void setiVehiculo(Integer iVehiculo) {
        this.iVehiculo = iVehiculo;
    }

    public Integer getiPainani() {
        return iPainani;
    }

    public void setiPainani(Integer iPainani) {
        this.iPainani = iPainani;
    }

    public Integer getiTipoVehiculo() {
        return iTipoVehiculo;
    }

    public void setiTipoVehiculo(Integer iTipoVehiculo) {
        this.iTipoVehiculo = iTipoVehiculo;
    }

    public Boolean getlPropio() {
        return lPropio;
    }

    public void setlPropio(Boolean lPropio) {
        this.lPropio = lPropio;
    }

    public Boolean getlLicencia() {
        return lLicencia;
    }

    public void setlLicencia(Boolean lLicencia) {
        this.lLicencia = lLicencia;
    }

    public Boolean getlActivo() {
        return lActivo;
    }

    public void setlActivo(Boolean lActivo) {
        this.lActivo = lActivo;
    }

    public String getcLicencia() {
        return cLicencia;
    }

    public void setcLicencia(String cLicencia) {
        this.cLicencia = cLicencia;
    }

    public String getcInfoVehiculo() {
        return cInfoVehiculo;
    }

    public void setcInfoVehiculo(String cInfoVehiculo) {
        this.cInfoVehiculo = cInfoVehiculo;
    }

    public Double getDePesoTotal() {
        return dePesoTotal;
    }

    public void setDePesoTotal(Double dePesoTotal) {
        this.dePesoTotal = dePesoTotal;
    }

    public Double getDeValorTotal() {
        return deValorTotal;
    }

    public void setDeValorTotal(Double deValorTotal) {
        this.deValorTotal = deValorTotal;
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

    public String getcVehiculo() {
        return cVehiculo;
    }

    public void setcVehiculo(String cVehiculo) {
        this.cVehiculo = cVehiculo;
    }
}
