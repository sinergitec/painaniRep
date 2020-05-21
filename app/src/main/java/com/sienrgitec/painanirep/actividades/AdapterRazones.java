package com.sienrgitec.painanirep.actividades;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sienrgitec.painanirep.R;

import com.sienrgitec.painanirep.model.ctRazones;

import java.util.ArrayList;

public class AdapterRazones extends BaseAdapter {
    private Context context;
    private ArrayList<ctRazones> arrayList;

    public AdapterRazones(Context context, ArrayList<ctRazones> arrayList) {
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
            convertView = layoutInflater.inflate(R.layout.lista_ctrazones, null);

        }
        TextView tvRazones = (TextView) convertView.findViewById(R.id.txtRazon);



        tvRazones.setText(arrayList.get(position).getcRazon());

        return convertView;
    }

}

