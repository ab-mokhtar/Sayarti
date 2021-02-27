package com.example.sayarti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity  {
 private CardView c1,c2,c3,c4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        c1 = (CardView) findViewById(R.id.card_view1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frag,new voiture_neuve()).addToBackStack("frg").commit();
            }
        });

        c4 = (CardView) findViewById(R.id.card_view4);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frag,new Sos()).addToBackStack("frg").commit();
            }});



}}