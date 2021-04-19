package com.example.sayarti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.concurrent.Executor;

public class Authenfication extends Fragment {
    private FirebaseAuth mAuth;
    private static final String TAG = "Sayarti";
    private CallbackManager callbackManager;
    private LoginButton loginButton;


    private EditText edittxtEmail, edittxtPwd;
    private CheckBox mCheck;
    private SharedPreferences mPer;
    private SharedPreferences.Editor mEdit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.authentification_frag, container,false);
        //facebook auth
        loginButton = v.findViewById(R.id.login_button);

        mAuth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackManager = CallbackManager.Factory.create();
                loginButton.setPermissions("email","public_profile");
                loginButton.setFragment(Authenfication.this);
                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Snackbar.make(getView(), "Annuler", Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Snackbar.make(getView(), "erreur "+error.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });


        edittxtEmail = v.findViewById(R.id.authenMAT);
        edittxtPwd = v.findViewById(R.id.authenMDP);
        mAuth = FirebaseAuth.getInstance();
        Button sign = v.findViewById(R.id.authen);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getContext(), notes_home.class));
                        }
                        else
                        {
                            // If sign in fails, display a message to the user.
                            Snackbar.make(getView(), "erreur "+task.getException().getMessage(), Snackbar.LENGTH_LONG).show();
                        }

                    }
                });
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
        mCheck.setChecked(checkbox.equals("True"));
    }

}
