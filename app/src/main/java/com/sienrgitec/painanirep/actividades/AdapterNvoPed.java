package com.sienrgitec.painanirep.actividades;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.model.opPedPainaniDet;

import java.util.List;

public class AdapterNvoPed  extends PagerAdapter {

    private List<opPedPainaniDet> listapedidos;
    private LayoutInflater layoutInflater;
    private Context context;
    private Integer viProvTotal = 0, viProv = 0;
    private Button btn;


    public AdapterNvoPed(List<opPedPainaniDet> listapedidos, Context context) {
        this.listapedidos = listapedidos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listapedidos.size();
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

        viProvTotal = 0;
        for (opPedPainaniDet objPedDet:  listapedidos){
            viProvTotal = viProvTotal + 1;
        }


        TextView tvProv, tvDirProv, tvPedido, tvCantidad, tvTotalProv;
        ImageView img;

        img = view.findViewById(R.id.imageView);
        tvPedido = view.findViewById(R.id.txPedidoF);
        tvCantidad = view.findViewById(R.id.txPzaTot);
        tvProv = view.findViewById(R.id.txtNegocio);
        tvDirProv = view.findViewById(R.id.txtDomProv);
        tvTotalProv = view.findViewById(R.id.tvProvT);



        btn = view.findViewById(R.id.bLlegaP);







        tvPedido.setText("Pedido: " + listapedidos.get(position).getiPedido().toString());
        tvCantidad.setText("Tot. Art " + listapedidos.get(position).getDeTotalPiezas().toString());
        tvProv.setText(listapedidos.get(position).getcNegocion());
        tvDirProv.setText(listapedidos.get(position).getcDirProveedor());

        viProv = (listapedidos.get(position).getiPartida());

        tvTotalProv.setText("Proveedor: " + viProv + "/" + viProvTotal.toString());

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }



    public void LlegadaProv () {

        Log.e("funciones", "funciones");
    }

}
