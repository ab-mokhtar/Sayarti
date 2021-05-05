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
 * Use the {@link Kiosque#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Kiosque extends Fragment {
    ImageView shell,ola,total,agil,staroil;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public Kiosque() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Kiosque.
     */
    // TODO: Rename and change types and number of parameters
    public static Kiosque newInstance(String param1, String param2) {
        Kiosque fragment = new Kiosque();
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