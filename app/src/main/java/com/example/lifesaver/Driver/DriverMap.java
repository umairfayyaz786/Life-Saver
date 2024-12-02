package com.example.lifesaver.Driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifesaver.MainActivity;
import com.example.lifesaver.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;

public class DriverMap extends AppCompatActivity implements OnMapReadyCallback {

    private final int FINE_PERMISSION_CODE = 1;
    FusedLocationProviderClient fusedLocationProviderClient;
    private GoogleMap map;
    Location CurrentLocation;
    private SearchView SearchView;
    TextView completeridee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_driver_map);
        getSupportActionBar().setTitle("Map");
        CallComing();
        completeridee = findViewById(R.id.completeride);
        completeridee.setOnClickListener(v -> {
            Toast.makeText(this, "Successfully Complete your Ride..!!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(DriverMap.this , ForceTraveller.class);
            startActivity(i);
        });
        SearchView = findViewById(R.id.drivermapSearch);
        SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String location = SearchView.getQuery().toString();
                List<Address> addressList = null;

                if (location !=null){
                    Geocoder geocoder = new Geocoder(DriverMap.this);

                    try {
                        addressList = geocoder.getFromLocationName(location , 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    assert addressList != null;
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude() , address.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(location));
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng , 10));
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.driver_map);
        assert mapFragment != null;
        mapFragment.getMapAsync(DriverMap.this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        try {
            map = googleMap;
            LatLng sydney = new LatLng(CurrentLocation.getLatitude(), CurrentLocation.getLongitude());
            map.addMarker(new MarkerOptions().position(sydney).title("Pickup Location"));
            map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        } catch (Exception e){
            e.getLocalizedMessage();
        }

    }
    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this ,  new String[]{Manifest.permission.ACCESS_FINE_LOCATION} , FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            try {
                if (location != null){
                    CurrentLocation = location;
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.driver_map);
                    mapFragment.getMapAsync(DriverMap.this);
                }
            }catch (Exception exception){
                exception.getLocalizedMessage();
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == FINE_PERMISSION_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else {
                Toast.makeText(this, "Location permission is denied, please allow the permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void CallComing() {
        Dialog dialog = new Dialog(DriverMap.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.call_notification);
        Button btncancel = dialog.findViewById(R.id.cancelride);
        Button btnAccept = dialog.findViewById(R.id.Acceptride);
        btncancel.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AcceptRide.class));
                    dialog.dismiss();
        });
        btnAccept.setOnClickListener(v -> {
            dialog.dismiss();
        });
        dialog.show();
    }

}