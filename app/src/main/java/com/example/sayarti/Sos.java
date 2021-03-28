package com.example.sayarti;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Sos extends Fragment {

   EditText e1,e2,e3;
   ImageView i1;
   Button btn;
   FusedLocationProviderClient client;
   DatabaseReference db;
   ImageView i2;
   Spinner spinner;
   TextView typepanne;
   int maxid = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sos, container, false);
        e1 = v.findViewById(R.id.local);
        e2 = v.findViewById(R.id.matricule);
        e3= v.findViewById(R.id.autre);
        btn = v.findViewById(R.id.env);
        i1 = v.findViewById(R.id.btnlocal);
        i2 = v.findViewById(R.id.callsos);
        Declaration declaration;
        client = LocationServices.getFusedLocationProviderClient(getActivity());

        //spinner
        spinner = v.findViewById(R.id.type_panne);
        typepanne = v.findViewById(R.id.selected);
        String [] values = {"Choisissez le type de panne","AAA","BBB","CCC","DDD","EEE","Autre"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        e3.setVisibility(View.INVISIBLE);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("Autre"))
                {
                    e3.setVisibility(View.VISIBLE);
                }
                else
                {
                    e3.setVisibility(View.GONE);
                    e3.setText(adapterView.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

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
        declaration = new Declaration();
        db = FirebaseDatabase.getInstance("https://sayarti-122d7-default-rtdb.firebaseio.com/").getReference().child("declarations");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mat = e2.getText().toString().trim();
                String type_panne = spinner.getSelectedItem().toString().trim();
                String au = e3.getText().toString().trim();
                String local = e1.getText().toString().trim();
                if(mat.length()==0|| type_panne.equals("Choisissez le type de panne") || local.length()==0) {
                    Toast.makeText(getContext(), "vérifier que les champs rempli ou vérifier votre correction internet", Toast.LENGTH_SHORT).show();
                }
                else{
                    declaration.setMatricule(e2.getText().toString().trim());
                    declaration.setType_panne(spinner.getSelectedItem().toString().trim());
                    declaration.setLocalisation(e1.getText().toString().trim());
                    declaration.setType_panne(e3.getText().toString().trim());
                    declaration.setEtat(false);
                    db.push().setValue(declaration);
                    Toast.makeText(getContext(), "data insert sucessfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:71200300"));
                startActivity(intent);
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
                                                                            e1.setText(String.valueOf(location1.getLatitude() )+','+String.valueOf(location1.getLongitude() ));
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