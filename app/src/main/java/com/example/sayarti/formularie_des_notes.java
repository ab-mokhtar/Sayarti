package com.example.sayarti;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


public class formularie_des_notes extends Fragment  {
    String user ;
    EditText e1,e2;
    Button btn, btn2;

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
        btn2 = v.findViewById(R.id.event);
        mAuth = FirebaseAuth.getInstance();

        Calendar calendar=Calendar.getInstance();
        date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

        Notes note = new Notes();

        db = FirebaseDatabase.getInstance("https://sayarti-122d7-default-rtdb.firebaseio.com/").getReference().child(user);

        btn2.setOnClickListener(v12 -> {
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

            if(e1.length()==0|| e2.length()==0 ) {
                Snackbar.make(Objects.requireNonNull(getView()), "vérifier les champs remplis", Snackbar.LENGTH_SHORT).show();
            }
            else{
                note.setMatricule(e1.getText().toString().trim());
                note.setNote(e2.getText().toString().trim());

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar c = Calendar.getInstance();
                date = sdf.format(c.getTime());
                note.setDate(date);
                note.setuId(FirebaseAuth.getInstance().getUid());
                db.push().setValue(note);
                Snackbar.make(Objects.requireNonNull(getView()), "LES DONNEES SONT BIEN AJOUTEES", Snackbar.LENGTH_SHORT).show();
            }
        });

        return v;
    }


}