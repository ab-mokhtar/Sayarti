package com.example.sayarti;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {
    private static  final String BASE_URL = "http://dev.goodlinks.tn/sayarti-apps/getdata.php";
    private DrawerLayout drawerLayout;
    private final ArrayList<posi> data= new ArrayList<>();
    Context  context ;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getProducts();
        setContentView(R.layout.activity_main);

        if (!isConnected())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(false);
            builder.setTitle(R.string.internetTitle);
            builder.setMessage(R.string.internetMsg);
            builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Vous utilisez SAYARTI sans connection internet !", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            });

            builder.setNegativeButton("Ressayer", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(isConnected())
                    {
                        dialog.dismiss();
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Connexion établie", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                    else
                    {
                        builder.show();
                    }
                }
            });
            builder.show();
        }

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
                            getSupportFragmentManager().beginTransaction().replace(R.id.container_frag,new entretien()).addToBackStack(null).commit();
                        case R.id.borne_charge :
                            getSupportFragmentManager().beginTransaction().replace(R.id.container_frag,new charge_bornes()).addToBackStack(null).commit();
                            break;
                        case R.id.kiosque :
                            getSupportFragmentManager().beginTransaction().replace(R.id.container_frag,new Kiosque()).addToBackStack(null).commit();
                            break;
                        case R.id.assur :
                            getSupportFragmentManager().beginTransaction().replace(R.id.container_frag,new Assurance()).addToBackStack(null).commit();
                            break;
                        case R.id.Visite_technique :
                            getSupportFragmentManager().beginTransaction().replace(R.id.container_frag, new Visite_technique()).addToBackStack("frg").commit();
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
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
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
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
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
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
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
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
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
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
                }
            }
        });

        CardView c7 = findViewById(R.id.card_view7);
        c7.setOnClickListener(v -> {
            LocationManager locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);
            if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MainActivity.this
                    , Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)) {
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)|| locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_frag, new Visite_technique()).addToBackStack("frg").commit();

                }else {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }

            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
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

    private void getProducts (){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                response -> {
                    try {

                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i<array.length(); i++){

                            JSONObject object = array.getJSONObject(i);

                            Double lat = object.getDouble("lat");
                            Double longi = object.getDouble("longi");
                            String tel = object.getString("tel");
                            String name = object.getString("name");
                            String marque = object.getString("marque");
                            String type = object.getString("type");
                            posi p =new posi(lat,longi,name,marque,tel,type);
                            data.add(p);

                        }
                        PrefConfig.writeListInPref(getApplicationContext(), data);


                    }catch (Exception ignored){

                    }
                }, null);
        Volley.newRequestQueue(MainActivity.this).add(stringRequest);
    }


    private boolean isConnected()
    {
        ConnectivityManager connectivityManager  = (ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}

