package com.sienrgitec.painanirep.actividades;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.core.content.ContextCompat;



public class CreaUbicacionService extends JobIntentService  implements Thread.UncaughtExceptionHandler {



    public static final int JOB_ID = 1;

    public static void enqueueWork(final Context context, Intent work) {
        Log.e("servicio", "iniciando");

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener= new LocationListener(){
            public void onLocationChanged(Location location){


                Log.e("posision --> : ", "latitud " + location.getLatitude()  + " " +  location.getLongitude());
                Home creaUbicacion = new Home();
              //  creaUbicacion.CreaUbicacion(location.getLatitude(), location.getLongitude(), context);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            @Override
            public void onProviderEnabled(String provider){}
            @Override
            public void onProviderDisabled(String provider){}

        };
        int permissionChecks = ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,15000,0,locationListener);

        enqueueWork(context, CreaUbicacionService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        // your code
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {

    }
}
