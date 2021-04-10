package com.example.sayarti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_entretien, container, false);


        v.findViewById(R.id.alfa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "alfa";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }


        });
        v.findViewById(R.id.audi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "audi";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }


        });
        v.findViewById(R.id.baic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "baic";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }


        });
        v.findViewById(R.id.bmw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "bmw";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.changan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "changan";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.chery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "chery";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.chevrolet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "chevrolet";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.citroen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "citroen";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.dacia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "dacia";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.dfsk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "dfsk";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.dongfeng).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "dongfeng";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.ds).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "ds";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.fiat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "fiat";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.foday).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "foday";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.ford).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "ford";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.foton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "foton";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.geely).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "geely";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.great).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "great wall";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.haval).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "haval";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.honda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "honda";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.hyundai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "hyundai";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.isuzu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "isuzu";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.jaguar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "jaguar";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.jeep).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "jeep";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.kia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "kia";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.lada).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "lada";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.landrover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "land rover";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.mahindra).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "mahindra";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.mazda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "mazda";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.mercedes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "mercedes";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.mini).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "mini";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.mitshi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "mitsubishi";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.mg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "mg";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.nissan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "nissan";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.opel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "opel";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.peugeot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "peugeot";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.porshe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "porshe";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.renault).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "renault";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.seat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "seat";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.skoda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "skoda";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.sangyoung).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "ssangyoung";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.suzuki).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "suzuki";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.tata).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "tata";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.toyota).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "toyota";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.volkswagen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "volkswagen";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.volvo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "volvo";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.wallys).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "wallys";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        v.findViewById(R.id.zxauto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = "zxauto";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.testfrag, new map_entretien(marque)).addToBackStack("frg").commit();
            }
        });
        return v;
    }


}