package com.pdg.appasociados;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class PerfilFragment extends Fragment {

    View vista;

    Button btn_salir;

    FirebaseAuth auth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_profile, container, false);

        auth = FirebaseAuth.getInstance();

        btn_salir = vista.findViewById(R.id.btn_salir);

        botonesPerfil();

        return vista;
    }

    private void botonesPerfil (){

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                //intent.putExtra("algo", "algo mas");
                startActivity(intent);
            }
        });

    }
}