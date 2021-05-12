package com.example.sayarti;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;


public class formularie_des_notes extends Fragment  {
    String user ;
    EditText e1,e2;
    Button btn;
    ImageView cal;
    private FirebaseAuth mAuth;
    DatabaseReference db;

    String date;


    public formularie_des_notes(String user) {

        this.user= user;
    }
    public formularie_des_notes() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_formularie_des_notes, container, false);
        e1 = v.findViewById(R.id.matnote);
        e2 = v.findViewById(R.id.notes);
        btn = v.findViewById(R.id.envfor);
        cal = v.findViewById(R.id.calen);

        mAuth = FirebaseAuth.getInstance();

        Calendar calendar=Calendar.getInstance();
        date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

        Notes note = new Notes();

        db = FirebaseDatabase.getInstance("https://sayarti-122d7-default-rtdb.firebaseio.com/").getReference().child(user);

        cal.setOnClickListener(v12 -> {
            //calendrier
            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setData(CalendarContract.Events.CONTENT_URI);
            intent.putExtra(CalendarContract.Events.TITLE,e1.getText().toString().trim());
            intent.putExtra(CalendarContract.Events.DESCRIPTION,e2.getText().toString().trim());
            intent.putExtra(CalendarContract.Events.ALL_DAY,true);
            intent.putExtra(Intent.EXTRA_EMAIL, Objects.requireNonNull(mAuth.getCurrentUser()).getEmail());
            startActivity(intent);
        });

        btn.setOnClickListener(v1 -> {
            String mat = e1.getText().toString().trim();
            String notes = e2.getText().toString().trim();


            if(mat.length()==0|| notes.length()==0) {
                Snackbar.make(Objects.requireNonNull(getView()), "vérifier les champs remplis", Snackbar.LENGTH_LONG).show();
            }
            else{
                note.setMatricule(e1.getText().toString().trim());
                note.setNote(e2.getText().toString().trim());
                note.setDate(date);

                db.push().setValue(note);
                Snackbar.make(Objects.requireNonNull(getView()), "LES DONNEES SONT BIEN AJOUTEES", Snackbar.LENGTH_LONG).show();
            }
        });

        return v;
    }


}