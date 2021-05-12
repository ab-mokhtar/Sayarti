package com.example.sayarti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MapsFragment extends Fragment {
    FusedLocationProviderClient client;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    private final String kiosque;
    double longitude;

    public MapsFragment(String kiosque) {
        this.kiosque = kiosque;
    }

    double currentlat = 0, currentlong = 0;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_maps, container, false);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        Objects.requireNonNull(supportMapFragment).getMapAsync(googleMap -> {






        });

        return v;
    }
    private String getUrl(double latitude, double longitude, String nearbyPlace){
        StringBuilder googleUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleUrl.append("location=").append(latitude).append(",").append(longitude);
        int proxumityRadius = 10000;
        googleUrl.append("&radius=").append(proxumityRadius);
        googleUrl.append("&type=" + "gas_station");
        googleUrl.append("&keyword=").append(nearbyPlace);
        googleUrl.append("&sensor=true" );
        googleUrl.append("&key=" + "AIzaSyDvOWlQo1Tp1Q-6ZVdwxLwblIWD6wQVbQ8");
        Log.d("MapsFragment", "url = "+googleUrl.toString());

        return googleUrl.toString();

    }



    private final OnMapReadyCallback callback = new OnMapReadyCallback() {

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
            client = LocationServices.getFusedLocationProviderClient(Objects.requireNonNull(getActivity()));
            if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
            task.addOnSuccessListener(location -> {
                if (location != null){
                    currentlat=location.getLatitude();
                    currentlong=location.getLongitude();
                    supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

                    Objects.requireNonNull(supportMapFragment).getMapAsync(googleMap1 -> {

                        markmy_location(googleMap1);
                        map = googleMap1;
                        Object[] transferData = new Object[2];

                        Getnerabyplaces getnerabyplaces = new Getnerabyplaces(getContext());

                        String url = getUrl(currentlat, currentlong, kiosque);


                        transferData[0] = googleMap1;
                        transferData[1] = url;
                        getnerabyplaces.execute(transferData);
                        //Toast.makeText(getActivity()," search for : "+placeNameList[i],Toast.LENGTH_SHORT).show();
                        Snackbar.make(Objects.requireNonNull(getView()), "RECHERCHER " + kiosque, Snackbar.LENGTH_LONG).show();


                    });
                }
            });

        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

    }
    public void markmy_location(GoogleMap map){

        LatLng mylocation = new LatLng(currentlat, currentlong);
        map.addMarker(new MarkerOptions().position(mylocation).title("My Location"));
        map.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentlat,currentlong),13));
    }

}