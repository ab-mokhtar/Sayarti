package com.example.sayarti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Objects;

public class charge_bornes extends Fragment {
    FusedLocationProviderClient client;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    private ArrayList<posi> data= new ArrayList<>();
    private final ArrayList<posi> data2= new ArrayList<>();

    double currentlat = 0, currentlong = 0;


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
        public void onMapReady( GoogleMap googleMap) {
            client = LocationServices.getFusedLocationProviderClient(requireActivity());
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                        map = googleMap1;
                        LatLng mylocation = new LatLng(currentlat, currentlong);
                        googleMap1.addMarker(new MarkerOptions().position(mylocation).title("My Location"));
                        data= (ArrayList<posi>) PrefConfig.readListFromPref(getContext());
                        if(data.size()>0){
                            for (int i=0;i<data.size();i++){
                                if (data.get(i).getType().equals( "charge_elec" )){
                                    data2.add(data.get(i));

                                }

                            }
                            for (int i=0;i<data2.size();i++){
                                String   name=data2.get(i).getName();
                                String tel=data2.get(i).getTel();
                                String marque=data2.get(i).getMarque();
                                LatLng location1 = new LatLng(data2.get(i).getLati(), data2.get(i).getLongi());
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(location1);
                                String info = name+"/"+tel+"/"+marque;
                                markerOptions.title(info);
                                // markerOptions.title(nameOfPlace);
                                markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.imarkerelec));
                                googleMap.addMarker(markerOptions);
                                googleMap.moveCamera(CameraUpdateFactory.newLatLng(location1));
                                googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                                    @Override
                                    public View getInfoWindow( Marker marker) {
                                        return null;
                                    }

                                    @Override
                                    public View getInfoContents( Marker marker) {
                                        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
                                        View v = getLayoutInflater().inflate(R.layout.snippet,null);
                                        TextView t1 = v.findViewById(R.id.text1);
                                        TextView t2 = v.findViewById(R.id.text22);
                                        TextView t3 = v.findViewById(R.id.text3);
                                        String info=marker.getTitle();
                                        assert info != null;
                                        if(info.contains("/")){
                                            String name =info.substring(0,info.indexOf("/"));
                                            info =  info.substring(info.indexOf("/")+1);
                                            String tel = info.substring(0,info.indexOf("/"));
                                            info =  info.substring(info.indexOf("/")+1);
                                            t1.setText(name);
                                            t2.setText(tel);
                                            t3.setText(info);}
                                        else
                                        {
                                            t1.setText(R.string.my_location);


                                        }
                                        return  v;
                                    }
                                });

                            }
                        } googleMap1.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentlat, currentlong), 10));


                    });
                }
            });

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_charge_bornes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
    private BitmapDescriptor bitmapDescriptordescriptor (Context context, int vector){
        Drawable vectorDrawable = ContextCompat.getDrawable(context,vector);
        Objects.requireNonNull(vectorDrawable).setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap=Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}