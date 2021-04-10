package com.example.sayarti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class notes_home extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        user = FirebaseAuth.getInstance().getUid();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag, new formularie_des_notes(user)).addToBackStack("frg").commit();
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedfragment  = null;

            switch (item.getItemId()){
                case R.id.add_note:
                    selectedfragment = new formularie_des_notes(user);
                    break;
                case R.id.list:
                    selectedfragment = new liste_notes();
                    break;
                case R.id.logout:
                    selectedfragment = new logout();
                    break;



            }
            assert selectedfragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.frag,selectedfragment).commit();
            return true;
        }

    };
    }