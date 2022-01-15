package com.gdsciiita.ontimepro.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gdsciiita.ontimepro.R;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    Button logout;
    TextView name,email;
    String authToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        logout = findViewById(R.id.logOut);
        logout.setOnClickListener(view -> signOut());

//        getAuthToken();
    }


//    private void getAuthToken(){
//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        if(currentUser!=null) {
//            Handler h = new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    if (msg.what == 0) {
//                        name.setText(authToken);
//                    }
//                }
//            };
//
//            Runnable runnable = () -> {
//                try {
//                    String scope = "oauth2:" + getString(R.string.auth_scope);
//                    authToken = GoogleAuthUtil.getToken(getApplicationContext(), currentUser.getEmail(), scope, new Bundle());
//                    h.sendEmptyMessage(0);
//                } catch (IOException | GoogleAuthException e) {
//                    e.printStackTrace();
//                }
//            };
//
//            AsyncTask.execute(runnable);
//        }
//    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}