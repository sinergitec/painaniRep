package com.sienrgitec.painanirep.model;
import java.io.Serializable;

public class ctDomicilio implements Serializable{
    private Integer iDomicilio;
    private Integer iTipoPersona;
    private Integer iTipoDomicilio;
    private String cCalle;
    private String cNumExt;
    private String cNumInt;
    private String cColonia;
    private String cMpioDeleg;
    private String cEstado;
    private String cCP;
    private String cPais;
    private String cReferencia;
    private String cObs;
    private Boolean lActivo;
    private String dtCreado;
    private String dtModificado;
    private String cUsuCrea;
    private String cUsuModifica;
    private Object id;

    public Integer getiDomicilio() {
        return iDomicilio;
    }

    public void setiDomicilio(Integer iDomicilio) {
        this.iDomicilio = iDomicilio;
    }

    public Integer getiTipoPersona() {
        return iTipoPersona;
    }

    public void setiTipoPersona(Integer iTipoPersona) {
        this.iTipoPersona = iTipoPersona;
    }

    public Integer getiTipoDomicilio() {
        return iTipoDomicilio;
    }

    public void setiTipoDomicilio(Integer iTipoDomicilio) {
        this.iTipoDomicilio = iTipoDomicilio;
    }

    public String getcCalle() {
        return cCalle;
    }

    public void setcCalle(String cCalle) {
        this.cCalle = cCalle;
    }

    public String getcNumExt() {
        return cNumExt;
    }

    public void setcNumExt(String cNumExt) {
        this.cNumExt = cNumExt;
    }

    public String getcNumInt() {
        return cNumInt;
    }

    public void setcNumInt(String cNumInt) {
        this.cNumInt = cNumInt;
    }

    public String getcColonia() {
        return cColonia;
    }

    public void setcColonia(String cColonia) {
        this.cColonia = cColonia;
    }

    public String getcMpioDeleg() {
        return cMpioDeleg;
    }

    public void setcMpioDeleg(String cMpioDeleg) {
        this.cMpioDeleg = cMpioDeleg;
    }

    public String getcEstado() {
        return cEstado;
    }

    public void setcEstado(String cEstado) {
        this.cEstado = cEstado;
    }

    public String getcCP() {
        return cCP;
    }

    public void setcCP(String cCP) {
        this.cCP = cCP;
    }

    public String getcPais() {
        return cPais;
    }

    public void setcPais(String cPais) {
        this.cPais = cPais;
    }

    public String getcReferencia() {
        return cReferencia;
    }

    public void setcReferencia(String cReferencia) {
        this.cReferencia = cReferencia;
    }

    public String getcObs() {
        return cObs;
    }

    public void setcObs(String cObs) {
        this.cObs = cObs;
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
