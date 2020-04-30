package com.sienrgitec.painanirep.actividades;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.model.ctEvaluacion;

import java.util.ArrayList;

public class AdapterEvaluacion extends BaseAdapter {
    private Context context;
    private ArrayList<ctEvaluacion> arrayList;

    public AdapterEvaluacion(Context context, ArrayList<ctEvaluacion> arrayList) {
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
            convertView = layoutInflater.inflate(R.layout.lista_ctevaluacion, null);

        }
        TextView tvEvalua = (TextView) convertView.findViewById(R.id.txtcDesc);



        tvEvalua.setText(arrayList.get(position).getcEvalua());

        return convertView;
    }

}
