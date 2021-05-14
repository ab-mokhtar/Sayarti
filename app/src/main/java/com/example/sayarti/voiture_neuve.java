package com.example.sayarti;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


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


        recher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a = input.getText().toString();

                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.actBien, new ListVoiture(a)).addToBackStack("frg").commit();
            }
        });
        return v;

    }

}





