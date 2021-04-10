package com.example.sayarti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class map_entretien extends Fragment {
    private static  final String BASE_URL = "http://192.168.1.20/android/getdata.php";
    private String marques;
    private Double latit,longit;
    private String nameplace;


    public map_entretien(String marque) {
        this.marques = marque;
    }
    private void getProducts (){


        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);

                                Double lat = object.getDouble("lat");
                                Double longi = object.getDouble("longi");
                                int tel = object.getInt("tel");
                                String name = object.getString("name");
                                String marque = object.getString("marque");
                                latit= lat;
                                longit = longi;
                                nameplace = name;
                                posi p =new posi(lat,longi,name,marque);
                                final ArrayList<posi> data= new ArrayList<posi>();
                                data.add(p);

                            }

                        }catch (Exception e){

                        }





                    }
                }, null);


        Volley.newRequestQueue(getContext()).add(stringRequest);


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
            getProducts();




             /*   posi pi = data.get();


            }else
            {

                LatLng pos = new LatLng(36.79584843969127, 10.18360939999999);
                googleMap.addMarker(new MarkerOptions().position(pos).title("ma famech"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
            }
             */}
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map_entretien, container, false);
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


}