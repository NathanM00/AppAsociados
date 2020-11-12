package com.pdg.appasociados.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.pdg.appasociados.R;
import com.pdg.appasociados.form.CreditForm3Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CreditForm2Fragment extends Fragment {

    EditText et_documento;
    EditText et_hijos;

    RadioButton rb_linea1;
    RadioButton rb_linea2;

    RadioButton rb_estado1;
    RadioButton rb_estado2;
    RadioButton rb_estado3;
    RadioButton rb_estado4;
    RadioButton rb_estado5;

    RadioButton rb_viv1;
    RadioButton rb_viv2;
    RadioButton rb_viv3;

    RadioButton rb_in1;
    RadioButton rb_in2;

    RadioButton rb_hijos;

    Button btn_siguiente;

    Bundle bundle = new Bundle();
    View vista;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_credit_form2, container, false);

        et_documento = vista.findViewById(R.id.et_documento);
        et_hijos = vista.findViewById(R.id.et_hijos);

        rb_linea1 = vista.findViewById(R.id.rb_linea1);
        rb_linea2 = vista.findViewById(R.id.rb_linea2);

        rb_estado1 = vista.findViewById(R.id.rb_estado1);
        rb_estado2 = vista.findViewById(R.id.rb_estado2);
        rb_estado3 = vista.findViewById(R.id.rb_estado3);
        rb_estado4 = vista.findViewById(R.id.rb_estado4);
        rb_estado5 = vista.findViewById(R.id.rb_estado5);

        rb_viv1 = vista.findViewById(R.id.rb_viv1);
        rb_viv2 = vista.findViewById(R.id.rb_viv2);
        rb_viv3 = vista.findViewById(R.id.rb_viv3);

        rb_in1 = vista.findViewById(R.id.rb_in1);
        rb_in2 = vista.findViewById(R.id.rb_in2);

        rb_hijos = vista.findViewById(R.id.rb_hijos);

        btn_siguiente = vista.findViewById(R.id.btn_siguiente);

        botonesForm();

        return vista;
    }

    private void botonesForm() {
        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CreditForm3Fragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();

            }
        });
    }
}