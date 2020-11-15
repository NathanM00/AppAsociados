package com.pdg.appasociados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PasadiaFragment extends Fragment {

    View vista;
    String destinoPasadia = "null";
    String descripPasadia = "null";
    String archivoPasadia = "null";
    String fechaPasadia = "null";

    TextView titulo;
    TextView subtitulo;
    TextView descrip;
    TextView fecha;
    ImageView imagen;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            destinoPasadia = getArguments().getString("destino", "Pos es null");
            descripPasadia = getArguments().getString("descripcion", "Pos es null");
            fechaPasadia = getArguments().getString("fecha", "Pos es null");
            archivoPasadia = getArguments().getString("archivo", "Pos es null");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_pasadia, container, false);
        titulo = vista.findViewById(R.id.txt_pasadiaTitlte);
        descrip = vista.findViewById(R.id.text_noticiaDescrip);
        subtitulo = vista.findViewById(R.id.text_noticiaSubT);
        fecha = vista.findViewById(R.id.txt_pasadiaFecha);
        imagen = vista.findViewById(R.id.img_noticia);

        String sub = (String) subtitulo.getText();

       titulo.setText(destinoPasadia);
       descrip.setText(descripPasadia);
       subtitulo.setText(destinoPasadia);
       fecha.setText("Fecha: "+fechaPasadia);
       Picasso.with(getContext()).load(archivoPasadia).into(imagen);

        return vista;

    }
}
