package com.pdg.appasociados;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class PasadiasFragment extends Fragment {

    RecyclerView pd_recyclerview;
    DatabaseReference ref;

    View vista;
    Bundle bundle = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_pasadias, container, false);

        ref = FirebaseDatabase.getInstance().getReference().child("Eventos");
        ref.keepSynced(true);

        pd_recyclerview = vista.findViewById(R.id.pd_recyclerview);
        pd_recyclerview.setHasFixedSize(true);
        pd_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        return vista;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Pasadia, PasadiaViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Pasadia, PasadiaViewHolder>(Pasadia.class,R.layout.card_pasadia,PasadiaViewHolder.class,ref){
            @Override
            protected void populateViewHolder(final PasadiaViewHolder pasadiaViewHolder, Pasadia model, int i) {

                pasadiaViewHolder.setTitle(model.getDestino());

                pasadiaViewHolder.setDate(model.getFecha());

                pasadiaViewHolder.setImage(getContext(),model.getArchivo());

                pasadiaViewHolder.setDescripcion(model.getDescripcion());

                pasadiaViewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        bundle.putString("descripcion", pasadiaViewHolder.getDescripcion());
                        bundle.putString("destino", pasadiaViewHolder.getDestino());
                        bundle.putString("fecha", pasadiaViewHolder.getFecha());
                        bundle.putString("archivo", pasadiaViewHolder.getArchivo());

                        Fragment fragment = new PasadiaFragment();
                        fragment.setArguments(bundle);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_contenedor, fragment);
                        transaction.commit();
                    }
                });

            }

        };
        pd_recyclerview.setAdapter(firebaseRecyclerAdapter);
        
    }

    public static class PasadiaViewHolder extends RecyclerView.ViewHolder{
        View view;

        private String Destino, Archivo, Fecha, Descripcion;

        public PasadiaViewHolder(View itemView){
            super(itemView);
            view = itemView;
        }
        public void setTitle(String destino){
            TextView tv_titulo = view.findViewById(R.id.tv_titulo);
            tv_titulo.setText(destino);
            Destino = destino;
        }

        public void setImage(Context ctx,String archivo){
            ImageView iv_image = view.findViewById(R.id.iv_image);
            Picasso.with(ctx).load(archivo).into(iv_image);
            Archivo = archivo;
        }

        public void setDate(String fecha){
            TextView tv_fecha = view.findViewById(R.id.tv_fecha);
            tv_fecha.setText(fecha);
            Fecha = fecha;
        }

        public void setDescripcion(String descripcion) {
            Descripcion = descripcion;
        }

        public String getDestino() { return Destino; }

        public String getFecha() { return Fecha; }

        public String getDescripcion() { return Descripcion; }

        public String getArchivo() { return Archivo; }

    }
}

