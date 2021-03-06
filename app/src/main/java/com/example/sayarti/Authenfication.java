package com.example.sayarti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Authenfication extends Fragment {
    private FirebaseAuth mAuth;
    private EditText edittxtEmail, edittxtPwd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.authentification_frag, container,false);
        edittxtEmail = v.findViewById(R.id.authenMAT);
        edittxtPwd = v.findViewById(R.id.authenMDP);
        mAuth = FirebaseAuth.getInstance();
        Button sign = v.findViewById(R.id.authen);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        return v;
    }
    private void userLogin(){
        String email = edittxtEmail.getText().toString().trim();
        String password = edittxtPwd.getText().toString().trim();

        //sign in with firebase
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(),"Login successful",Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_frag, new formularie_des_notes(email)).commit();

                } else {
                    Toast.makeText(getContext(), "échec de la connexion, veuillez vérifier vos informations d'identification et réessayer", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
