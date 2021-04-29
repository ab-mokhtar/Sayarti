package com.example.sayarti;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DataParser {
    private HashMap<String,String> getSinglePlace(JSONObject googlePlaceJSON){

        HashMap<String,String> googlrPlaceMap= new HashMap<>();
        String NameOfPlace = "-NA-";
        String vicinity = "-NA-";
        String latitude;
        String longitude;
        String reference;
        try {
            if(!googlePlaceJSON.isNull("name")) {
                 NameOfPlace = googlePlaceJSON.getString("name");
            }
            if(!googlePlaceJSON.isNull("vicinity")) {
                vicinity = googlePlaceJSON.getString("vicinity");
            }
             latitude =googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lat");
             longitude = googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lng");
             reference = googlePlaceJSON.getString("reference");
            googlrPlaceMap.put("Place_name",NameOfPlace);
            googlrPlaceMap.put("vicinity",vicinity);
            googlrPlaceMap.put("lat",latitude);
            googlrPlaceMap.put("lng",longitude);
            googlrPlaceMap.put("reference",reference);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return googlrPlaceMap;
    }
    private List<HashMap<String,String>> getAllPlaces(JSONArray jsonArray){
        List<HashMap<String,String>> nearbyPlacelist = new ArrayList<>();
        int counter = jsonArray.length();
        HashMap<String,String> NearbyPlaceMap;
        for(int i=0; i<counter;i++){
            try {
                NearbyPlaceMap =getSinglePlace( (JSONObject) jsonArray.get(i));
                nearbyPlacelist.add(NearbyPlaceMap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return nearbyPlacelist;
    }
    public List<HashMap<String,String>>parse(String jSONdata){
        JSONArray jsonArray = null;
        JSONObject jsonObject;


        try {
            jsonObject = new JSONObject(jSONdata);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getAllPlaces(Objects.requireNonNull(jsonArray));
    }
}
