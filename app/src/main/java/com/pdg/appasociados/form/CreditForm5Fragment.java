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

public class CreditForm5Fragment extends Fragment {

    EditText et_cargo;

    EditText et_mes;
    EditText et_dia;
    EditText et_ano;

    RadioButton rb_contrat1;
    RadioButton rb_contrat2;

    EditText et_dir;

    Button btn_siguiente;

    Bundle bundle = new Bundle();
    View vista;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_credit_form5, container, false);

        et_cargo = vista.findViewById(R.id.et_cargo);

        et_mes = vista.findViewById(R.id.et_mes);
        et_dia = vista.findViewById(R.id.et_dia);
        et_ano = vista.findViewById(R.id.et_ano);

        rb_contrat1 = vista.findViewById(R.id.rb_contrat1);
        rb_contrat2 = vista.findViewById(R.id.rb_contrat2);

        et_dir = vista.findViewById(R.id.et_dir);

        btn_siguiente = vista.findViewById(R.id.btn_siguiente);

        botonesForm();

        return vista;
    }

    private void botonesForm() {
        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CreditForm6Fragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();

            }
        });
    }
}
