package com.example.sayarti;

import android.content.Intent;
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


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FragmentSignUp extends Fragment {
    private FirebaseAuth mAuth;
    DatabaseReference db;
    private EditText mail;
    private EditText PasswordInpSignUP;
    private EditText Mat;
    private Button btnReg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.signup, container, false);
        mail = v.findViewById(R.id.signUpMail);
        PasswordInpSignUP = v.findViewById(R.id.signUpMDP);
        Mat = v.findViewById(R.id.signUpMat);
        btnReg = v.findViewById(R.id.SignUpbtn);
        mAuth = FirebaseAuth.getInstance();

        User u = new User();
        db = FirebaseDatabase.getInstance("https://sayarti-122d7-default-rtdb.firebaseio.com/").getReference().child("User");
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mat = Mat.getText().toString().trim();
                String email = mail.getText().toString().trim();
                String mdp = PasswordInpSignUP.getText().toString().trim();
                if(mat.length()==0|| email.length()==0 || mdp.length()==0) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content), "VERIFIER VOS CHAMPS", Snackbar.LENGTH_SHORT).show();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(email, mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null)
                                    {
                                        // The user's ID, unique to the Firebase project. Do NOT use this value to
                                        // authenticate with your backend server, if you have one. Use
                                        // FirebaseUser.getIdToken() instead.
                                        String uid = user.getUid();
                                        u.setIdUnique(uid);
                                        u.setMat(Mat.getText().toString().trim());
                                        db.push().setValue(u);
                                    }
                                    Snackbar.make(getActivity().findViewById(android.R.id.content), "VOTRE COMPTE ETE BIEN CREER", Snackbar.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Snackbar.make(getActivity().findViewById(android.R.id.content), "CREATION ECHOUER", Snackbar.LENGTH_SHORT).show();
                                }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String error = e.getMessage().toUpperCase();
                            Snackbar.make(getActivity().findViewById(android.R.id.content), error, Snackbar.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return v;
    }



}
