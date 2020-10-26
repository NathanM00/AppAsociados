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

public class CreditForm4Fragment extends Fragment {

    EditText et_salario;
    EditText et_variable;
    EditText et_arrendamientos;
    EditText et_ingresos;
    EditText et_nomina;
    EditText et_gastos;
    EditText et_obliga;
    EditText et_otros;


    Button btn_siguiente;

    Bundle bundle = new Bundle();
    View vista;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_credit_form4, container, false);

        et_salario = vista.findViewById(R.id.et_salario);
        et_variable = vista.findViewById(R.id.et_variable);
        et_arrendamientos = vista.findViewById(R.id.et_arrendamientos);
        et_ingresos = vista.findViewById(R.id.et_ingresos);
        et_nomina = vista.findViewById(R.id.et_nomina);
        et_gastos = vista.findViewById(R.id.et_gastos);
        et_obliga = vista.findViewById(R.id.et_obliga);
        et_otros = vista.findViewById(R.id.et_otros);



        btn_siguiente = vista.findViewById(R.id.btn_siguiente);

        botonesForm();

        return vista;
    }

    private void botonesForm() {
        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CreditForm5Fragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();

            }
        });
    }
}
