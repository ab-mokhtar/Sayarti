package com.example.sayarti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;

public class MapsFragment extends Fragment {
    FusedLocationProviderClient client;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    double latitude, longitude;
    private int proxumityRadius = 10000;

    double currentlat = 0, currentlong = 0;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_maps, container, false);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                Button btnsearch = v.findViewById(R.id.search);
                Spinner sptype = v.findViewById(R.id.sp_type);

                String[] placeTypeList = {"atm", "banque", "restaurant"};
                String[] placeNameList = {"ATM", "BIAT", "Hospital"};
                Object transferData[]=new Object[2];
                Getnerabyplaces getnerabyplaces=new Getnerabyplaces();



                sptype.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, placeNameList));
                btnsearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        int i = sptype.getSelectedItemPosition();
                        String url = getUrl(currentlat, currentlong, placeTypeList[i]);


                        transferData[0]=googleMap;
                        transferData[1]=url;
                        getnerabyplaces.execute(transferData);
                        Toast.makeText(getActivity()," search for : "+placeNameList[i],Toast.LENGTH_SHORT).show();




                    }
                });
            }});
        return v;
    }
    private String getUrl(double latitude, double longitude, String nearbyPlace){
        StringBuilder googleUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleUrl.append("location="+ latitude + "," + longitude);
        googleUrl.append("&radius=" + proxumityRadius);
        googleUrl.append("&type=" + nearbyPlace);
        googleUrl.append("&sensor=true" );
        googleUrl.append("&key=" + "AIzaSyBZv65DPSbLu-CEusdgokmyA_k2thDDFwU");
        Log.d("MapsFragment", "url = "+googleUrl.toString());

        return googleUrl.toString();

    }



    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            client = LocationServices.getFusedLocationProviderClient(getActivity());
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Task<Location> task = client.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null){
                        currentlat=location.getLatitude();
                        currentlong=location.getLongitude();
                        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                map = googleMap;
                                LatLng mylocation = new LatLng(currentlat, currentlong);
                                googleMap.addMarker(new MarkerOptions().position(mylocation).title("My Location"));
                                googleMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentlat,currentlong),15));


                            }
                        });
                    }
                }
            });

        }
    };

    @Nullable

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

    }

}