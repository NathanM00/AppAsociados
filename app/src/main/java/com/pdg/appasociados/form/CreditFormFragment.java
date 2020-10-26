package com.pdg.appasociados.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.pdg.appasociados.R;

public class CreditFormFragment extends Fragment {

    EditText et_valor;
    RadioButton rb_linea1;
    RadioButton rb_linea2;

    Button btn_siguiente;

    Bundle bundle = new Bundle();
    View vista;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_credit_form, container, false);

        et_valor = vista.findViewById(R.id.et_valor);
        rb_linea1 = vista.findViewById(R.id.rb_linea1);
        rb_linea2 = vista.findViewById(R.id.rb_linea2);

        btn_siguiente = vista.findViewById(R.id.btn_siguiente);

        botonesForm();

        return vista;
    }

    private void botonesForm() {
        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CreditForm2Fragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();

            }
        });
    }
}
