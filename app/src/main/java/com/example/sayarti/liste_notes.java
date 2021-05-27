package com.example.sayarti;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static com.facebook.FacebookSdk.getApplicationContext;


public class liste_notes extends Fragment {

    DatabaseReference myRef;

    //public String key ;
    public ArrayList<String> Arraykey = new ArrayList();


    public liste_notes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView =inflater.inflate(R.layout.fragment_liste_notes, container, false);
        String user = FirebaseAuth.getInstance().getUid();




        FirebaseDatabase database = FirebaseDatabase.getInstance("https://sayarti-122d7-default-rtdb.firebaseio.com/");
        myRef = database.getReference(Objects.requireNonNull(user));
        String[]from={"matricule","note","date"};
        int[] to= { R.id.titreview, R.id.descr,R.id.Date};
        //LogAdapter logAdapter = new LogAdapter (this, Log, LogImg);
        //final ListView list1 = RootView.findViewById(R.id.ListeView1);
        final SwipeMenuListView list1 = RootView.findViewById(R.id.ListeView1);
        final ArrayList<HashMap<String,String>> list= new ArrayList();
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

                //child key
                Arraykey.add(snapshot.getKey());



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

        ImageView c = RootView.findViewById(R.id.clear);
        ListView l = RootView.findViewById(R.id.ListeView1);
        c.setOnClickListener(v -> {
            if(adapter.getCount()==0)
            {
                Snackbar.make(Objects.requireNonNull(getView()), "Rien à supprimer !", Snackbar.LENGTH_SHORT).show();

            }
            else
            {
                ClearList();
                adapter.notifyDataSetChanged();
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0, 200, 0)));
                // set item width
                openItem.setWidth(170);

                openItem.setIcon(R.drawable.ic_update);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(255, 0, 0)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        list1.setMenuCreator(creator);

        list1.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        //update
                        UpdateNote(Arraykey.get(position));
                        adapter.notifyDataSetChanged();

                        break;
                    case 1:
                        //delete
                        DeleteItem(Arraykey.get(position));
                        adapter.notifyDataSetChanged();

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });


        // Inflate the layout for this fragment
        return RootView;


    }

    private void DeleteItem(String childkey)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle("Supprimer Item");
        builder.setMessage("vous etes sure de supprimer ?");
        builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                myRef.child(childkey).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        myRef.child(childkey).removeValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Snackbar.make(Objects.requireNonNull(getView()), "Annuler", Snackbar.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void ClearList()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle("Supprimer tous les notes");
        builder.setMessage("vous êtes sure de supprimer ?");
        builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Query query = myRef.orderByChild("uId");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snapshot1 : snapshot.getChildren()){
                            snapshot1.getRef().removeValue();
                        }
                        Snackbar.make(Objects.requireNonNull(getView()), "Les notes ont supprimé", Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Snackbar.make(Objects.requireNonNull(getView()), "Annuler", Snackbar.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void UpdateNote(String childKey)
    {


        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.update_dialog,null);
        builder.setView(view).setTitle("Modifier la note").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                EditText upMat = view.findViewById(R.id.edit_mat);
                EditText upNote= view.findViewById(R.id.edit_note);

                HashMap hashMap = new HashMap();
                hashMap.put("note",upNote.getText().toString());
                hashMap.put("matricule",upMat.getText().toString());

                myRef.child(childKey).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Snackbar.make(Objects.requireNonNull(getView()), "La note a été modifiée", Snackbar.LENGTH_SHORT).show();
                    }
                });

            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Snackbar.make(Objects.requireNonNull(getView()), "Annuler", Snackbar.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}