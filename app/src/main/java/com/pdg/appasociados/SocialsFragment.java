package com.pdg.appasociados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SocialsFragment extends Fragment {

    ImageView btn_back;
    CardView btn_adultosMayores, btn_bonoNavideno, btn_centroMulti, btn_convenios, btn_cultura;
    CardView btn_educacion, btn_planReferido, btn_recreacionYDeportes, btn_voluntariadoJuvenil;
    View vista;
    Bundle bundle = new Bundle();
    FirebaseDatabase db;
    DatabaseReference ref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_sociales, container, false);

        btn_back = vista.findViewById(R.id.btn_back);
        btn_adultosMayores = vista.findViewById(R.id.btn_adultosMayores);
        btn_bonoNavideno = vista.findViewById(R.id.btn_bonoNavideno);
        btn_centroMulti = vista.findViewById(R.id.btn_centroMulti);
        btn_convenios = vista.findViewById(R.id.btn_convenios);
        btn_cultura = vista.findViewById(R.id.btn_cultura);
        btn_educacion = vista.findViewById(R.id.btn_educacion);
        btn_planReferido = vista.findViewById(R.id.btn_planReferido);
        btn_recreacionYDeportes = vista.findViewById(R.id.btn_recreacionYDeportes);
        btn_voluntariadoJuvenil = vista.findViewById(R.id.btn_voluntariadoJuvenil);

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Beneficios").child("comiteBSocial");

       obtenerDatos();

        return vista;
    }

    private void obtenerDatos(){
        final Social[] sociales = new Social[9];

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sociales[0] = dataSnapshot.child("adultosMayores").getValue(Social.class);
                sociales[1] = dataSnapshot.child("bonoNavideno").getValue(Social.class);
                sociales[2] = dataSnapshot.child("centroMulti").getValue(Social.class);
                sociales[3] = dataSnapshot.child("convenios").getValue(Social.class);
                sociales[4] = dataSnapshot.child("cultura").getValue(Social.class);
                sociales[5] = dataSnapshot.child("educacion").getValue(Social.class);
                sociales[6] = dataSnapshot.child("planReferido").getValue(Social.class);
                sociales[7] = dataSnapshot.child("recreacionYDeportes").getValue(Social.class);
                sociales[8] = dataSnapshot.child("voluntariadoJuvenil").getValue(Social.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });

        btn_voluntariadoJuvenil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", sociales[8].nombre);
                bundle.putString("subtitulo", sociales[8].nombre+"?");
                bundle.putString("descripcion", sociales[8].descripcion);

                Fragment fragment = new SocialFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_recreacionYDeportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", sociales[7].nombre);
                bundle.putString("subtitulo", sociales[7].nombre+"?");
                bundle.putString("descripcion", sociales[7].descripcion);

                Fragment fragment = new SocialFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_planReferido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", sociales[6].nombre);
                bundle.putString("subtitulo", sociales[6].nombre+"?");
                bundle.putString("descripcion", sociales[6].descripcion);

                Fragment fragment = new SocialFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_educacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", sociales[5].nombre);
                bundle.putString("subtitulo", sociales[5].nombre+"?");
                bundle.putString("descripcion", sociales[5].descripcion);

                Fragment fragment = new SocialFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_cultura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", sociales[4].nombre);
                bundle.putString("subtitulo", sociales[4].nombre+"?");
                bundle.putString("descripcion", sociales[4].descripcion);

                Fragment fragment = new SocialFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_convenios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", sociales[3].nombre);
                bundle.putString("subtitulo", sociales[3].nombre+"?");
                bundle.putString("descripcion", sociales[3].descripcion);
                bundle.putString("convenios", sociales[3].convenios);

                Fragment fragment = new SocialFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_centroMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", sociales[2].nombre);
                bundle.putString("subtitulo", sociales[2].nombre+"?");
                bundle.putString("descripcion", sociales[2].descripcion);

                Fragment fragment = new SocialFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_bonoNavideno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", sociales[1].nombre);
                bundle.putString("subtitulo", sociales[1].nombre+"?");
                bundle.putString("descripcion", sociales[1].descripcion);

                Fragment fragment = new SocialFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_adultosMayores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", sociales[0].nombre);
                bundle.putString("subtitulo", sociales[0].nombre+"?");
                bundle.putString("descripcion", sociales[0].descripcion);

                Fragment fragment = new SocialFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });
    }

}
