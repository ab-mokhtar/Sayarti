package com.example.sayarti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

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
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.comar).setOnClickListener(v1 -> {
            String assur = "comar";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.ami).setOnClickListener(v12 -> {
            String assur = "ami";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.astree).setOnClickListener(v1 -> {
            String assur = "astree";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.attakafulia).setOnClickListener(v12 -> {
            String assur = "attakafulia";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.attijari).setOnClickListener(v1 -> {
            String assur = "attijari";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.biat).setOnClickListener(v12 -> {
            String assur = "biat";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.carte).setOnClickListener(v1 -> {
            String assur = "carte";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.ctama).setOnClickListener(v12 -> {
            String assur = "ctama";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.maghrebia).setOnClickListener(v1 -> {
            String assur = "maghrebia";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.star).setOnClickListener(v12 -> {
            String assur = "star";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.zitouna).setOnClickListener(v1 -> {
            String assur = "zitouna";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.gat).setOnClickListener(v12 -> {
            String assur = "gat";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.lloyd).setOnClickListener(v1 -> {
            String assur = "lloyd";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.hayet).setOnClickListener(v12 -> {
            String assur = "hayet";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        v.findViewById(R.id.mae).setOnClickListener(v1 -> {
            String assur = "mae";
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.fragassur, new assurmap(assur)).addToBackStack("frg").commit();
        });
        return v;

    }
}