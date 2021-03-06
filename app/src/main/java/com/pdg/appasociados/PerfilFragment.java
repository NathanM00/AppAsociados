package com.pdg.appasociados;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilFragment extends Fragment {

    View vista;

    TextView tv_nombre, txt_puntos;

    Button btn_salir;
    Button btn_compa;

    FirebaseAuth auth;
    FirebaseDatabase db;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference context;
    DatabaseReference ref;
    DatabaseReference refe;

    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_profile, container, false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("usuarios").child(auth.getUid());
        refe = db.getReference("Acompanantes").child(auth.getUid());
        context = db.getReference("usuarios");
        String place = context.toString();

        tv_nombre = vista.findViewById(R.id.txt_profileName);
        txt_puntos = vista.findViewById(R.id.txt_puntos);

        //tv_nombre.setText(user.getUid());

        btn_salir = vista.findViewById(R.id.btn_salir);
        btn_compa = vista.findViewById(R.id.btn_compa);

        botonesPerfil();

        System.out.println(place);

        getNombre();

        //getNombreCompa();

        return vista;
    }

    private void botonesPerfil (){

        //Boton para cerrar sesion
        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                //intent.putExtra("algo", "algo mas");
                startActivity(intent);
            }
        });

        //Boton para agregar nuevo acompanante
        btn_compa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CompanionFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

    }

    //Metodo para conseguir el nombre del usuario logeado
    private void getNombre() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                InfoUser info = dataSnapshot.getValue(InfoUser.class);
                String nombre = info.getNombre();

                tv_nombre.setText(nombre);

                String puntos = dataSnapshot.child("puntos").getValue().toString();
                txt_puntos.setText(puntos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });

    }

    public void getNombreCompa(){
        refe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                InfoUser info = snapshot.getValue(InfoUser.class);
                String nombre = info.getNombre();

                tv_nombre.setText(nombre);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });
    }

}
