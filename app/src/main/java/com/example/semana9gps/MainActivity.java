package com.example.semana9gps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LocationManager ubicacion;
    TextView longitud, latitud;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localizacion();
    }

    private void localizacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            }, 1000);
        }
        longitud = findViewById(R.id.tvLongitud);
        latitud = findViewById(R.id.tvLatitud);

        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Location location = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            Log.d("Longitud", String.valueOf(location.getLongitude()));
            Log.d("Latitud", String.valueOf(location.getLatitude()));

            longitud.setText("Longitud: " + String.valueOf(location.getLongitude()));
            latitud.setText("Latitud: " + String.valueOf(location.getLatitude()));
        } else {
            Log.d("Error", "No se pudo obtener la ubicación actual.");
        }
    }
}