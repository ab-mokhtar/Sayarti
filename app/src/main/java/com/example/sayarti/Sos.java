package com.example.sayarti;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Sos extends Fragment {

    SupportMapFragment supportMapFragment;
    GoogleMap map;

    double currentlat , currentlong ;
    String Mylocalisation;
    EditText e2,e3;
    ImageView i1;
    Button btn;
    FusedLocationProviderClient client;
    ImageView i2;
    Spinner spinner;

    private static final String server_name = "dev.goodlinks.tn/sayarti-apps/mysql.php?server=goodlinkiastage.mysql.db&db=goodlinkiastage";
    private static final String database = "goodlinkiastage";
    private static final String DB_URL = "jdbc:mysql://dev.goodlinks.tn/sayarti-apps/mysql.php?server=goodlinkiastage.mysql.db&db=goodlinkiastage" ;
    private static final String USER = "goodlinkiastage";
    private static final String PASS = "FUP2JT5qbYdz";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sos, container, false);
        e2 = v.findViewById(R.id.matricule);
        e3= v.findViewById(R.id.autre);
        btn = v.findViewById(R.id.env);
        i2 = v.findViewById(R.id.callsos);
        client = LocationServices.getFusedLocationProviderClient(Objects.requireNonNull(getActivity()));

        //spinner
        spinner = v.findViewById(R.id.type_panne);
        String [] values = {"Choisissez le type de panne","Batterie","Pneumatique","Essence","Accident","Incendie","Autre"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        e3.setVisibility(View.INVISIBLE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("Autre"))
                {
                    e3.setVisibility(View.VISIBLE);
                }
                else
                {
                    e3.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        getCurretLocation();
        i2.setEnabled(false);

        //declaration = new Declaration();
        btn.setOnClickListener(v12 -> {
            String mat = e2.getText().toString().trim();
            String type_panne = spinner.getSelectedItem().toString().trim();
            String au = e3.getText().toString().trim();
            String local = Mylocalisation;

            if(mat.length()==0|| type_panne.equals("Choisissez le type de panne") || local.length()==0) {
                Snackbar.make(Objects.requireNonNull(getView()), "vérifier que les champs rempli ou vérifier votre correction internet", Snackbar.LENGTH_LONG).show();
            }
            else
            {
                Send send = new Send(mat, type_panne, local,au);
                send.setMatricule(mat);
                send.setTypePa(type_panne);
                send.setLoc(local);
                send.setAutre(au);
                send.execute("");
            }
            i2.setEnabled(true);
            e2.getText().clear();
            e3.getText().clear();
        });

        i2.setOnClickListener(v1 -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:71200300"));
            startActivity(intent);
        });


        return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && (grantResults.length>0)&&(grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED)){
            getCurretLocation();
        }
        else{
            Snackbar.make(Objects.requireNonNull(getView()), "Permission refusée", Snackbar.LENGTH_LONG).show();
        }
    }

    @SuppressLint("MissingPermission")
    public void getCurretLocation() {
        LocationManager locationManager = (LocationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)|| locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))

        {client.getLastLocation().addOnCompleteListener(task -> {
            Location location = task.getResult();
            supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_sos);
            if (location!= null){
                Mylocalisation=String.valueOf(location.getLatitude() )+','+ location.getLongitude();

                supportMapFragment.getMapAsync(googleMap -> {
                    LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(mylocation).title("My Location"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

                });
            }
            else
            {
                LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setInterval(10000)
                        .setFastestInterval(1000)
                        .setNumUpdates(1);
                LocationCallback locationCallback = new LocationCallback() {
                    @Override
                    public void onLocationResult(@NonNull LocationResult locationResult) {
                        Location location1 = locationResult.getLastLocation();
                        Mylocalisation=String.valueOf(location1.getLatitude() )+','+ location1.getLongitude();
                        supportMapFragment.getMapAsync(googleMap -> {
                            LatLng mylocation = new LatLng(location1.getLatitude(), location1.getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(mylocation).title("My Location"));
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location1.getLatitude(), location1.getLongitude()), 13));

                        });

                    }
                };
                client.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
            }
        });
        }else {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }
    public class Send extends AsyncTask<String,String,String>
    {
        private String matricule,typePa,loc,autre;
        private final String date ;

        public Send(String matricule, String typePa, String loc,String autre) {
            this.matricule = matricule;
            this.typePa = typePa;
            this.loc = loc;
            this.autre = autre;
            Calendar calendar=Calendar.getInstance();
            this.date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        }


        @Override
        public String doInBackground(String... strings) {

            String msg;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                if(conn == null)
                {
                    msg = "erreur de la connection";
                }
                else
                {
                    String agent = "";
                    int etat = 0;
                    if(spinner.getSelectedItem().equals("Autre"))
                    {
                        String query = "INSERT INTO `sayarti_declarations` (`matricule`, `type_panne`,`localisation`,`date`,`etat`,`agent`) VALUES('"+matricule+"', '"+autre+"','"+loc+"','"+date+"','"+ etat +"','"+ agent +"')";
                        Statement statement = conn.createStatement();
                        statement.executeUpdate(query);
                        msg ="insertion avec succes";
                    }
                    else
                    {
                        String query = "INSERT INTO `sayarti_declarations` (`matricule`, `type_panne`,`localisation`,`date`,`etat`,`agent`) VALUES('"+matricule+"', '"+typePa+"','"+loc+"','"+date+"','"+ etat +"','"+ agent +"')";
                        Statement statement = conn.createStatement();
                        statement.executeUpdate(query);
                        msg ="insertion avec succes";
                    }
                }
                Objects.requireNonNull(conn).close();
            }
            catch (Exception e)
            {
                msg =e.getMessage();
                e.printStackTrace();
            }
            Snackbar.make(Objects.requireNonNull(getView()), Objects.requireNonNull(msg), Snackbar.LENGTH_SHORT).show();
            return msg;
        }

        public String getMatricule() {
            return matricule;
        }

        public void setMatricule(String matricule) {
            this.matricule = matricule;
        }

        public String getTypePa() {
            return typePa;
        }

        public void setTypePa(String typePa) {
            this.typePa = typePa;
        }

        public String getLoc() {
            return loc;
        }

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public String getAutre() {
            return autre;
        }

        public void setAutre(String autre) {
            this.autre = autre;
        }
    }
}