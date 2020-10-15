package com.pdg.appasociados;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/*
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;*/

public class LoginActivity extends Activity {


    EditText et_correo;
    EditText et_contra;
    Button btn_iniciar;

    FirebaseAuth auth;
    //FirebaseDatabase db;
    //FirebaseUser usuario;
    //  FirebaseDatabase db;
    //  FirebaseAuth auth;

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        //db = FirebaseDatabase.getInstance();
        //usuario = auth.getCurrentUser();

        et_correo = findViewById(R.id.et_correo);
        et_contra = findViewById(R.id.et_contra);
        btn_iniciar = findViewById(R.id.btn_iniciar);
        //  db = FirebaseDatabase.getInstance();
        //  auth = FirebaseAuth.getInstance();

        /*if(usuario != null){

            finish();
            startActivity( new Intent(LoginActivity.this, MainActivity.class));
        }*/

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });

    }

    private void login() {
        String correo = et_correo.getText().toString().trim();
        String contra = et_contra.getText().toString().trim();


        if(correo.isEmpty()){
            et_correo.setError("Por favor ingresar un correo valido");
            et_correo.requestFocus();
            return;
        }

        if(contra.isEmpty()){
            et_contra.setError("Por favor ingresar contrasena");
            et_contra.requestFocus();
            return;
        }

        auth.signInWithEmailAndPassword(correo,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    startActivity( new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(LoginActivity.this, "Login Fallido", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}