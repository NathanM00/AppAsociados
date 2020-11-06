package com.pdg.appasociados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SolidaryFragment extends Fragment {

    View vista;

    TextView titulo;
    TextView subtitulo;
    TextView descrip;
    ListView lista;

    FirebaseDatabase db;
    DatabaseReference ref;

    ArrayList<String> tipos;
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_solidary, container, false);
        titulo = vista.findViewById(R.id.txt_solidaryTitlte);
        descrip = vista.findViewById(R.id.text_solidaryDescrip);
        subtitulo = vista.findViewById(R.id.text_solidarySubT);
        lista = vista.findViewById(R.id.list_solidarios);
        tipos = new ArrayList<String>();

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Beneficios").child("comiteSolidaridad");

        obtenerDatos();

        String sub = (String) subtitulo.getText();
        //String nominal = (String) TANM.getText();
       // String efectiva = (String) TAAnual.getText();

        tipos.add("Auxilio para Calamidad domestica");
        tipos.add("Auxilio para Salud");
        tipos.add("Medicamento");
        tipos.add("Optometría");
        tipos.add("Servicios Hospitalarios");
        tipos.add("Auxilio por Incapacidad");
        tipos.add("Auxilio por Fallecimiento del Asociado");
        tipos.add("Servicios Funerarios");
        tipos.add("Prestamo de equipos ortopedicos");

        adapter = new ArrayAdapter<String>(vista.getContext(), android.R.layout.simple_list_item_1, tipos);
        lista.setAdapter(adapter);

        return vista;

    }

    private void obtenerDatos() {

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               Social social = dataSnapshot.getValue(Social.class);

                titulo.setText(social.nombre);
                descrip.setText(social.descripcion);
                subtitulo.setText("¿Qué es el "+social.nombre+"?");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });
    }
}
