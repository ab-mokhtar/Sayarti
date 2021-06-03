package com.example.sayarti;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Getnerabyplaces extends AsyncTask<Object, String, String> {
    private String googleplaceData;
    private GoogleMap mMap;
    private final Context mContext;

    public Getnerabyplaces(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap)objects[0];
        String url = (String) objects[1];
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
        List<HashMap<String, String>> nearbyPlacesList;
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
            double latitude =Double.parseDouble(Objects.requireNonNull(googleNearbyPlace.get("lat")));
            double longitude = Double.parseDouble(Objects.requireNonNull(googleNearbyPlace.get("lng")));
            LatLng latLng = new LatLng(latitude,longitude);
            markerOptions.position(latLng);
           // markerOptions.title(nameOfPlace);
            assert nameOfPlace != null;
            if (nameOfPlace.toLowerCase().contains("shell")){
                markerOptions.icon(bitmapDescriptordescriptor(mContext, R.drawable.mark_shell));

            }else if (nameOfPlace.toLowerCase().contains("agil")) {
                markerOptions.icon(bitmapDescriptordescriptor(mContext, R.drawable.mark_agil));


            }
            else if (nameOfPlace.toLowerCase().contains("total") || nameOfPlace.toLowerCase().contains("طوطال")) {
                markerOptions.icon(bitmapDescriptordescriptor(mContext,R.drawable.mark_total));


            }
            else if (nameOfPlace.toLowerCase().contains("ola") || nameOfPlace.toLowerCase().contains("oilibya")|| nameOfPlace.toLowerCase().contains("أويليبيا")) {
                markerOptions.icon(bitmapDescriptordescriptor(mContext, R.drawable.mark_ola));


            }else {
               markerOptions.icon(bitmapDescriptordescriptor(mContext, R.drawable.makerkiosque));

           }

            markerOptions.title(nameOfPlace);
            mMap.addMarker(markerOptions);

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
