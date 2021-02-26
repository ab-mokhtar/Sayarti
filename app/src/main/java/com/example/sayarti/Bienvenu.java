package com.example.sayarti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Bienvenu extends AppCompatActivity implements View.OnClickListener {
    private CardView Lcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenu);

        Lcard = (CardView) findViewById(R.id.logoCard);
        Lcard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.logoCard :
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.sayarti.tn/")));
                break;
        }
    }

}