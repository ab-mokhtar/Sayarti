package com.example.sayarti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Kiosque extends Fragment {
    ImageView shell,ola,total,agil,staroil,random;


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
        random = v.findViewById(R.id.random_gas);
        shell.setOnClickListener(v15 -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.kiosqueconten,new MapsFragment("SHELL")).addToBackStack(null).commit());
        agil.setOnClickListener(v14 -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.kiosqueconten,new MapsFragment("AGIL")).addToBackStack(null).commit());
        total.setOnClickListener(v13 -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.kiosqueconten,new MapsFragment("TOTAL")).addToBackStack(null).commit());
        ola.setOnClickListener(v12 -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.kiosqueconten,new MapsFragment("ola")).addToBackStack(null).commit());
        staroil.setOnClickListener(v1 -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.kiosqueconten,new MapsFragment("STAROIL")).addToBackStack(null).commit());
        random.setOnClickListener(v1 -> requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.kiosqueconten,new MapsFragment("")).addToBackStack(null).commit());
        /*random.setOnClickListener(v1 -> {
            Snackbar.make(Objects.requireNonNull(getView()), "random", Snackbar.LENGTH_LONG).show();
*/
        return v;
    }
}