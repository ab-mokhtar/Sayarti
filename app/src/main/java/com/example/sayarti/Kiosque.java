package com.example.sayarti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Objects;


public class Kiosque extends Fragment {
    ImageView shell,ola,total,agil,staroil;


    public Kiosque() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_kiosque, container, false);
        shell=v.findViewById(R.id.SHELL);
        agil=v.findViewById(R.id.AGIL);
        total=v.findViewById(R.id.TOTAL);
        ola=v.findViewById(R.id.OLA);
        staroil=v.findViewById(R.id.STAROIL);
        shell.setOnClickListener(v15 -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.kiosqueconten,new MapsFragment("SHELL")).addToBackStack(null).commit());
        agil.setOnClickListener(v14 -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.kiosqueconten,new MapsFragment("AGIL")).addToBackStack(null).commit());
        total.setOnClickListener(v13 -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.kiosqueconten,new MapsFragment("TOTAL")).addToBackStack(null).commit());
        ola.setOnClickListener(v12 -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.kiosqueconten,new MapsFragment("ola")).addToBackStack(null).commit());
        staroil.setOnClickListener(v1 -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.kiosqueconten,new MapsFragment("STAROIL")).addToBackStack(null).commit());
        return v;
    }
}