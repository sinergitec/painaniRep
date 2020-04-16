package com.sienrgitec.painanirep.actividades;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.model.opPedidoDet;

import java.text.DecimalFormat;
import java.util.ArrayList;

class AdapterHome extends BaseAdapter {

    private Context context;
    private ArrayList<opPedidoDet>arrayList;

    public AdapterHome(Context context, ArrayList<opPedidoDet> arrayList) {
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
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.lista_pedidedet, null);

        }
        TextView txtArticulo = (TextView) convertView.findViewById(R.id.txtArticulo);
        TextView txtCantidad = (TextView) convertView.findViewById(R.id.txtCantidad);


        txtArticulo.setText(arrayList.get(position).getcDescripcion().toString());

        String cantArtString = new DecimalFormat("0.00").format(arrayList.get(position).getDeCantidad());
        SpannableString vdeCantArt = new SpannableString(cantArtString);
        txtCantidad.setText(vdeCantArt);









        return convertView;
    }
}
