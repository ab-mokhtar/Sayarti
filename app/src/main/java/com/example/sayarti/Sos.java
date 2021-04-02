package com.example.sayarti;

import android.Manifest;
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
import androidx.core.content.ContextCompat;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Sos extends Fragment {

   EditText e1,e2,e3;
   ImageView i1;
   Button btn;
   FusedLocationProviderClient client;
   ImageView i2;
   Spinner spinner;

    private static final String server_name = "192.168.1.25";
    private static final String database = "decla";
    private static final String DB_URL = "jdbc:mysql://" + server_name +  "/" + database;
    private static final String USER = "root";
    private static final String PASS = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sos, container, false);
        e1 = v.findViewById(R.id.local);
        e2 = v.findViewById(R.id.matricule);
        e3= v.findViewById(R.id.autre);
        btn = v.findViewById(R.id.env);
        i1 = v.findViewById(R.id.btnlocal);
        i2 = v.findViewById(R.id.callsos);
        client = LocationServices.getFusedLocationProviderClient(getActivity());

        //spinner
        spinner = v.findViewById(R.id.type_panne);
        String [] values = {"Choisissez le type de panne","Batterie","Pneumatique","Essence","Accident","Incendie","Autre"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
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

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity()
                        , Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    getCurretLocation();
                }else{
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
                }

            }
        });
        i2.setEnabled(false);

        //declaration = new Declaration();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mat = e2.getText().toString().trim();
                String type_panne = spinner.getSelectedItem().toString().trim();
                String au = e3.getText().toString().trim();
                String local = e1.getText().toString().trim();

                if(mat.length()==0|| type_panne.equals("Choisissez le type de panne") || local.length()==0) {
                    Snackbar.make(getView(), "vérifier que les champs rempli ou vérifier votre correction internet", Snackbar.LENGTH_LONG).show();
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
                e1.getText().clear();
                e2.getText().clear();
                e3.getText().clear();
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:71200300"));
                startActivity(intent);
            }
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
            Snackbar.make(getView(), "Permission refusée", Snackbar.LENGTH_LONG).show();
        }
    }

    @SuppressLint("MissingPermission")
    public void getCurretLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)|| locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))

        {client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Location> task) {
                                                                Location location = task.getResult();
                                                                if (location!= null){
                                                                    e1.setText(String.valueOf(location.getLatitude() )+','+String.valueOf(location.getLongitude() ));
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
                                                                            e1.setText(String.valueOf(location1.getLatitude() )+','+String.valueOf(location1.getLongitude() ));
                                                                        }
                                                                    };
                                                                    client.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
                                                                }
                                                            }
                                                            });
                                                        }else {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }
    public class Send extends AsyncTask<String,String,String>
    {
        private String msg ="";
        private int etat = 0;
        private String matricule,typePa,loc,autre;

        public Send(String matricule, String typePa, String loc,String autre) {
            this.matricule = matricule;
            this.typePa = typePa;
            this.loc = loc;
            this.autre = autre;
        }


        @Override
        public String doInBackground(String... strings) {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                if(conn == null)
                {
                    msg = "erreur de la connection";
                }
                else
                {
                    if(spinner.getSelectedItem().equals("Autre"))
                    {
                        String query = "INSERT INTO `declaration` (`matricule`, `type_panne`,`localisation`,`etat`) VALUES('"+matricule+"', '"+autre+"','"+loc+"','"+etat+"')";
                        Statement statement = conn.createStatement();
                        statement.executeUpdate(query);
                        msg ="insertion avec succes";
                    }
                    else
                    {
                        String query = "INSERT INTO `declaration` (`matricule`, `type_panne`,`localisation`,`etat`) VALUES('"+matricule+"', '"+typePa+"','"+loc+"','"+etat+"')";
                        Statement statement = conn.createStatement();
                        statement.executeUpdate(query);
                        msg ="insertion avec succes";
                    }
                }
                conn.close();
            }
            catch (Exception e)
            {
                msg =e.getMessage();
                e.printStackTrace();
            }
            Snackbar.make(getView(), msg, Snackbar.LENGTH_SHORT).show();
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