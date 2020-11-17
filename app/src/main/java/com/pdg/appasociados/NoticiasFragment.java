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

public class NoticiasFragment extends Fragment {

    RecyclerView nt_recyclerview;
    DatabaseReference ref;

    View vista;
    Bundle bundle = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_noticias, container, false);

        ref = FirebaseDatabase.getInstance().getReference().child("Noticias");
        ref.keepSynced(true);

        nt_recyclerview = vista.findViewById(R.id.nt_recyclerview);
        nt_recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        nt_recyclerview.setLayoutManager(linearLayoutManager);

        return vista;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Noticia, NoticiaViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Noticia, NoticiaViewHolder>(Noticia.class,R.layout.card_noticia,NoticiaViewHolder.class,ref){
                    @Override
                    protected void populateViewHolder(final NoticiaViewHolder noticiaViewHolder, Noticia model, int i) {

                        noticiaViewHolder.setTitle(model.getTitular());

                        noticiaViewHolder.setImage(getContext(),model.getArchivo());

                        noticiaViewHolder.setCuerpo(model.getCuerpo());

                        noticiaViewHolder.view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                bundle.putString("cuerpo", noticiaViewHolder.getCuerpo());
                                bundle.putString("titular", noticiaViewHolder.getTitular());
                                bundle.putString("archivo", noticiaViewHolder.getArchivo());

                                Fragment fragment = new NoticiaFragment();
                                fragment.setArguments(bundle);
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.fragment_contenedor, fragment);
                                transaction.commit();
                            }
                        });

                    }

                };
        nt_recyclerview.setAdapter(firebaseRecyclerAdapter);

    }

    public static class NoticiaViewHolder extends RecyclerView.ViewHolder{
        View view;

        private String Titular, Archivo, Cuerpo;

        public NoticiaViewHolder(View itemView){
            super(itemView);
            view = itemView;
        }
        public void setTitle(String titular){
            TextView tv_titulo = view.findViewById(R.id.tv_titulo);
            tv_titulo.setText(titular);
            Titular = titular;
        }

        public void setImage(Context ctx,String archivo){
            ImageView iv_image = view.findViewById(R.id.iv_image);
            Picasso.with(ctx).load(archivo).into(iv_image);
            Archivo = archivo;
        }

        public void setCuerpo(String cuerpo) {
            Cuerpo = cuerpo;
        }

        public String getTitular() { return Titular; }

        public String getCuerpo() { return Cuerpo; }

        public String getArchivo() { return Archivo; }

    }
}
