package com.example.sayarti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;


public class entretien extends Fragment  {


    public entretien() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_entretien, container, false);


        v.findViewById(R.id.midas).setOnClickListener(v148 -> {
            String marque = "midas";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.fixngo).setOnClickListener(v147 -> {
            String marque = "fixngo";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.bosh).setOnClickListener(v146 -> {
            String marque = "bosh";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.otop).setOnClickListener(v145 -> {
            String marque = "otop";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.eurorepar).setOnClickListener(v144 -> {
            String marque = "eurorepar";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.speedy).setOnClickListener(v143 -> {
            String marque = "speedy";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        return v;
    }


}