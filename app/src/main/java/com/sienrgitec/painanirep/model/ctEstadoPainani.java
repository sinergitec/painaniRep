package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class ctEstadoPainani implements Serializable {
    private String  cEdoPainani;
    private String  cUsuCrea;
    private String  cUsuModifica;
    private String  dtCreado;
    private String  dtModificado;
    private Integer iEdoPainani;
    private Boolean lActivo;
    private Object id;

    public String getcEdoPainani() {
        return cEdoPainani;
    }

    public void setcEdoPainani(String cEdoPainani) {
        this.cEdoPainani = cEdoPainani;
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

    public Integer getiEdoPainani() {
        return iEdoPainani;
    }

    public void setiEdoPainani(Integer iEdoPainani) {
        this.iEdoPainani = iEdoPainani;
    }

    public Boolean getlActivo() {
        return lActivo;
    }

    public void setlActivo(Boolean lActivo) {
        this.lActivo = lActivo;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
