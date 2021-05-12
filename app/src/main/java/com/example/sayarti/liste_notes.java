package com.example.sayarti;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class liste_notes extends Fragment {


    public liste_notes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView =inflater.inflate(R.layout.fragment_liste_notes, container, false);
        String user = FirebaseAuth.getInstance().getUid();
        final ArrayList<HashMap<String,String>> list= new ArrayList();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://sayarti-122d7-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference(Objects.requireNonNull(user));
        String[]from={"matricule","note","date"};
        int[] to= { R.id.titreview, R.id.descr,R.id.Date};
        //LogAdapter logAdapter = new LogAdapter (this, Log, LogImg);
        final ListView list1 = RootView.findViewById(R.id.ListeView1);
        final SimpleAdapter adapter = new SimpleAdapter(Objects.requireNonNull(getActivity()).getBaseContext(),list,R.layout.ligne, from, to);
        list1.setAdapter(adapter);
        list1.setOnItemLongClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
            builder.setTitle("Sélection Item");
            builder.setMessage(list.get(position).get("note")+"\n"+list.get(position).get("date"));
            builder.setCancelable(true);
            builder.setPositiveButton("ok", null).show();

            return true;
        });

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                HashMap<String,String>Log= new HashMap<>();
                String matricule = Objects.requireNonNull(snapshot.child("matricule").getValue()).toString();
                String note = Objects.requireNonNull(snapshot.child("note").getValue()).toString();
                String date = Objects.requireNonNull(snapshot.child("date").getValue()).toString();

                Log.put("matricule", matricule);
                Log.put("note", note);
                Log.put("date", date);
                list.add(Log);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // Inflate the layout for this fragment
        return RootView;


    }

}