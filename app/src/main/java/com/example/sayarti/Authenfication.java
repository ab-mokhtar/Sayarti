package com.example.sayarti;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.concurrent.Executor;

public class Authenfication extends Fragment {
    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;
    private LoginButton mFacebookLoginButton;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DrawerLayout drawerLayout;
    private SignInButton googlesignInButton;
    private GoogleApiClient mGoogleApiClient; // for google sign in
    private CallbackManager mFacebookCallbackManager; // for facebook log in

    private final String TAG = "AuthenticationFragment";

    // for google sign in
    private final String WEB_CLIENT_ID = "719953434368-rfs2vaejcps6pb5e833usd6qrus5gubh.apps.googleusercontent.com";
    private final int GOOGLE_SIGN_IN_REQUEST_CODE = 0;

    // for facebook log in
    private final int FACEBOOK_LOG_IN_REQUEST_CODE = 64206;


    private EditText edittxtEmail, edittxtPwd;
    private CheckBox mCheck;
    private SharedPreferences mPer;
    private SharedPreferences.Editor mEdit;


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                case R.id.google_btn:
                    signInWithGoogleSignIn();
                    break;
            }
        }
    };
    public Authenfication() {
        // Required empty public constructor
    }

    public static Authenfication newInstance() {
        Authenfication fragment = new Authenfication();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initFBAuthentication();
        initFBGoogleSignIn();
        initFBAuthState();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.authentification_frag, container,false);


        //facebook auth
        mFacebookLoginButton = (LoginButton) v.findViewById(R.id.fb_btn);
        initFBFacebookLogIn();

        //GOOGLE AUTH
        googlesignInButton = (SignInButton) v.findViewById(R.id.google_btn);
        //initFBGoogleSignIn();
        googlesignInButton.setOnClickListener(mOnClickListener);


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


//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthStateListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthStateListener != null) {
//            mAuth.removeAuthStateListener(mAuthStateListener);
//        }
//    }
@Override
public void onPause() {
    super.onPause();

    mGoogleApiClient.stopAutoManage(getActivity());
    mGoogleApiClient.disconnect();
}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                Snackbar.make(getView(), "Erreur", Snackbar.LENGTH_LONG).show();
            }
        } else if (requestCode == FACEBOOK_LOG_IN_REQUEST_CODE) {
            mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initFBAuthentication() {
        mAuth = FirebaseAuth.getInstance();
    }

    private void initFBAuthState() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                String message;
                if (firebaseUser != null) {
                    message = "onAuthStateChanged signed in : " + firebaseUser.getUid();
                } else {
                    message = "onAuthStateChanged signed out";
                }
                Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
            }
        };
    }

    // [START auth_with_google]
    private void initFBGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(WEB_CLIENT_ID)
                .requestEmail()
                .build();

        Context context = getContext();
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(getActivity(), new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Snackbar.make(getView(), connectionResult.getErrorMessage(), Snackbar.LENGTH_LONG).show();

                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
    }

    private void signInWithGoogleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(Authenfication.this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String message;
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getContext(), notes_home.class));
                            message = "Bienvenu "+task.getResult().getUser().getDisplayName();
                        } else {
                            message = "Erreur de se connecté";
                        }
                        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(getView(), e.getMessage(), Snackbar.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });
    }
    // [END auth_with_google]

    // [START auth_with_facebook]
    private void initFBFacebookLogIn() {
        mFacebookCallbackManager = CallbackManager.Factory.create();
        mFacebookLoginButton.setFragment(this);
        mFacebookLoginButton.setReadPermissions("email", "public_profile");
        mFacebookLoginButton.registerCallback(mFacebookCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Snackbar.make(getView(), "Connecté", Snackbar.LENGTH_LONG).show();
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Snackbar.make(getView(), "Annuler", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Snackbar.make(getView(), "error : " + error.getMessage(), Snackbar.LENGTH_LONG).show();

            }

        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(Authenfication.this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String message;
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getContext(), notes_home.class));
                            message = "Bienvenu "+task.getResult().getUser().getDisplayName();
                        } else {
                            message = "Erreur de se connecté";
                        }
                        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
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
