package com.example.sayarti;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;


public class logout extends Fragment {
    private FirebaseAuth mAuth;


    public logout() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        View v = inflater.inflate(R.layout.fragment_logout, container, false);
        //Button btn = v.findViewById(R.id.logoutbtn);
        LinearLayout log = v.findViewById(R.id.logoutbtn);
        log.setOnClickListener(v1 -> {
            mAuth.signOut();
            LoginManager.getInstance().logOut();
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        });
        return v;
    }
}