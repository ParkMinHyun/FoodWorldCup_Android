package com.example.parkminhyun.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ResultFoodMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GPSInfo gpsInfo;

    private GoogleMap gMap;
    private Geocoder gCorder;

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_food_map);

        gpsInfo = new GPSInfo(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        // 현재 위치 이동
        LatLng currentPos = new LatLng(gpsInfo.getLatitude(),gpsInfo.getLongitude());
        gMap.addMarker(new MarkerOptions().position(currentPos).title("Marker in Sydney"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(currentPos));

        latitude = gpsInfo.getLatitude();
        longitude = gpsInfo.getLongitude();

        Geocoder gCoder = new Geocoder(getApplicationContext());
        List<Address> addr = null;
        try {
            addr = gCoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Address a = addr.get(0);
        Toast.makeText(getApplicationContext(),
                a.getSubLocality(),Toast.LENGTH_LONG).show();
//        a.getAdminArea()+" "+a.getLocality()+" "+a.getThoroughfare();

//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        gMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        gMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
