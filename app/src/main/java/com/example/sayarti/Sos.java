package com.example.sayarti;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class Sos extends Fragment {

   EditText e1;
   ImageView i1;
   FusedLocationProviderClient client;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sos, container, false);
        e1 = v.findViewById(R.id.local);
        i1 = v.findViewById(R.id.btnlocal);
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity()
                        , Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    getCurretLocation();
                }else{
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
                }

            }
        });
    return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && (grantResults.length>0)&&(grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED)){
            getCurretLocation();
        }
        else{
            Toast.makeText(getActivity(),"Permission denied",Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurretLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)|| locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))

        {client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Location> task) {
                                                                Location location = task.getResult();
                                                                if (location!= null){
                                                                    e1.setText(String.valueOf(location.getLatitude() )+','+String.valueOf(location.getLongitude() ));
                                                                }
                                                                else
                                                                {
                                                                    LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                                                            .setInterval(10000)
                                                                            .setFastestInterval(1000)
                                                                            .setNumUpdates(1);
                                                                    LocationCallback locationCallback = new LocationCallback() {
                                                                        @Override
                                                                        public void onLocationResult(@NonNull LocationResult locationResult) {
                                                                            Location location1 = locationResult.getLastLocation();
                                                                            e1.setText(String.valueOf(location.getLatitude() )+','+String.valueOf(location.getLongitude() ));
                                                                        }
                                                                    };
                                                                    client.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
                                                                }
                                                            }
                                                            });
                                                        }else {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }
}