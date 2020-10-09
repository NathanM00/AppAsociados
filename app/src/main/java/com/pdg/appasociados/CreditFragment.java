package com.pdg.appasociados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CreditFragment extends Fragment {

    View vista;
    Bundle bundle = new Bundle();
    String tituloCredito = "null";
    String descripCredito = "null";

    TextView titulo;
    TextView descrip;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tituloCredito = getArguments().getString("titulo", "Pos es null");
            descripCredito = getArguments().getString("descripcion", "Pos es null");

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_credito, container, false);
        titulo = vista.findViewById(R.id.txt_creditTitlte);
        descrip = vista.findViewById(R.id.text_creditDescrip);

        titulo.setText(tituloCredito);
        descrip.setText(descripCredito);

        return vista;

    }

}
