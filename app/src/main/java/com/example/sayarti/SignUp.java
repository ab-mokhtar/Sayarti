package com.example.sayarti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private EditText Nom;
    private EditText PasswordInpSignUP;
    private EditText Mat;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        mAuth = FirebaseAuth.getInstance();


        TextView registerButton = (Button) findViewById(R.id.authen);
        registerButton.setOnClickListener(this);
        Mat = findViewById(R.id.signUpMat);
        Nom = findViewById(R.id.signUpNom);
        PasswordInpSignUP = findViewById(R.id.signUpMDP);

    }
    @Override
    //action onclick
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.SignUpbtn)
        {
            RegisterUser();
        }
    }

    private void RegisterUser() {
        final String password = PasswordInpSignUP.getText().toString().trim();
        final String nom = Nom.getText().toString().trim();
        final String mat = Mat.getText().toString().trim();
        //test sur les données
        if (nom.isEmpty()) {
            Nom.setError("ENTEZ VOTRE NOM");
            Nom.requestFocus();
        }
        if (mat.isEmpty()) {
            Mat.setError("ENTREZ VOTRE MATRICULE");
            Mat.requestFocus();
        }
        if (password.isEmpty()) {
            PasswordInpSignUP.setError("ENTREZ VOTRE MOT DE PASSE");
            PasswordInpSignUP.requestFocus();
        }
        if (password.length() < 6) {
            PasswordInpSignUP.setError("la longueur minimale du mot de passe doit être de 6 caractères");
            PasswordInpSignUP.requestFocus();
        }
        //ceation d'un compte avec firebase methode createUserWithEmailAndPassword
        mAuth.createUserWithEmailAndPassword(mat, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (task.isSuccessful()) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.SignUpFrag,new Authenfication()).addToBackStack(null).commit();
                        Toast.makeText(SignUp.this, "VOTRE COMPTE ETE BIEN CREE", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(SignUp.this, "CREATION ECHOUER", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(SignUp.this, "CREATION ECHOUER", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}
