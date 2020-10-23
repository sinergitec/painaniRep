package com.sienrgitec.painanirep.actividades;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.configuracion.Globales;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Globales globales;
    private GoogleMap mMap;
    String vcDom, vcRazonS;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        Intent i = getIntent();
        vcDom =  i.getStringExtra("ipcDom");
        vcRazonS = i.getStringExtra("ipcNegocio");




    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {





        Geocoder geocoder=new Geocoder(MapsActivity.this);
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(vcDom, 1);
            if(addressList!=null){
                Address singleaddress=addressList.get(0);
                latLng =new LatLng(singleaddress.getLatitude(),singleaddress.getLongitude());
                Log.e("Home-->", " Abriendo mapas" + latLng);


                mMap = googleMap;

                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(singleaddress.getLatitude(),singleaddress.getLongitude());
                mMap.addMarker(new MarkerOptions().position(sydney).title(vcRazonS)).showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                CameraPosition camera = new CameraPosition.Builder().target(sydney)
                        .zoom(15)
                        .bearing(90)
                        .tilt(30)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));

                LatLng tuUbicacion = new LatLng(globales.vg_deLatitud,globales.vg_deLongitud);
                mMap.addMarker(new MarkerOptions().position(tuUbicacion).title("Tu ubicación"));



                LatLngBounds.Builder constructor = new LatLngBounds.Builder();

                constructor.include(sydney);
                constructor.include(tuUbicacion);

                LatLngBounds limites = constructor.build();

                int ancho = getResources().getDisplayMetrics().widthPixels;
                int alto = getResources().getDisplayMetrics().heightPixels;
                int padding = (int) (alto * 0.25); // 25% de espacio (padding) superior e inferior

                CameraUpdate centrarmarcadores = CameraUpdateFactory.newLatLngBounds(limites, ancho, alto, padding);

                mMap.animateCamera(centrarmarcadores);

            }

        }
        catch (Exception e){
            e.printStackTrace();
            return ;
        }


    }
}