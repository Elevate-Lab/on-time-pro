package com.gdsciiita.ontimepro.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;

import com.gdsciiita.ontimepro.GoogleSignInObject;
import com.gdsciiita.ontimepro.R;
import com.gdsciiita.ontimepro.classes.User;
import com.gdsciiita.ontimepro.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;
    private FirebaseAuth mAuth;
    private String TAG = "SPECIAL_TAG";

    private ActivityLoginBinding binding;
    private String progressString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        mAuth = FirebaseAuth.getInstance();


        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignInObject.Companion.getSignInClient(this);

        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(view -> signIn());
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
                updateProgress(1);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.d(TAG, "Google sign in failed", e);
                updateProgress(2);
            }

        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        updateProgress(3);
                        FirebaseUser user = mAuth.getCurrentUser();
                        getAuthToken(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d(TAG, "signInWithCredential:failure", task.getException());
                        updateProgress(4);
                        updateUI(null);
                    }
                });
    }

    private void getAuthToken(FirebaseUser currentUser){
        if(currentUser!=null) {
           new GetTokenAsync().execute(currentUser, currentUser);
        }
    }

    private void updateProgress(int step){
        progressString = progressString+" "+step;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        getAuthToken(currentUser);
    }


    private void updateUI(FirebaseUser currentUser) {
        if(currentUser != null) {
            binding.steps.setText(progressString);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    class GetTokenAsync extends AsyncTask<FirebaseUser, Void, FirebaseUser>{

        @Override
        protected FirebaseUser doInBackground(FirebaseUser... values) {
            FirebaseUser currentUser = values[0];
            List<String> Scopes = Arrays.asList(getResources().getStringArray(R.array.auth_scopes));
            String authScope = "oauth2:" + TextUtils.join(" ", Scopes);
            Account accountDetails = new Account(currentUser.getEmail(), GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
            try {
                User.authToken = "Bearer "+ GoogleAuthUtil.getToken(getApplicationContext(), accountDetails, authScope, new Bundle());
            } catch (IOException | GoogleAuthException e) {
                e.printStackTrace();
            }
            User.userName = currentUser.getDisplayName();
            User.firebaseToken = currentUser.getIdToken(true).toString();
            User.userEmail = currentUser.getEmail();
            return values[0];
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(FirebaseUser currentUser) {
            super.onPostExecute(currentUser);
            Log.d(TAG, "Received Auth Token");
            updateProgress(5);
            updateUI(currentUser);
        }
    }
}