package com.example.sayarti;

import android.os.AsyncTask;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

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
            markerOptions.position(latLng);
           // markerOptions.title(nameOfPlace);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            markerOptions.snippet(nameOfPlace);
            mMap.addMarker(markerOptions);



        }
    }
}
