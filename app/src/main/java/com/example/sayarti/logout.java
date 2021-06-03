package com.example.sayarti;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


public class logout extends Fragment {
    private FirebaseAuth mAuth;
    private final String userName;
    private final String userEmail;
    ImageView photo;


    public logout(String userName, String userEmail) {
        // Required empty public constructor
        this.userName = userName;
        this.userEmail = userEmail;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        View v = inflater.inflate(R.layout.fragment_logout, container, false);

        TextView mail = v.findViewById(R.id.mail);
        TextView nom = v.findViewById(R.id.username);
        photo = v.findViewById(R.id.profilimg);
        nom.setText(userName);
        mail.setText(userEmail);
        FirebaseUser u = mAuth.getCurrentUser();
        image(u);

        //Button btn = v.findViewById(R.id.logoutbtn);
        LinearLayout log = v.findViewById(R.id.logoutbtn);
        log.setOnClickListener(v1 -> {
            mAuth.signOut();
            LoginManager.getInstance().logOut();
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        });
        return v;
    }

    private void image(FirebaseUser user)
    {
        if(user != null)
        {
            if(user.getPhotoUrl() != null)
            {
                String photourl = user.getPhotoUrl() + "?type=large";
                Picasso.get().load(photourl).into(photo);
            }
            else
            {
                photo.setImageResource(R.drawable.ic_user);
            }
        }
    }

}