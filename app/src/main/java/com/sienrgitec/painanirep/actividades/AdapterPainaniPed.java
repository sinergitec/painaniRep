/*package com.sienrgitec.painanirep.actividades;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.model.opPedPainaniDet;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterPainaniPed extends BaseAdapter {
    private Context context;
    private ArrayList<opPedPainaniDet> arrayList;


    public AdapterPainaniPed(Context context, ArrayList<opPedPainaniDet> arrayList) {
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
            convertView = layoutInflater.inflate(R.layout.lista_pedprov, null);

        }
        TextView txtDom = (TextView) convertView.findViewById(R.id.txtDom);
        TextView txtNegocio = (TextView) convertView.findViewById(R.id.txtNegocio);


        txtNegocio.setText(arrayList.get(position).getcNegocion().toString());
        txtDom.setText(arrayList.get(position).getcDirProveedor().toString());



        return convertView;
    }
}
*/