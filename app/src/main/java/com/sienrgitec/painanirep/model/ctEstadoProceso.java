package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class ctEstadoProceso implements Serializable {
    private String cEstadoPedido;
    private String cUsuCrea;
    private String cUsuModifica;
    private String dtCreado;
    private String dtModificado;
    private Integer iEstadoProceso;
    private Boolean lActivo;
    private Object id;

/*modelos de ctEstadoprocesos*/
    public String getcEstadoPedido() {
        return cEstadoPedido;
    }

    public void setcEstadoPedido(String cEstadoPedido) {
        this.cEstadoPedido = cEstadoPedido;
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

    public Integer getiEstadoProceso() {
        return iEstadoProceso;
    }

    public void setiEstadoProceso(Integer iEstadoProceso) {
        this.iEstadoProceso = iEstadoProceso;
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
