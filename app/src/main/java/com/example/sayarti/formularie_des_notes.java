package com.example.sayarti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link formularie_des_notes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class formularie_des_notes extends Fragment  {
    String user ;
    EditText e1,e2;
    DrawerLayout d1;
    Button btn;

    DatabaseReference db;

    String date;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public formularie_des_notes(String user) {
        int pos;
        while (user.indexOf(".")!=-1){
             pos=user.indexOf(".");
            String ch= user.substring(0,pos)+user.substring(pos+1);
            user=ch;
        }

        this.user= user;
    }
    public formularie_des_notes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment formularie_des_notes.
     */
    // TODO: Rename and change types and number of parameters
    public static formularie_des_notes newInstance(String param1, String param2) {
        formularie_des_notes fragment = new formularie_des_notes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_formularie_des_notes, container, false);
        e1 = v.findViewById(R.id.matnote);
        e2 = v.findViewById(R.id.notes);
        btn = v.findViewById(R.id.envfor);
        Calendar calendar=Calendar.getInstance();
        date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        Toolbar toolbar = v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        Notes note = new Notes();

        db = FirebaseDatabase.getInstance("https://sayarti-122d7-default-rtdb.firebaseio.com/").getReference().child(user);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mat = e1.getText().toString().trim();
                String notes = e2.getText().toString().trim();


                if(mat.length()==0|| notes.length()==0 ) {
                    Snackbar.make(getView(), "vérifier que les champs rempli ou vérifier votre correction internet", Snackbar.LENGTH_LONG).show();

                }
                else{

                    note.setMatricule(e1.getText().toString().trim());
                    note.setNote(e2.getText().toString().trim());
                    note.setDate(date);
                    db.push().setValue(note);
                    Snackbar.make(getView(), "LES DONNEES SONT BIEN AJOUTEES", Snackbar.LENGTH_LONG).show();
                }
            }
        });



        return v;
    }


}