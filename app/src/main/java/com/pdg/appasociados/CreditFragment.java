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
    String subtituloCredito = "null";
    String descripCredito = "null";
    String TANMCredito = "null";
    String TAAnualCredito = "null";

    TextView titulo;
    TextView subtitulo;
    TextView descrip;
    TextView TANM;
    TextView TAAnual;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tituloCredito = getArguments().getString("titulo", "Pos es null");
            descripCredito = getArguments().getString("descripcion", "Pos es null");
            subtituloCredito = getArguments().getString("subtitulo", "Pos es null");
            TANMCredito = getArguments().getString("TANM", "Pos es null");
            TAAnualCredito = getArguments().getString("TAAnual", "Pos es null");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_credito, container, false);
        titulo = vista.findViewById(R.id.txt_creditTitlte);
        descrip = vista.findViewById(R.id.text_creditDescrip);
        subtitulo = vista.findViewById(R.id.text_creditSubT);
        TANM = vista.findViewById(R.id.text_creditTANM1);
        TAAnual = vista.findViewById(R.id.text_creditTAAnual1);

        String sub = (String) subtitulo.getText();
        //String nominal = (String) TANM.getText();
       // String efectiva = (String) TAAnual.getText();

        titulo.setText(tituloCredito);
        descrip.setText(descripCredito);
        subtitulo.setText(sub+subtituloCredito);
        TANM.setText(TANMCredito);
        TAAnual.setText(TAAnualCredito);

        return vista;

    }
}
