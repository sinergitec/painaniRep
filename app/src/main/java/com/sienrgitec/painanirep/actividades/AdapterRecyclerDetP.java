package com.sienrgitec.painanirep.actividades;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.model.opPedidoDet;

import java.util.ArrayList;

public class AdapterRecyclerDetP extends RecyclerView.Adapter<AdapterRecyclerDetP.ViewHolder> {
    ArrayList<opPedidoDet> detallePedList;

    public AdapterRecyclerDetP(ArrayList<opPedidoDet> detallePedList) {
        this.detallePedList = detallePedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_detalleped,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.asignaDatos(detallePedList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.detallePedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView cantidad, descripcion, obs;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cantidad = (TextView) itemView.findViewById(R.id.tvCantidad);
            descripcion  = (TextView) itemView.findViewById(R.id.tvArt);
            obs = (TextView) itemView.findViewById(R.id.tvArt);

        }

        public void asignaDatos(opPedidoDet opPedidoDet) {
            cantidad.setText(opPedidoDet.getDeCantidad().toString());
            descripcion.setText(opPedidoDet.getcDescripcion());
            obs.setText(opPedidoDet.getcObs());
        }
    }
}
