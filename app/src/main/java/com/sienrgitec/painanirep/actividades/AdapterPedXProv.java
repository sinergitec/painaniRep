package com.sienrgitec.painanirep.actividades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.configuracion.Globales;
import com.sienrgitec.painanirep.model.opPedPainaniDet;
import com.sienrgitec.painanirep.model.opPedidoProveedor;

import java.util.ArrayList;

public class AdapterPedXProv extends BaseAdapter {
    private Globales globales;
    private Context context;
    private ArrayList<opPedidoProveedor> arrayList;


    public AdapterPedXProv(Context context, ArrayList<opPedidoProveedor> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.lista_pedxprov, null);

        }


        TextView txPedProv   = (TextView) convertView.findViewById(R.id.txPedProv);
        TextView txPzaTot    = (TextView) convertView.findViewById(R.id.txPzaTot);
        TextView txtNegocio  = (TextView) convertView.findViewById(R.id.txtNegocio);
        TextView txtDomProv  = (TextView) convertView.findViewById(R.id.txtDomProv);

        txPedProv.setText(arrayList.get(position).getiPedidoProv().toString());
        txPzaTot.setText(arrayList.get(position).getDeTotalPzas().toString());

        for(opPedPainaniDet  objPedPinaniDet : globales.g_ctPedPainaniDetList){
            if(objPedPinaniDet.getiProveedor().equals(arrayList.get(position).getiProveedor())){
                txtNegocio.setText(objPedPinaniDet.getcNegocion());
                txtDomProv.setText(objPedPinaniDet.getcDirProveedor());
            };
        }


        return convertView;
    }

}
