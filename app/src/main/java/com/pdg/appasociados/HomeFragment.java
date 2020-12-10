package com.pdg.appasociados;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    TextView tv_saludo;

    DatabaseReference ref;
    DatabaseReference refNoticias;
    FirebaseAuth auth;
    FirebaseDatabase db;
    Bundle bundle = new Bundle();

    RecyclerView nt_recyclerview;

    CardView btn_creditos, btn_pasadias,btn_premios;

    TextView btn_todasNoticias;
    View vista;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_home, container, false);

        tv_saludo = vista.findViewById(R.id.tv_saludo);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("usuarios");

        btn_creditos = vista.findViewById(R.id.btn_creditos);
        btn_pasadias = vista.findViewById(R.id.btn_pasadias);
        btn_premios = vista.findViewById(R.id.btn_premios);
        btn_todasNoticias= vista.findViewById(R.id.btn_todasNoticias);

        refNoticias = FirebaseDatabase.getInstance().getReference().child("Noticias");
        refNoticias.keepSynced(true);

        nt_recyclerview = vista.findViewById(R.id.nt_recyclerview);
        nt_recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        nt_recyclerview.setLayoutManager(linearLayoutManager);

        getNombre();
        botonesBeneficios();

        return vista;

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Noticia, NoticiasFragment.NoticiaViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Noticia, NoticiasFragment.NoticiaViewHolder>(Noticia.class,R.layout.card_noticia, NoticiasFragment.NoticiaViewHolder.class,refNoticias){
                    @Override
                    protected void populateViewHolder(final NoticiasFragment.NoticiaViewHolder noticiaViewHolder, Noticia model, int i) {

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

        public void setImage(Context ctx, String archivo){
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

    //Metodo para conseguir el nombre del usuario logeado
    private void getNombre() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.child(auth.getUid()).child("nombre").getValue().toString();
                String userMail = dataSnapshot.child(auth.getUid()).child("correo").getValue().toString();
                Boolean userStatus = (Boolean) dataSnapshot.child(auth.getUid()).child("asociado").getValue();
                String userPass = dataSnapshot.child(auth.getUid()).child("contraseña").getValue().toString();

                Log.d("zzz",userName);
                Log.d("zzz",userMail);

                tv_saludo.setText("Hola, " +userName);
            }

            //  asasd
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });

    }

    private void botonesBeneficios() {

        btn_creditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new PreCreditFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_todasNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new NoticiasFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });


        btn_premios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new PricesFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        btn_pasadias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new PasadiasFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });
    }

    }
