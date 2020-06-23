package com.sienrgitec.painanirep.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.sienrgitec.painanirep.R;
import com.sienrgitec.painanirep.configuracion.Globales;

public class Inicio extends AppCompatActivity {

    public Globales globales;
    private final int DURACION_SPLASH = 8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Toast.makeText(Inicio.this, "BIENVENIDO" , Toast.LENGTH_SHORT).show();

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        setContentView(R.layout.activity_inicio);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(Inicio.this , Login.class);
                startActivity(intent);
                fileList();
                finish();
            }
        }, DURACION_SPLASH);


        int permissionCheck = ContextCompat.checkSelfPermission(Inicio.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }




        LocationManager locationManager = (LocationManager) Inicio.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener= new LocationListener(){
            public void onLocationChanged(Location location){
                globales.vg_deLatitud = location.getLatitude();
                globales.vg_deLongitud = location.getLongitude();

                Log.e("posision --> : ", "latitud " + globales.vg_deLatitud  + " " +  globales.vg_deLongitud);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            @Override
            public void onProviderEnabled(String provider){}
            @Override
            public void onProviderDisabled(String provider){}

        };
        int permissionChecks = ContextCompat.checkSelfPermission(Inicio.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,2000,0,locationListener);
        Toast.makeText(Inicio.this, "OBTENIENDO TU UBICACIÓN" , Toast.LENGTH_LONG).show();
    }
}
