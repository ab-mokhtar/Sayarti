package com.example.sayarti;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Sos extends Fragment {

    SupportMapFragment supportMapFragment;
    String Mylocalisation;
    EditText e2,e3,e4;
    Button btn;
    FusedLocationProviderClient client;
    ImageView i2;
    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sos, container, false);
        e2 = v.findViewById(R.id.matricule);
        e3= v.findViewById(R.id.autre);
        e4=v.findViewById(R.id.tel);
        btn = v.findViewById(R.id.env);
        i2 = v.findViewById(R.id.callsos);
        client = LocationServices.getFusedLocationProviderClient(Objects.requireNonNull(getActivity()));

        //spinner
        spinner = v.findViewById(R.id.type_panne);
        String [] values = {"Choisissez le type de panne","Batterie","Pneumatique","Essence","Accident","Incendie","Autre"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, values);
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
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        getCurretLocation();
        i2.setEnabled(false);


        //declaration = new Declaration();
        btn.setOnClickListener(v12 -> {
            String mat = e2.getText().toString().trim();
            String tel ="+216"+e4.getText().toString().trim();
            String type_panne = spinner.getSelectedItem().toString().trim();
            String au = e3.getText().toString().trim();
            String local = Mylocalisation;
            Calendar calendar=Calendar.getInstance();
            String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);

            // To get the correct window token, lets first get the currently focused view
            View view = getActivity().getCurrentFocus();

            // To get the window token when there is no currently focused view, we have a to create a view
            if (view == null) {
                view = new View(getActivity());
            }

            // hide the keyboard
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            if(mat.length()==0|| type_panne.equals("Choisissez le type de panne") || local.length()==0) {
                Snackbar.make(Objects.requireNonNull(getView()), "Vérifier les champs remplis ou vérifier votre connexion internet", Snackbar.LENGTH_LONG).show();
            }
            else
            {

                StringRequest request = new StringRequest(Request.Method.POST, "http://dev.goodlinks.tn/sayarti-apps/insert.php",
                        response -> {

                            if(response.equalsIgnoreCase("Data Inserted")){
                                Snackbar.make(Objects.requireNonNull(getView()), "La déclaration était bien envoyée"+"\n"+"Vous pouvez passer l'appel maintenant", Snackbar.LENGTH_SHORT).show();

                            }
                            else{
                                Snackbar.make(Objects.requireNonNull(getView()), response, Snackbar.LENGTH_LONG).show();


                            }

                        }, error -> Snackbar.make(Objects.requireNonNull(getView()), Objects.requireNonNull(error.getMessage()), Snackbar.LENGTH_LONG).show()

                ){
                    @Override
                    protected Map<String, String> getParams() {

                        Map<String,String> params = new HashMap<>();

                        params.put("matricule",mat);
                        if (type_panne.equals("Autre")){
                            params.put("type_panne",au);
                        }
                        else{
                            params.put("type_panne",type_panne);
                        }

                        params.put("localisation",local);
                        params.put("date",date);
                        params.put("tel",tel);



                        return params;
                    }
                };


                RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
                requestQueue.add(request);

            }
            i2.setEnabled(true);
            e2.getText().clear();
            e3.getText().clear();
            e4.getText().clear();
        });

        i2.setOnClickListener(v1 -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:71104555"));
            startActivity(intent);
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
            Snackbar.make(Objects.requireNonNull(getView()), "Permission refusée", Snackbar.LENGTH_LONG).show();
        }
    }

    @SuppressLint("MissingPermission")
    public void getCurretLocation() {
        LocationManager locationManager = (LocationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)|| locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))

        {client.getLastLocation().addOnCompleteListener(task -> {
            Location location = task.getResult();
            supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_sos);
            if (location!= null){
                Mylocalisation=String.valueOf(location.getLatitude() )+','+ location.getLongitude();

                supportMapFragment.getMapAsync(googleMap -> {
                    LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(mylocation).title("My Location"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

                });
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
                        Mylocalisation=String.valueOf(location1.getLatitude() )+','+ location1.getLongitude();
                        supportMapFragment.getMapAsync(googleMap -> {
                            LatLng mylocation = new LatLng(location1.getLatitude(), location1.getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(mylocation).title("My Location"));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location1.getLatitude(), location1.getLongitude()), 13));

                        });

                    }
                };
                client.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
            }
        });
        }else {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }






}