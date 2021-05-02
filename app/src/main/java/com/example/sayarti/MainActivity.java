package com.example.sayarti;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity  {
    private DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, initializationStatus -> {
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);



        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    // set item as selected to persist highlight
                    menuItem.setChecked(true);
                    // close drawer when item is tapped
                    drawerLayout.closeDrawers();

                    // Add code here to update the UI based on the item selected
                    // For example, swap UI fragments here
                    switch (menuItem.getItemId())
                    {
                        case R.id.connect :
                            getSupportFragmentManager().beginTransaction().replace(R.id.container_frag,new Authenfication()).addToBackStack(null).commit();
                            break;
                        case R.id.create :
                            getSupportFragmentManager().beginTransaction().replace(R.id.container_frag,new FragmentSignUp()).addToBackStack(null).commit();
                            break;
                        case R.id.voit_neuv :
                            getSupportFragmentManager().beginTransaction().replace(R.id.container_frag,new voiture_neuve()).addToBackStack(null).commit();
                            break;
                        case R.id.entretien :
                        case R.id.borne_charge :
                            getSupportFragmentManager().beginTransaction().replace(R.id.container_frag,new entretien()).addToBackStack(null).commit();
                            break;
                        case R.id.kiosque :
                            getSupportFragmentManager().beginTransaction().replace(R.id.container_frag,new Kiosque()).addToBackStack(null).commit();
                            break;
                        case R.id.assur :
                            getSupportFragmentManager().beginTransaction().replace(R.id.container_frag,new Assurance()).addToBackStack(null).commit();
                            break;
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                });


        CardView c1 = findViewById(R.id.card_view1);
        c1.setOnClickListener(v -> getSupportFragmentManager().beginTransaction().replace(R.id.container_frag, new voiture_neuve()).addToBackStack("frg").commit());
        CardView c2 = findViewById(R.id.card_view2);
        c2.setOnClickListener(v -> {
            LocationManager locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);
            if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MainActivity.this
                    , Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)) {
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)|| locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_frag, new charge_bornes()).addToBackStack("frg").commit();

                }else {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }

            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
                    }
                }
            }
        });


        CardView c3 = findViewById(R.id.card_view3);
        c3.setOnClickListener(v -> {
            LocationManager locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);
            if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MainActivity.this
                    , Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)) {
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)|| locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_frag, new Kiosque()).addToBackStack("frg").commit();

                }else {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }

            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
                    }
                }
            }
            });

        CardView c4 = findViewById(R.id.card_view4);
        c4.setOnClickListener(v -> {
            LocationManager locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);
            if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MainActivity.this
                    , Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)) {
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)|| locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_frag, new Sos()).addToBackStack("frg").commit();

                }else {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }

            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
                    }
                }
            }
        });
        CardView c6 = findViewById(R.id.card_view5);
        c6.setOnClickListener(v -> {
            LocationManager locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);
            if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MainActivity.this
                    , Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)) {
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)|| locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_frag, new Assurance()).addToBackStack("frg").commit();

                }else {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }

            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
                    }
                }
            }
        });
        CardView c5 = findViewById(R.id.card_view0);
        c5.setOnClickListener(v -> {
            LocationManager locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);
            if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MainActivity.this
                    , Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)) {
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)|| locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_frag, new entretien()).addToBackStack("frg").commit();

                }else {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }

            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
                    }
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

