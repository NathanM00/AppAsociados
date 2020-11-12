package com.pdg.appasociados;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pdg.appasociados.form.CreditFormFragment;

import java.util.ArrayList;

public class CreditsFragment extends Fragment {

    ImageView btn_back;
    CardView btn_bonoMercado, btn_carteraInterna, btn_convenios, btn_crediExpress, btn_eduBecas, btn_eduRotativo, btn_eventos, btn_exequial, btn_fomento;
    CardView btn_libreInversion, btn_portafolio1, btn_portafolio2, btn_portafolioAAA, btn_vehiculoConvenio, btn_vehiculoFondo, btn_viviendaGarantia, btn_viviendaOrdinario;
    View vista;
    Bundle bundle = new Bundle();
    FirebaseDatabase db;
    DatabaseReference ref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_creditos, container, false);

        btn_back = vista.findViewById(R.id.btn_back);
        btn_viviendaOrdinario = vista.findViewById(R.id.btn_viviendaOrdinario);
        btn_viviendaGarantia = vista.findViewById(R.id.btn_viviendaGarantia);
        btn_vehiculoConvenio = vista.findViewById(R.id.btn_vehiculoConvenio);
        btn_vehiculoFondo = vista.findViewById(R.id.btn_vehiculoFondo);
        btn_portafolio1 = vista.findViewById(R.id.btn_portafolio1);
        btn_portafolio2 = vista.findViewById(R.id.btn_portafolio2);
        btn_portafolioAAA = vista.findViewById(R.id.btn_portafolioAAA);
        btn_libreInversion = vista.findViewById(R.id.btn_libreInversion);
        btn_fomento = vista.findViewById(R.id.btn_fomento);
        btn_exequial = vista.findViewById(R.id.btn_exequial);
        btn_eventos = vista.findViewById(R.id.btn_eventos);
        btn_eduBecas = vista.findViewById(R.id.btn_eduBecas);
        btn_eduRotativo= vista.findViewById(R.id.btn_eduRotativo);
        btn_crediExpress = vista.findViewById(R.id.btn_crediExpress);
        btn_convenios = vista.findViewById(R.id.btn_convenios);
        btn_carteraInterna = vista.findViewById(R.id.btn_carteraInterna);
        btn_bonoMercado = vista.findViewById(R.id.btn_bonoMercado);


        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Beneficios").child("Creditos");

       obtenerDatos();

        return vista;
    }

    private void obtenerDatos(){
        final Credit[] creditos = new Credit[17];

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                creditos[0] = dataSnapshot.child("bonoMercado").getValue(Credit.class);
                creditos[1] = dataSnapshot.child("carteraInterna").getValue(Credit.class);
                creditos[2] = dataSnapshot.child("convenios").getValue(Credit.class);
                creditos[3] = dataSnapshot.child("crediExpress").getValue(Credit.class);
                creditos[4] = dataSnapshot.child("educativoRotativo").getValue(Credit.class);
                creditos[5] = dataSnapshot.child("educativoBecas").getValue(Credit.class);
                creditos[6] = dataSnapshot.child("eventos").getValue(Credit.class);
                creditos[7] = dataSnapshot.child("exequial").getValue(Credit.class);
                creditos[8] = dataSnapshot.child("fomento").getValue(Credit.class);
                creditos[9]= dataSnapshot.child("libreInversion").getValue(Credit.class);
                creditos[10] = dataSnapshot.child("portafolioEsp1").getValue(Credit.class);
                creditos[11] = dataSnapshot.child("portafolioEsp2").getValue(Credit.class);
                creditos[12] = dataSnapshot.child("portafolioEspAAA").getValue(Credit.class);
                creditos[13] = dataSnapshot.child("vehiculoConvenio").getValue(Credit.class);
                creditos[14] = dataSnapshot.child("vehiculoFondo").getValue(Credit.class);
                creditos[15] = dataSnapshot.child("viviendaGarantia").getValue(Credit.class);
                creditos[16] = dataSnapshot.child("viviendaOrdinario").getValue(Credit.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });

        btn_viviendaOrdinario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", creditos[16].nombre);
                bundle.putString("subtitulo", creditos[16].nombre+"?");
                bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                bundle.putString("TANM", creditos[16].TANM);
                bundle.putString("TAAnual", creditos[16].TAAnual);

                Fragment fragment = new CreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_viviendaGarantia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", creditos[15].nombre);
                bundle.putString("subtitulo", creditos[15].nombre+"?");
                bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                bundle.putString("TANM", creditos[15].TANM);
                bundle.putString("TAAnual", creditos[15].TAAnual);

                Fragment fragment = new CreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_vehiculoFondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", creditos[14].nombre);
                bundle.putString("subtitulo", creditos[14].nombre+"?");
                bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                bundle.putString("TANM", creditos[14].TANM);
                bundle.putString("TAAnual", creditos[14].TAAnual);

                Fragment fragment = new CreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_vehiculoConvenio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", creditos[13].nombre);
                bundle.putString("subtitulo", creditos[13].nombre+"?");
                bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                bundle.putString("TANM", creditos[13].TANM);
                bundle.putString("TAAnual", creditos[13].TAAnual);

                Fragment fragment = new CreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_portafolioAAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", creditos[12].nombre);
                bundle.putString("subtitulo", creditos[12].nombre+"?");
                bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                bundle.putString("TANM", creditos[12].TANM);
                bundle.putString("TAAnual", creditos[12].TAAnual);

                Fragment fragment = new CreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_portafolio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", creditos[11].nombre);
                bundle.putString("subtitulo", creditos[11].nombre+"?");
                bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                bundle.putString("TANM", creditos[11].TANM);
                bundle.putString("TAAnual", creditos[11].TAAnual);

                Fragment fragment = new CreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_portafolio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", creditos[10].nombre);
                bundle.putString("subtitulo", creditos[10].nombre+"?");
                bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                bundle.putString("TANM", creditos[10].TANM);
                bundle.putString("TAAnual", creditos[10].TAAnual);

                Fragment fragment = new CreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_libreInversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", creditos[9].nombre);
                bundle.putString("subtitulo", creditos[9].nombre+"?");
                bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                bundle.putString("TANM", creditos[9].TANM);
                bundle.putString("TAAnual", creditos[9].TAAnual);

                Fragment fragment = new CreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();


            }
        });

        btn_fomento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", creditos[8].nombre);
                bundle.putString("subtitulo", creditos[8].nombre+"?");
                bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                bundle.putString("TANM", creditos[8].TANM);
                bundle.putString("TAAnual", creditos[8].TAAnual);

                Fragment fragment = new CreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();


            }
        });

        btn_exequial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", creditos[7].nombre);
                bundle.putString("subtitulo", creditos[7].nombre+"?");
                bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                bundle.putString("TANM", creditos[7].TANM);
                bundle.putString("TAAnual", creditos[7].TAAnual);

                Fragment fragment = new CreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();


            }
        });

        btn_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", creditos[6].nombre);
                bundle.putString("subtitulo", creditos[6].nombre+"?");
                bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                bundle.putString("TANM", creditos[6].TANM);
                bundle.putString("TAAnual", creditos[6].TAAnual);

                Fragment fragment = new CreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();


            }
        });

        btn_eduBecas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment fragment = new CreditFormFragment();
                bundle.putString("titulo", creditos[5].nombre);
                bundle.putString("subtitulo", creditos[5].nombre+"?");
                bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                bundle.putString("TANM", creditos[5].TANM);
                bundle.putString("TAAnual", creditos[5].TAAnual);

                Fragment fragment = new CreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();


            }
        });

            btn_eduRotativo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Fragment fragment = new CreditFormFragment();
                    bundle.putString("titulo", creditos[4].nombre);
                    bundle.putString("subtitulo", creditos[4].nombre+"?");
                    bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                    bundle.putString("TANM", creditos[4].TANM);
                    bundle.putString("TAAnual", creditos[4].TAAnual);

                    Fragment fragment = new CreditFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contenedor, fragment);
                    transaction.commit();


                }
            });

            btn_crediExpress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Fragment fragment = new CreditFormFragment();
                    bundle.putString("titulo", creditos[3].nombre);
                    bundle.putString("subtitulo", creditos[3].nombre+"?");
                    bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                    bundle.putString("TANM", creditos[3].TANM);
                    bundle.putString("TAAnual", creditos[3].TAAnual);

                    Fragment fragment = new CreditFragment();
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
                    bundle.putString("titulo", creditos[2].nombre);
                    bundle.putString("subtitulo", creditos[2].nombre+"?");
                    bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                    bundle.putString("TANM", creditos[2].TANM);
                    bundle.putString("TAAnual", creditos[2].TAAnual);

                    Fragment fragment = new CreditFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contenedor, fragment);
                    transaction.commit();


                }
            });

            btn_carteraInterna.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Fragment fragment = new CreditFormFragment();
                    bundle.putString("titulo", creditos[1].nombre);
                    bundle.putString("subtitulo", creditos[1].nombre+"?");
                    bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                    bundle.putString("TANM", creditos[1].TANM);
                    bundle.putString("TAAnual", creditos[1].TAAnual);

                    Fragment fragment = new CreditFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contenedor, fragment);
                    transaction.commit();


                }
            });

            btn_bonoMercado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Fragment fragment = new CreditFormFragment();
                    bundle.putString("titulo", creditos[0].nombre);
                    bundle.putString("subtitulo", creditos[0].nombre+"?");
                    bundle.putString("descripcion", "Solicitarle al fondo una descripción de cada uno de los creditos");
                    bundle.putString("TANM", creditos[0].TANM);
                    bundle.putString("TAAnual", creditos[0].TAAnual);

                    Fragment fragment = new CreditFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_contenedor, fragment);
                    transaction.commit();


                }
            });
        }

}
