package com.example.sayarti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FragmentSignUp extends Fragment {

    DatabaseReference db;
    private EditText Nom;
    private EditText PasswordInpSignUP;
    private EditText Mat;
    private Button btnReg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        User user;
        View v = inflater.inflate(R.layout.signup, container, false);
        Nom = v.findViewById(R.id.signUpNom);
        PasswordInpSignUP = v.findViewById(R.id.signUpMDP);
        Mat = v.findViewById(R.id.signUpMat);
        btnReg = v.findViewById(R.id.SignUpbtn);
        user = new User();
        db = FirebaseDatabase.getInstance("https://sayarti-122d7-default-rtdb.firebaseio.com/").getReference().child("User");
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mat = Mat.getText().toString().trim();
                String nom = Nom.getText().toString().trim();
                String mdp = PasswordInpSignUP.getText().toString().trim();
                if(mat.length()==0|| nom.length()==0 || mdp.length()==0) {
                    Toast.makeText(getContext(), "VERFIER VOS CHAMPS", Toast.LENGTH_SHORT).show();
                }
                else{
                    user.setMat(Mat.getText().toString().trim());
                    user.setNom(Nom.getText().toString().trim());
                    user.setMdp(PasswordInpSignUP.getText().toString().trim());
                    db.push().setValue(user);
                    Toast.makeText(getContext(), "VOTRE COMPTE EST BIEN CREE", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }



}
