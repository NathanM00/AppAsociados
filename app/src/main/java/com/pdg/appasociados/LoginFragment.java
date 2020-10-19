package com.pdg.appasociados;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LoginFragment extends Fragment {
    View vista;

    EditText et_correo;
    EditText et_contra;
    Button btn_iniciar;

    TextView tv_Rasociado;
    TextView tv_RCompa;

    FirebaseAuth auth;

    Bundle bundle = new Bundle();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_login, container, false);


        auth = FirebaseAuth.getInstance();

        et_correo = vista.findViewById(R.id.et_correo);
        et_contra = vista.findViewById(R.id.et_contra);
        btn_iniciar = vista.findViewById(R.id.btn_iniciar);

        tv_Rasociado = vista.findViewById(R.id.tv_Rasociado);
        tv_RCompa = vista.findViewById(R.id.tv_RCompa);

        botonesLogin();

        return vista;

    }

    public void botonesLogin(){
        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        tv_Rasociado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new AsocRegistFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();

            }
        });

        tv_RCompa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new CompaRegistFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();

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

                    startActivity( new Intent(getActivity(), MainActivity.class));
                    Toast.makeText(getActivity(), "Login exitoso", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(getActivity(), "Login Fallido", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
