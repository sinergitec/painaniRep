package com.sienrgitec.painanirep.configuracion;

import com.sienrgitec.painanirep.model.opPedPainaniDet;

import java.util.Comparator;

public class ComparadorProv implements Comparator<opPedPainaniDet> {
    @Override
    public int compare(opPedPainaniDet o1, opPedPainaniDet o2) {
        return o1.getiPartida().compareTo(o2.getiPartida());
    }
}
