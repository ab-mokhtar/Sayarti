package com.example.sayarti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class FragmentSignUp extends Fragment {
    private FirebaseAuth mAuth;
    DatabaseReference db;
    private EditText Email,mdp1,mdp2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        User user;
        View v = inflater.inflate(R.layout.signup, container, false);
        Email = v.findViewById(R.id.signUpMail);
        mdp1 = v.findViewById(R.id.signUpMDP1);
        mdp2 = v.findViewById(R.id.signUpMDP2);
        Button btnReg = v.findViewById(R.id.SignUpbtn);
        mAuth = FirebaseAuth.getInstance();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String pass1 = mdp1.getText().toString().trim();
                String pass2 = mdp2.getText().toString().trim();
                if(email.length()==0|| pass1.length()==0 || pass2.length()==0) {

                    Snackbar.make(getView(), "VERIFIER VOS CHAMPS", Snackbar.LENGTH_LONG).show();
                }
                if(!pass1.equals(pass2))
                {
                    Snackbar.make(getView(), "les mots de passe ne correspondent pas", Snackbar.LENGTH_LONG).show();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(email, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (task.isSuccessful()) {
                                    Snackbar.make(getView(),"votre compte était bien créé", Snackbar.LENGTH_LONG).show();
                                      } else {
                                    Snackbar.make(getView(),"Création échoué! veuillez réessayer", Snackbar.LENGTH_LONG).show();
                                }
                            } else {
                                Snackbar.make(getView(),"Création échouée", Snackbar.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });
        return v;
    }



}
