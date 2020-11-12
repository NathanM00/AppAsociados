package com.pdg.appasociados;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SocialFragment extends Fragment {

    View vista;
    Bundle bundle = new Bundle();
    String tituloSocial = "null";
    String subtituloSocial = "null";
    String descripSocial = "null";
    String isConvenios = "null";

    TextView titulo;
    TextView subtitulo;
    TextView descrip;
    ListView lista;

    ArrayList<String> convenios;
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tituloSocial = getArguments().getString("titulo", "Pos es null");
            descripSocial = getArguments().getString("descripcion", "Pos es null");
            subtituloSocial = getArguments().getString("subtitulo", "Pos es null");
            isConvenios = getArguments().getString("convenios", "null");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_social, container, false);
        titulo = vista.findViewById(R.id.txt_socialTitlte);
        descrip = vista.findViewById(R.id.text_socialDescrip);
        subtitulo = vista.findViewById(R.id.text_socialSubT);
        lista = vista.findViewById(R.id.list_sociales);
        convenios = new ArrayList<String>();

        String sub = (String) subtitulo.getText();
        //String nominal = (String) TANM.getText();
       // String efectiva = (String) TAAnual.getText();
        //¿
        titulo.setText(tituloSocial);
        descrip.setText(descripSocial);

        if( tituloSocial.contains("Centro") || tituloSocial.contains("Plan") || tituloSocial.contains("Voluntariado") ){
            subtitulo.setText("¿Qué es el "+subtituloSocial);
        }
        else if(tituloSocial.contains("Cultura") || tituloSocial.contains("Educación") ||tituloSocial.contains("Deportes")  ){
            subtitulo.setText("¿Qué son los beneficios de "+subtituloSocial);
        }
        else if(tituloSocial.contains("Bono") ||tituloSocial.contains("Convenios") ){
            subtitulo.setText("¿Qué son los "+subtituloSocial);
        }
        else if(tituloSocial.contains("Adultos")){
            subtitulo.setText("¿Qué son los beneficios para "+subtituloSocial);
        }

        if(isConvenios.equals("siEs")){
            convenios.add("Agencias de viaje");
            convenios.add("Exequial");
            convenios.add("Hogar");
            convenios.add("Odontologia");
            convenios.add("Optometría");
            convenios.add("Planes de telefonía (Tigo, movistar, claro)");
            convenios.add("Pólizas de vehículo y hogar");
            convenios.add("Repuestos");
            convenios.add("Salud");
            convenios.add("Seguro Soat");
            convenios.add("Vehículos");
            convenios.add("Otros");

            lista.setVisibility(View.VISIBLE);
            adapter = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_list_item_1, convenios);
            lista.setAdapter(adapter);
        }

        return vista;

    }
}
