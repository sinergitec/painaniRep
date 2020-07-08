package com.sienrgitec.painanirep.actividades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.android.volley.Response;
import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.model.opPedPainaniDet;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewPedDet extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<opPedPainaniDet> arrayList;

    public AdapterViewPedDet(Context context, ArrayList<opPedPainaniDet> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public AdapterViewPedDet(Response.Listener<JSONObject> jsonObjectListener, List<opPedPainaniDet> models) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);


        TextView tvProv, tvDirProv;

        tvProv = view.findViewById(R.id.txtNegocio);
        tvDirProv = view.findViewById(R.id.txtDomProv);

        tvProv.setText(arrayList.get(position).getcNegocion());
        tvDirProv.setText(arrayList.get(position).getcDirProveedor());

        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
