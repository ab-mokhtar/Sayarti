package com.example.sayarti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
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

public class assurmap extends Fragment {

    FusedLocationProviderClient client;
    SupportMapFragment supportMapFragment;
    private final String marques;
    double currentlat = 0, currentlong = 0;
    private final ArrayList<posi> data2= new ArrayList<>();


    public assurmap(String marques) {
        this.marques = marques;
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

                    Objects.requireNonNull(supportMapFragment).getMapAsync(googleMap1 -> markmy_location(googleMap1));
                }
            });


            ArrayList<posi> data = (ArrayList<posi>) PrefConfig.readListFromPref(getContext());
            if(data.size()>0){
                for (int i = 0; i< data.size(); i++){
                    if (data.get(i).getMarque().equals( marques )){
                        data2.add(data.get(i));

                    }

                }
                for (int i=0;i<data2.size();i++){
                    String   name=data2.get(i).getName();
                    String tel=data2.get(i).getTel();
                    String marque=data2.get(i).getMarque();
                    LatLng location = new LatLng(data2.get(i).getLati(), data2.get(i).getLongi());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(location);
                    String info = name+"/"+tel+"/"+marque;
                    markerOptions.title(info);
                    // markerOptions.title(nameOfPlace);
                    switch (marques)
                    {
                        case "bh":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_bh));
                            break;
                        case "comar":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_comar));
                            break;
                        case "ami":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_ami));
                            break;
                        case "biat":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_biat));
                            break;
                        case  "lloyd":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_lloyd));
                            break;
                        case  "gat":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_gat));
                            break;
                        case "star":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.star));
                            break;
                        case"hayet":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_hayet));
                            break;
                        case "carte":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_carte));
                            break;
                        case "astree":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_astree));
                            break;
                        case "maghrebia":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_maghrebia));
                            break;
                        case "ctama":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_ctama));
                            break;
                        case "mae":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_mae));
                            break;
                        case "attakafulia":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_attakafulia));
                            break;
                        case "zitouna":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_zitouna));
                            break;
                        case "attijari":
                            markerOptions.icon(bitmapDescriptordescriptor(getContext(),R.drawable.mark_attijeri));
                            break;
                    }

                    googleMap.addMarker(markerOptions);
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                    googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                        @Override
                        public View getInfoWindow( Marker marker) {
                            return null;
                        }

                        @SuppressLint("SetTextI18n")
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
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_assurmap, container, false);
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
    public void markmy_location(GoogleMap map){

        LatLng mylocation = new LatLng(currentlat, currentlong);
        map.addMarker(new MarkerOptions().position(mylocation).title("My Location"));
        map.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentlat,currentlong),13));
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