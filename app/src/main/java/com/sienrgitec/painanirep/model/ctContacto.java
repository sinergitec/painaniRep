package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class ctContacto implements Serializable {

    private Integer iPersona;
    private Integer iContacto;
    private Integer iTipoPersona;
    private Integer iTipoContacto;
    private String  cNombre;
    private String  cApellidos;
    private String  cObs;
    private Boolean lActivo;
    private String  dtCreado;
    private String  dtModificado;
    private String  cUsuCrea;
    private String  cUsuModifica;
    private String cEmail;
    private String cTelefono1;
    private String cTelefono2;
    private Object  id;

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getcTelefono1() {
        return cTelefono1;
    }

    public void setcTelefono1(String cTelefono1) {
        this.cTelefono1 = cTelefono1;
    }

    public String getcTelefono2() {
        return cTelefono2;
    }

    public void setcTelefono2(String cTelefono2) {
        this.cTelefono2 = cTelefono2;
    }



    public Integer getiContacto() {
        return iContacto;
    }

    public void setiContacto(Integer iContacto) {
        this.iContacto = iContacto;
    }

    public Integer getiPersona() {
        return iPersona;
    }

    public void setiPersona(Integer iPersona) {
        this.iPersona = iPersona;
    }

    public Integer getiTipoContacto() {
        return iTipoContacto;
    }

    public void setiTipoContacto(Integer iTipoContacto) {
        this.iTipoContacto = iTipoContacto;
    }

    public String getcNombre() {
        return cNombre;
    }

    public void setcNombre(String cNombre) {
        this.cNombre = cNombre;
    }

    public String getcApellidos() {
        return cApellidos;
    }

    public void setcApellidos(String cApellidos) {
        this.cApellidos = cApellidos;
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

    public Integer getiTipoPersona() {
        return iTipoPersona;
    }

    public void setiTipoPersona(Integer iTipoPersona) {
        this.iTipoPersona = iTipoPersona;
    }
}
