package com.example.sayarti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Assurance#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Assurance extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Assurance() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Assurance.
     */
    // TODO: Rename and change types and number of parameters
    public static Assurance newInstance(String param1, String param2) {
        Assurance fragment = new Assurance();
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
        View v = inflater.inflate(R.layout.fragment_assurance, container, false);
        v.findViewById(R.id.bh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String assur = "bh";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
            }


        });
        v.findViewById(R.id.comar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String assur = "comar";
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
            }


        });
        return v;

    }
}