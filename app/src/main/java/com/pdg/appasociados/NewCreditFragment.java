package com.pdg.appasociados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.pdg.appasociados.form.CreditFormFragment;

public class NewCreditFragment extends Fragment {

    CardView btn_new_credit;
    CardView btn_refinaancia;
    CardView btn_credit_actaliza;

    View vista;
    Bundle bundle = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_new_credit, container, false);

        btn_new_credit = vista.findViewById(R.id.btn_new_credit);
        btn_refinaancia = vista.findViewById(R.id.btn_refinancia);
        btn_credit_actaliza = vista.findViewById(R.id.btn_credit_actaliza);

        botonesNewCredit();

        return vista;
    }

    private void botonesNewCredit() {

        btn_new_credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CreditFormFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_refinaancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CreditFormFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();

            }
        });

        btn_credit_actaliza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CreditFormFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();

            }
        });
    }

}
