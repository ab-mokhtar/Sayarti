package com.example.sayarti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class voiture_neuve extends Fragment {
    public TextView erreur,T;
    private EditText input;
    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work


    //the recyclerview
    RecyclerView recyclerView;


    public voiture_neuve() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_bienvenu, container, false);


        input = v.findViewById(R.id.inp);
        Button recher = v.findViewById(R.id.rech);


        recher.setOnClickListener(v149 -> {

            String a = input.getText().toString();

            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(a)).addToBackStack("frg").commit();
        });


        v.findViewById(R.id.alfa).setOnClickListener(v148 -> {
            String marque = "alfa";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.audi).setOnClickListener(v147 -> {
            String marque = "audi";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.baic).setOnClickListener(v146 -> {
            String marque = "baic";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.bmw).setOnClickListener(v145 -> {
            String marque = "bmw";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.changan).setOnClickListener(v144 -> {
            String marque = "changan";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.chery).setOnClickListener(v143 -> {
            String marque = "chery";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.chevrolet).setOnClickListener(v142 -> {
            String marque = "chevrolet";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.citroen).setOnClickListener(v141 -> {
            String marque = "citroen";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.dacia).setOnClickListener(v140 -> {
            String marque = "dacia";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.dfsk).setOnClickListener(v139 -> {
            String marque = "dfsk";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.dongfeng).setOnClickListener(v138 -> {
            String marque = "dongfeng";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.ds).setOnClickListener(v137 -> {
            String marque = "ds";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.fiat).setOnClickListener(v136 -> {
            String marque = "fiat";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.foday).setOnClickListener(v135 -> {
            String marque = "foday";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.ford).setOnClickListener(v134 -> {
            String marque = "ford";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.foton).setOnClickListener(v133 -> {
            String marque = "foton";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.geely).setOnClickListener(v132 -> {
            String marque = "geely";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.great).setOnClickListener(v131 -> {
            String marque = "great wall";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.haval).setOnClickListener(v130 -> {
            String marque = "haval";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.honda).setOnClickListener(v129 -> {
            String marque = "honda";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.hyundai).setOnClickListener(v128 -> {
            String marque = "hyundai";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.isuzu).setOnClickListener(v127 -> {
            String marque = "isuzu";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.jaguar).setOnClickListener(v126 -> {
            String marque = "jaguar";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.jeep).setOnClickListener(v125 -> {
            String marque = "jeep";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.kia).setOnClickListener(v124 -> {
            String marque = "kia";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.lada).setOnClickListener(v123 -> {
            String marque = "lada";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.landrover).setOnClickListener(v122 -> {
            String marque = "land rover";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.mahindra).setOnClickListener(v121 -> {
            String marque = "mahindra";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.mazda).setOnClickListener(v120 -> {
            String marque = "mazda";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.mercedes).setOnClickListener(v119 -> {
            String marque = "mercedes";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.mini).setOnClickListener(v118 -> {
            String marque = "mini";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.mitshi).setOnClickListener(v117 -> {
            String marque = "mitsubishi";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.mg).setOnClickListener(v116 -> {
            String marque = "mg";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.nissan).setOnClickListener(v115 -> {
            String marque = "nissan";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.opel).setOnClickListener(v114 -> {
            String marque = "opel";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.peugeot).setOnClickListener(v113 -> {
            String marque = "peugeot";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.porshe).setOnClickListener(v112 -> {
            String marque = "porshe";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.renault).setOnClickListener(v111 -> {
            String marque = "renault";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.seat).setOnClickListener(v110 -> {
            String marque = "seat";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.skoda).setOnClickListener(v19 -> {
            String marque = "skoda";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.sangyoung).setOnClickListener(v18 -> {
            String marque = "ssangyoung";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.suzuki).setOnClickListener(v17 -> {
            String marque = "suzuki";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.tata).setOnClickListener(v16 -> {
            String marque = "tata";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.toyota).setOnClickListener(v15 -> {
            String marque = "toyota";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.volkswagen).setOnClickListener(v14 -> {
            String marque = "volkswagen";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.volvo).setOnClickListener(v13 -> {
            String marque = "volvo";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.wallys).setOnClickListener(v12 -> {
            String marque = "wallys";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.zxauto).setOnClickListener(v1 -> {
            String marque = "zxauto";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(marque)).addToBackStack("frg").commit();
        });
        return v;

    }

}





