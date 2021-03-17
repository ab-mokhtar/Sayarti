package com.example.sayarti;

import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Getnerabyplaces extends AsyncTask<Object, String, String> {
    private String googleplaceData,url;
    private GoogleMap mMap;
    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];
        DownloadUrl downloadUrl= new DownloadUrl();
        try {
            googleplaceData = downloadUrl.ReadUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googleplaceData;
    }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String, String>> nearbyPlacesList=null;
        DataParser dataParser=new DataParser();
        nearbyPlacesList = dataParser.parse(s);
        DisplayNearbyPlaces(nearbyPlacesList);
    }

    private void DisplayNearbyPlaces(List<HashMap<String, String>> nearbyPlacesList){

        for (int i=0; i<nearbyPlacesList.size();i++){

            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String>googleNearbyPlace = nearbyPlacesList.get(i);
            String nameOfPlace = googleNearbyPlace.get("Place_name");
            String vicinity = googleNearbyPlace.get("vicinity");
            double latitude =Double.parseDouble(googleNearbyPlace.get("lat"));
            double longitude = Double.parseDouble(googleNearbyPlace.get("lng"));
            LatLng latLng = new LatLng(latitude,longitude);
            mMap.addMarker(new MarkerOptions().position(latLng).title(nameOfPlace +" : "+vicinity));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));


        }
    }
}
