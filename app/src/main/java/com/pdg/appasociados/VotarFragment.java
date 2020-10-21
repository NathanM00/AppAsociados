package com.pdg.appasociados;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VotarFragment extends Fragment {

    EditText et_voteIdea;
    Button btn_shareButton;

    Date d = new Date();
    SimpleDateFormat date = new SimpleDateFormat("d, MMMM, yyyy");

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference ref = db.getReference("Comentarios");

    String nombreComment;

    ImageView btn_back;
    CardView btn_credit;
    CardView tueliges;
    View vista;

        ViewPager viewPager;
        AdapterVote adapter;
        List<Model> models;
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_votar, container, false);

            et_voteIdea = vista.findViewById(R.id.et_voteIdea);
            btn_shareButton = vista.findViewById(R.id.btn_shareButton);

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

            models = new ArrayList<>();
            models.add(new Model("Creditos nuevos", "Los nuevos creditos te poermitiran hacer cosas grandiosas"));
            models.add(new Model("Nuevos destinos", "Los nuevos creditos te poermitiran hacer cosas grandiosas"));
            models.add(new Model("Clases de pintura", "Los nuevos creditos te poermitiran hacer cosas grandiosas"));
            models.add(new Model("Salida de pesca", "Los nuevos creditos te poermitiran hacer cosas grandiosas"));

            adapter = new AdapterVote(models, this.getContext());

            viewPager = vista.findViewById(R.id.lyt_info);
            viewPager.setAdapter(adapter);
            viewPager.setPadding(130, 0, 130, 0);

            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        return vista;

        }

        //metodo para enviar el comentario
        private void enviarComentario() {
            final String voteIdea = et_voteIdea.getText().toString().trim();
            final String fecha = date.format(d);

            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    ref.push().setValue(new Coment(user.getUid(), nombreComment, fecha, voteIdea));

                    Toast.makeText(getActivity(), "Comentario enviado", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("The read failed: ");
                }
            });
        }



    }
