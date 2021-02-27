package com.example.sayarti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
 private CardView c1,c2,c3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        c1 = (CardView) findViewById(R.id.card_view1);
        c1.setOnClickListener(this);
        c2 = (CardView) findViewById(R.id.card_view2);
        c2.setOnClickListener(this);
        c3 = (CardView) findViewById(R.id.card_view3);
        c3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i ;
        switch (v.getId())
        {
            case R.id.card_view1 :
                i = new Intent(this,Bienvenu.class);
                startActivity(i);
                break;
            case R.id.card_view2 :
                i = new Intent(this,Bienvenu.class);
                startActivity(i);
                break;
            case R.id.card_view3 :
                i = new Intent(this,Bienvenu.class);
                startActivity(i);
                break;
        }
    }
}