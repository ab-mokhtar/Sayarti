package com.example.sayarti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Assurance extends Fragment {


    public Assurance() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_assurance, container, false);
        v.findViewById(R.id.bh).setOnClickListener(v12 -> {
            String assur = "bh";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.comar).setOnClickListener(v1 -> {
            String assur = "comar";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.ami).setOnClickListener(v12 -> {
            String assur = "ami";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.astree).setOnClickListener(v1 -> {
            String assur = "astree";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.attakafulia).setOnClickListener(v12 -> {
            String assur = "attakafulia";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.attijari).setOnClickListener(v1 -> {
            String assur = "attijari";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.biat).setOnClickListener(v12 -> {
            String assur = "biat";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.carte).setOnClickListener(v1 -> {
            String assur = "carte";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.ctama).setOnClickListener(v12 -> {
            String assur = "ctama";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.maghrebia).setOnClickListener(v1 -> {
            String assur = "maghrebia";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.star).setOnClickListener(v12 -> {
            String assur = "star";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.zitouna).setOnClickListener(v1 -> {
            String assur = "zitouna";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.gat).setOnClickListener(v12 -> {
            String assur = "gat";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.lloyd).setOnClickListener(v1 -> {
            String assur = "lloyd";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.hayet).setOnClickListener(v12 -> {
            String assur = "hayet";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.mae).setOnClickListener(v1 -> {
            String assur = "mae";
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        return v;

    }
}