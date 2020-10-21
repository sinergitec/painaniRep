package com.sienrgitec.painanirep.model;

import java.io.Serializable;

public class opPerfilCli implements Serializable {

    private Integer iCliente;
    private String  cCliente;
    private String  cDomicilio;
    private Integer iTotalPed;
    private Double  deEvalua;
    private  Object id;

    public Integer getiCliente() {
        return iCliente;
    }

    public void setiCliente(Integer iCliente) {
        this.iCliente = iCliente;
    }

    public String getcCliente() {
        return cCliente;
    }

    public void setcCliente(String cCliente) {
        this.cCliente = cCliente;
    }

    public String getcDomicilio() {
        return cDomicilio;
    }

    public void setcDomicilio(String cDomicilio) {
        this.cDomicilio = cDomicilio;
    }

    public Integer getiTotalPed() {
        return iTotalPed;
    }

    public void setiTotalPed(Integer iTotalPed) {
        this.iTotalPed = iTotalPed;
    }

    public Double getDeEvalua() {
        return deEvalua;
    }

    public void setDeEvalua(Double deEvalua) {
        this.deEvalua = deEvalua;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
