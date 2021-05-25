package com.example.sayarti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class notes_home extends AppCompatActivity {
    private String user, userName,userEmail;
    private DrawerLayout drawerLayout;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_connected);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        user = FirebaseAuth.getInstance().getUid();

        userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();



        getSupportFragmentManager().beginTransaction().replace(R.id.frag, new formularie_des_notes(user)).addToBackStack("frg").commit();
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        drawerLayout = findViewById(R.id.drawer_layout1);

        NavigationView navigationView = findViewById(R.id.nav_view1);



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

                        case R.id.voit_neuv :
                            getSupportFragmentManager().beginTransaction().replace(R.id.contt,new voiture_neuve()).addToBackStack(null).commit();
                            break;
                        case R.id.entretien :
                            getSupportFragmentManager().beginTransaction().replace(R.id.contt,new entretien()).addToBackStack(null).commit();
                            break;
                        case R.id.borne_charge :
                            getSupportFragmentManager().beginTransaction().replace(R.id.contt,new charge_bornes()).addToBackStack(null).commit();
                            break;
                        case R.id.kiosque :
                            getSupportFragmentManager().beginTransaction().replace(R.id.contt,new Kiosque()).addToBackStack(null).commit();
                            break;
                        case R.id.assur :
                            getSupportFragmentManager().beginTransaction().replace(R.id.contt,new Assurance()).addToBackStack(null).commit();
                            break;
                        case R.id.profil :
                            startActivity(new Intent(getApplicationContext(), notes_home.class));
                            break;
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                });

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
                    selectedfragment = new logout(userName,userEmail);
                    break;

            }
            assert selectedfragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.frag,selectedfragment).commit();
            return true;
        }

    };
    public void onDestroy() {

        super.onDestroy();
        super.onStop();
        mAuth.signOut();
        LoginManager.getInstance().logOut();

    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Veuillez déconnecté ",Toast.LENGTH_LONG).show();
    }
}