package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class ctTipoContacto implements Serializable {

    private Object id;
    private Integer iTipoContacto;
    private String  cTipoContacto;
    private String cTelefono1;
    private String cTelefono2;
    private String cEmail;
    private String dtCreado;
    private String dtModificado;
    private String cUsuCrea;
    private String cUsuModifca;
    private Boolean lActivo;


    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Integer getiTipoContacto() {
        return iTipoContacto;
    }

    public void setiTipoContacto(Integer iTipoContacto) {
        this.iTipoContacto = iTipoContacto;
    }

    public String getcTipoContacto() {
        return cTipoContacto;
    }

    public void setcTipoContacto(String cTipoContacto) {
        this.cTipoContacto = cTipoContacto;
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

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
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

    public String getcUsuModifca() {
        return cUsuModifca;
    }

    public void setcUsuModifca(String cUsuModifca) {
        this.cUsuModifca = cUsuModifca;
    }

    public Boolean getlActivo() {
        return lActivo;
    }

    public void setlActivo(Boolean lActivo) {
        this.lActivo = lActivo;
    }
}
