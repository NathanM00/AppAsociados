package com.pdg.appasociados;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    TextView tv_saludo;

    DatabaseReference ref;
    DatabaseReference refe;
    FirebaseAuth auth;
    FirebaseDatabase db;

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    Button btnView;
    View vista;
    User usuarioActual;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_home, container, false);

        models = new ArrayList<>();
        models.add(new Model("Creditos nuevos", "Los nuevos creditos te poermitiran hacer cosas grandiosas"));
        models.add(new Model("Nuevos destinos", "Los nuevos creditos te poermitiran hacer cosas grandiosas"));
        models.add(new Model("Clases de pintura", "Los nuevos creditos te poermitiran hacer cosas grandiosas"));
        models.add(new Model("Salida de pesca", "Los nuevos creditos te poermitiran hacer cosas grandiosas"));

        adapter = new Adapter(models, vista.getContext());

        tv_saludo = vista.findViewById(R.id.tv_saludo);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("usuarios");

        viewPager = vista.findViewById(R.id.ViewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        getNombre();

        return vista;

    }

    //Metodo para conseguir el nombre del usuario logeado
    private void getNombre() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.child(auth.getUid()).child("nombre").getValue().toString();
                String userMail = dataSnapshot.child(auth.getUid()).child("correo").getValue().toString();
                Boolean userStatus = (Boolean) dataSnapshot.child(auth.getUid()).child("asociado").getValue();
                String userPass = dataSnapshot.child(auth.getUid()).child("contraseña").getValue().toString();

                Log.d("zzz",userName);
                Log.d("zzz",userMail);

                tv_saludo.setText("Hola, " +userName);
            }

            //  asasd
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

                tv_saludo.setText(nombre);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });
    }

}
