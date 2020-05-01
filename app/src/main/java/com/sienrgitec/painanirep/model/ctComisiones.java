package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class ctComisiones implements Serializable {
    private String  cComision;
    private String  cUsuCrea;
    private String  cUsuModifica;
    private String  deValor;
    private String  dtCreado;
    private String  dtModificado;
    private Integer iComision;
    private Object id;

    public String getcComision() {
        return cComision;
    }

    public void setcComision(String cComision) {
        this.cComision = cComision;
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

    public String getDeValor() {
        return deValor;
    }

    public void setDeValor(String deValor) {
        this.deValor = deValor;
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

    public Integer getiComision() {
        return iComision;
    }

    public void setiComision(Integer iComision) {
        this.iComision = iComision;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
