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

public class SavingsFragment extends Fragment {

    ImageView btn_back;
    CardView btn_fonaviEmprendedor, btn_fonaviAhorrito, btn_cdat, btn_ahorroProgramadoVe, btn_ahorroProgramadoVi, btn_ahorroProgramadoV, btn_ahorroProgramadoE;
    View vista;
    Bundle bundle = new Bundle();
    FirebaseDatabase db;
    DatabaseReference ref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_ahorros, container, false);

        btn_back = vista.findViewById(R.id.btn_back);
        btn_ahorroProgramadoE = vista.findViewById(R.id.btn_ahorroProgramadoE);
        btn_ahorroProgramadoV = vista.findViewById(R.id.btn_ahorroProgramadoV);
        btn_ahorroProgramadoVi= vista.findViewById(R.id.btn_ahorroProgramadoVi);
        btn_ahorroProgramadoVe = vista.findViewById(R.id.btn_ahorroProgramadoVe);
        btn_cdat = vista.findViewById(R.id.btn_cdat);
        btn_fonaviAhorrito = vista.findViewById(R.id.btn_fonaviAhorrito);
        btn_fonaviEmprendedor = vista.findViewById(R.id.btn_fonaviEmprendedor);


        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Beneficios").child("AhorroVoluntario");

       obtenerDatos();

        return vista;
    }

    private void obtenerDatos(){
        final Saving[] ahorros = new Saving[7];

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ahorros[0] = dataSnapshot.child("ahorroProgramadoE").getValue(Saving.class);
                ahorros[1] = dataSnapshot.child("ahorroProgramadoV").getValue(Saving.class);
                ahorros[2] = dataSnapshot.child("ahorroProgramadoVe").getValue(Saving.class);
                ahorros[3] = dataSnapshot.child("ahorroProgramadoVi").getValue(Saving.class);
                ahorros[4] = dataSnapshot.child("cdat").getValue(Saving.class);
                ahorros[5] = dataSnapshot.child("fonaviAhorrito").getValue(Saving.class);
                ahorros[6] = dataSnapshot.child("fonaviEmprendedor").getValue(Saving.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });


        btn_fonaviEmprendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", ahorros[6].nombre);
                bundle.putString("subtitulo", ahorros[6].nombre+"?");
                bundle.putString("descripcion", ahorros[6].descripcion);
                bundle.putString("CMDA", ahorros[6].CMDA);
                bundle.putString("CMDC", ahorros[6].CMDC);
                bundle.putString("R30", ahorros[6].R30);
                bundle.putString("R365", ahorros[6].R365);

                Fragment fragment = new SavingFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_fonaviAhorrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", ahorros[5].nombre);
                bundle.putString("subtitulo", ahorros[5].nombre+"?");
                bundle.putString("descripcion", ahorros[5].descripcion);
                bundle.putString("CMDA", ahorros[5].CMDA);
                bundle.putString("CMDC", ahorros[5].CMDC);
                bundle.putString("R30", ahorros[5].R30);
                bundle.putString("R365", ahorros[5].R365);

                Fragment fragment = new SavingFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

            btn_cdat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Fragment fragment = new CreditFormFragment();
                    bundle.putString("titulo", ahorros[4].nombre);
                    bundle.putString("subtitulo", ahorros[4].nombre+"?");
                    bundle.putString("descripcion", ahorros[4].descripcion);
                    bundle.putString("CMDA", ahorros[4].CMDA);
                    bundle.putString("CMDC", ahorros[4].CMDC);
                    bundle.putString("R30", ahorros[4].R30);
                    bundle.putString("R365", ahorros[4].R365);

                    Fragment fragment = new SavingFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contenedor, fragment);
                    transaction.commit();
                }
            });

            btn_ahorroProgramadoVi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Fragment fragment = new CreditFormFragment();
                    bundle.putString("titulo", ahorros[3].nombre);
                    bundle.putString("subtitulo", ahorros[3].nombre+"?");
                    bundle.putString("descripcion", ahorros[3].descripcion);
                    bundle.putString("CMDA", ahorros[3].CMDA);
                    bundle.putString("CMDC", ahorros[3].CMDC);
                    bundle.putString("R30", ahorros[3].R30);
                    bundle.putString("R365", ahorros[3].R365);

                    Fragment fragment = new SavingFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contenedor, fragment);
                    transaction.commit();
                }
            });

        btn_ahorroProgramadoVe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Fragment fragment = new CreditFormFragment();
                    bundle.putString("titulo", ahorros[2].nombre);
                    bundle.putString("subtitulo", ahorros[2].nombre+"?");
                    bundle.putString("descripcion", ahorros[2].descripcion);
                    bundle.putString("CMDA", ahorros[2].CMDA);
                    bundle.putString("CMDC", ahorros[2].CMDC);
                    bundle.putString("R30", ahorros[2].R30);
                    bundle.putString("R365", ahorros[2].R365);

                    Fragment fragment = new SavingFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contenedor, fragment);
                    transaction.commit();
                }
            });

        btn_ahorroProgramadoV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Fragment fragment = new CreditFormFragment();
                    bundle.putString("titulo", ahorros[1].nombre);
                    bundle.putString("subtitulo", ahorros[1].nombre+"?");
                    bundle.putString("descripcion", ahorros[1].descripcion);
                    bundle.putString("CMDA", ahorros[1].CMDA);
                    bundle.putString("CMDC", ahorros[1].CMDC);
                    bundle.putString("R30", ahorros[1].R30);
                    bundle.putString("R365", ahorros[1].R365);

                    Fragment fragment = new SavingFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contenedor, fragment);
                    transaction.commit();
                }
            });

        btn_ahorroProgramadoE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Fragment fragment = new CreditFormFragment();
                    bundle.putString("titulo", ahorros[0].nombre);
                    bundle.putString("subtitulo", ahorros[0].nombre+"?");
                    bundle.putString("descripcion", ahorros[0].descripcion);
                    bundle.putString("CMDA", ahorros[0].CMDA);
                    bundle.putString("CMDC", ahorros[0].CMDC);
                    bundle.putString("R30", ahorros[0].R30);
                    bundle.putString("R365", ahorros[0].R365);

                    Fragment fragment = new SavingFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contenedor, fragment);
                    transaction.commit();
                }
            });
        }

}
