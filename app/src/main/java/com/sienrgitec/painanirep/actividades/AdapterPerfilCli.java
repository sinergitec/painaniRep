package com.sienrgitec.painanirep.actividades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.model.opPerfilCli;


import java.util.ArrayList;
import java.util.List;

public class AdapterPerfilCli extends BaseAdapter {
    private Context context;
    private ArrayList<opPerfilCli> arrayList;

    public AdapterPerfilCli(Context context, ArrayList<opPerfilCli> arrayList) {
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
            convertView = layoutInflater.inflate(R.layout.perfilclilist, null);

        }
        TextView tvNombre = (TextView) convertView.findViewById(R.id.textView6);
        TextView tvDomCli = (TextView) convertView.findViewById(R.id.textView8);
        TextView tvTotPed = (TextView) convertView.findViewById(R.id.textView9);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar3);;



        tvNombre.setText(arrayList.get(position).getcCliente());
        tvDomCli.setText(arrayList.get(position).getcDomicilio());
        tvTotPed.setText("Total Ped: " + arrayList.get(position).getiTotalPed());
        ratingBar.setRating(Float.parseFloat(String.valueOf(arrayList.get(position).getDeEvalua())));

        return convertView;
    }

    public void setList(List<opPerfilCli> list) {
        this.arrayList = arrayList;
    }
}
