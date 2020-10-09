package com.pdg.appasociados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CreditsFragment extends Fragment {

    ImageView btn_back;
    CardView btn_vivienda;
    CardView btn_vehiculo;
    View vista;
    Bundle bundle = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_creditos, container, false);

        btn_back = vista.findViewById(R.id.btn_back);
        btn_vivienda = vista.findViewById(R.id.btn_credit_vivienda);
        btn_vehiculo = vista.findViewById(R.id.btn_credit_vehiculo);

        botonesBeneficios();

        return vista;
    }

    private void botonesBeneficios(){

        btn_vehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("titulo", "Vehículo");
                bundle.putString("descripcion", "Este es el credito para vehiculos de fonaviemcali");

                Fragment fragment = new CreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_vivienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("titulo", "Vivienda");
                bundle.putString("descripcion", "Este es el credito para vivienda de fonaviemcali");

                Fragment fragment = new CreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });


    }


}
