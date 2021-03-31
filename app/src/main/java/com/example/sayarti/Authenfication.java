package com.example.sayarti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Authenfication extends Fragment {
    private FirebaseAuth mAuth;
    private EditText edittxtEmail, edittxtPwd;
    private Button sign;
    private CheckBox mCheck;
    private SharedPreferences mPer;
    private SharedPreferences.Editor mEdit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.authentification_frag, container,false);
        edittxtEmail = v.findViewById(R.id.authenMAT);
        edittxtPwd = v.findViewById(R.id.authenMDP);
        mAuth = FirebaseAuth.getInstance();
        sign = v.findViewById(R.id.authen);
        mCheck = v.findViewById(R.id.check);

        mPer = PreferenceManager.getDefaultSharedPreferences(getContext());
        mEdit = mPer.edit();
        checksharedper();

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mCheck.isChecked())
                {
                    mEdit.putString(getString(R.string.checkbox),"True");
                    mEdit.commit();

                    //sauvgarder le nom
                    String name = edittxtEmail.getText().toString();
                    mEdit.putString(getString(R.string.mail),name);
                    mEdit.commit();

                    //sauvgarder le MDP
                    String password = edittxtPwd.getText().toString();
                    mEdit.putString(getString(R.string.pass),password);
                    mEdit.commit();
                }
                else
                {
                    mEdit.putString(getString(R.string.checkbox),"False");
                    mEdit.commit();

                    //sauvgarder le nom
                    mEdit.putString(getString(R.string.mail),"");
                    mEdit.commit();

                    //sauvgarder le MDP
                    mEdit.putString(getString(R.string.pass),"");
                    mEdit.commit();
                }
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
                    Snackbar.make(getView(), "Connexion réussie", Snackbar.LENGTH_LONG).show();
                    startActivity(new Intent(getContext(), notes_home.class));


                } else {
                    Snackbar.make(getView(), "échec de la connexion, veuillez vérifier vos informations d'identification et réessayer", Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }

    private void checksharedper()
    {
        String checkbox  = mPer.getString(getString(R.string.checkbox),"False");
        String Mail  = mPer.getString(getString(R.string.mail),"");
        String pass  = mPer.getString(getString(R.string.pass),"");

        edittxtEmail.setText(Mail);
        edittxtPwd.setText(pass);
        if (checkbox.equals("True"))
        {
            mCheck.setChecked(true);
        }
        else
        {
            mCheck.setChecked(false);
        }
    }

}
