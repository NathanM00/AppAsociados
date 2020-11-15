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

public class NoticiaFragment extends Fragment {

    View vista;
    String titularNoticia = "null";
    String cuerpoNoticia = "null";
    String archivoNoticia = "null";

    TextView subtitulo;
    TextView descrip;
    ImageView imagen;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            titularNoticia = getArguments().getString("titular", "Pos es null");
            cuerpoNoticia = getArguments().getString("cuerpo", "Pos es null");
            archivoNoticia = getArguments().getString("archivo", "Pos es null");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_noticia, container, false);
        descrip = vista.findViewById(R.id.text_noticiaDescrip);
        subtitulo = vista.findViewById(R.id.text_noticiaSubT);
        imagen = vista.findViewById(R.id.img_noticia);

       descrip.setText(cuerpoNoticia);
       subtitulo.setText(titularNoticia);
       Picasso.with(getContext()).load(archivoNoticia).into(imagen);

        return vista;

    }
}
