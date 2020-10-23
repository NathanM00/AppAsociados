package com.pdg.appasociados;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(( new Intent(getApplicationContext(), MainActivity.class)));

        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contenedor, new LoginFragment()).commit();

        }
    }
}
