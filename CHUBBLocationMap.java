package com.example.projectmobapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CHUBBLocationMap extends AppCompatActivity implements OnMapReadyCallback {

    Button terrain, satellite, normal, hybrid, mylocation, search;
    GoogleMap map;
    LatLng home, position;
    Double latitude, longitude, x, y;
    int PLACE_AUTO = 1;

    @Override
    public void onMapReady(GoogleMap googleMap){
        map = googleMap;
        home = new LatLng(-6.198833634286951, 106.82264281999849);
        map.addMarker(new MarkerOptions().position(home).title("Kantor Pusat CHUBB General Insurance")).showInfoWindow();
        map.moveCamera(CameraUpdateFactory.newLatLng(home));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(home,16));
        map.setTrafficEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chubblocation_map);

        try{
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

        terrain = findViewById(R.id.btnTerrainMode);
        terrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });
        hybrid = findViewById(R.id.btnHybrid);
        hybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });
        satellite = findViewById(R.id.btnSatelliteMode);
        satellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        normal = findViewById(R.id.btnNormalMode);
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });
    }
}