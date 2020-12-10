package com.pdg.appasociados;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VotarFragment extends Fragment {

    EditText et_voteIdea;
    Button btn_shareButton;

    Date d = new Date();
    SimpleDateFormat date = new SimpleDateFormat("d, MMMM, yyyy");
    Boolean votarActivo =true;

    DatabaseReference refPanfletos;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference ref = db.getReference("Comentarios");
    DatabaseReference pan = db.getReference("Panfletos");

    Bundle bundle = new Bundle();

    Integer votosGeneral;
    String nombreComment;

    RecyclerView vt_recyclerview;


    View vista;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_votar, container, false);

            et_voteIdea = vista.findViewById(R.id.et_voteIdea);
            btn_shareButton = vista.findViewById(R.id.btn_shareButton);

             refPanfletos = FirebaseDatabase.getInstance().getReference().child("Panfletos");
             refPanfletos.keepSynced(true);

            vt_recyclerview = vista.findViewById(R.id.vt_recyclerview);
            vt_recyclerview.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
            linearLayoutManager.setReverseLayout(true);
            linearLayoutManager.setStackFromEnd(true);
            vt_recyclerview.setLayoutManager(linearLayoutManager);


            btn_shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enviarComentario();
                }
            });

            DatabaseReference a = db.getReference("usuarios").child(auth.getUid());

            a.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    InfoUser info = dataSnapshot.getValue(InfoUser.class);
                    String nombre = info.getNombre();

                    nombreComment = nombre;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("The read failed: ");
                }
            });

        return vista;

        }


    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Panfleto, VotarFragment.PanfletoViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Panfleto, VotarFragment.PanfletoViewHolder>(Panfleto.class,R.layout.card_panfleto, VotarFragment.PanfletoViewHolder.class,refPanfletos){
                    @Override
                    protected void populateViewHolder(final VotarFragment.PanfletoViewHolder panfletoViewHolder, final Panfleto model, int i) {

                        panfletoViewHolder.setTitle(model.getTitular());
                        panfletoViewHolder.setImage(getContext(),model.getArchivo());
                        panfletoViewHolder.setCuerpo(model.getCuerpo());
                        panfletoViewHolder.setId(model.getId());

                        panfletoViewHolder.getButton().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                enviarVoto(panfletoViewHolder.getId());
                                panfletoViewHolder.setAlpha((float) 0.5);
                                Toast.makeText(getActivity(), "Voto realizado", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }

                };
        vt_recyclerview.setAdapter(firebaseRecyclerAdapter);

    }

    public static class PanfletoViewHolder extends RecyclerView.ViewHolder{
        View view;

        private String Titular, Archivo, Cuerpo,Id;

        public PanfletoViewHolder(View itemView){
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
            TextView tv_cuerpo = view.findViewById(R.id.tv_cuerpo);
            tv_cuerpo.setText(cuerpo);
            Cuerpo = cuerpo;
        }

        public void setId(String id) {
            Id = id;
        }

        public void setAlpha(Float alpha){
            Button btn_votar = view.findViewById(R.id.btn_votar);
            btn_votar.setAlpha(alpha);
        }


        public String getTitular() { return Titular; }

        public String getCuerpo() { return Cuerpo; }

        public String getArchivo() { return Archivo; }

        public String getId() { return Id; }

        public Button getButton() { return view.findViewById(R.id.btn_votar); }

    }

    private void enviarVoto(final String id){

        pan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String votos = dataSnapshot.child(id).child("votos").getValue().toString();
                votosGeneral = Integer.parseInt(votos);
                if(votarActivo){
                    votosGeneral +=1 ;
                    votarActivo= false;
                }
                pan.child(id).child("votos").setValue(votosGeneral);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });
    }

        //metodo para enviar el comentario
        private void enviarComentario() {
            final String voteIdea = et_voteIdea.getText().toString().trim();
            final String fecha = date.format(d);

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    ref.push().setValue(new Coment(user.getUid(), nombreComment, fecha, voteIdea, "Tú eliges"));

                    Toast.makeText(getActivity(), "Comentario enviado", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("The read failed: ");
                }
            });
            et_voteIdea.setText("");
        }



    }
