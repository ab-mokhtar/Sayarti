package com.example.sayarti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link entretien#newInstance} factory method to
 * create an instance of this fragment.
 */
public class entretien extends Fragment  {
    ImageView i1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public entretien() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment entretien.
     */
    // TODO: Rename and change types and number of parameters
    public static entretien newInstance(String param1, String param2) {
        entretien fragment = new entretien();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_entretien, container, false);


        v.findViewById(R.id.alfa).setOnClickListener(v148 -> {
            String marque = "alfa";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.audi).setOnClickListener(v147 -> {
            String marque = "audi";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.baic).setOnClickListener(v146 -> {
            String marque = "baic";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.bmw).setOnClickListener(v145 -> {
            String marque = "bmw";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.changan).setOnClickListener(v144 -> {
            String marque = "changan";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.chery).setOnClickListener(v143 -> {
            String marque = "chery";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.chevrolet).setOnClickListener(v142 -> {
            String marque = "chevrolet";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.citroen).setOnClickListener(v141 -> {
            String marque = "citroen";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.dacia).setOnClickListener(v140 -> {
            String marque = "dacia";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.dfsk).setOnClickListener(v139 -> {
            String marque = "dfsk";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.dongfeng).setOnClickListener(v138 -> {
            String marque = "dongfeng";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.ds).setOnClickListener(v137 -> {
            String marque = "ds";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.fiat).setOnClickListener(v136 -> {
            String marque = "fiat";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.foday).setOnClickListener(v135 -> {
            String marque = "foday";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.ford).setOnClickListener(v134 -> {
            String marque = "ford";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.foton).setOnClickListener(v133 -> {
            String marque = "foton";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.geely).setOnClickListener(v132 -> {
            String marque = "geely";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.great).setOnClickListener(v131 -> {
            String marque = "great wall";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.haval).setOnClickListener(v130 -> {
            String marque = "haval";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.honda).setOnClickListener(v129 -> {
            String marque = "honda";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.hyundai).setOnClickListener(v128 -> {
            String marque = "hyundai";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.isuzu).setOnClickListener(v127 -> {
            String marque = "isuzu";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.jaguar).setOnClickListener(v126 -> {
            String marque = "jaguar";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.jeep).setOnClickListener(v125 -> {
            String marque = "jeep";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.kia).setOnClickListener(v124 -> {
            String marque = "kia";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.lada).setOnClickListener(v123 -> {
            String marque = "lada";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.landrover).setOnClickListener(v122 -> {
            String marque = "land rover";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.mahindra).setOnClickListener(v121 -> {
            String marque = "mahindra";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.mazda).setOnClickListener(v120 -> {
            String marque = "mazda";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.mercedes).setOnClickListener(v119 -> {
            String marque = "mercedes";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.mini).setOnClickListener(v118 -> {
            String marque = "mini";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.mitshi).setOnClickListener(v117 -> {
            String marque = "mitsubishi";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.mg).setOnClickListener(v116 -> {
            String marque = "mg";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.nissan).setOnClickListener(v115 -> {
            String marque = "nissan";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.opel).setOnClickListener(v114 -> {
            String marque = "opel";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.peugeot).setOnClickListener(v113 -> {
            String marque = "peugeot";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.porshe).setOnClickListener(v112 -> {
            String marque = "porshe";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.renault).setOnClickListener(v111 -> {
            String marque = "renault";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.seat).setOnClickListener(v110 -> {
            String marque = "seat";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.skoda).setOnClickListener(v19 -> {
            String marque = "skoda";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.sangyoung).setOnClickListener(v18 -> {
            String marque = "ssangyoung";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.suzuki).setOnClickListener(v17 -> {
            String marque = "suzuki";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.tata).setOnClickListener(v16 -> {
            String marque = "tata";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.toyota).setOnClickListener(v15 -> {
            String marque = "toyota";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.volkswagen).setOnClickListener(v14 -> {
            String marque = "volkswagen";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.volvo).setOnClickListener(v13 -> {
            String marque = "volvo";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.wallys).setOnClickListener(v12 -> {
            String marque = "wallys";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.zxauto).setOnClickListener(v1 -> {
            String marque = "zxauto";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
        });
        return v;
    }


}