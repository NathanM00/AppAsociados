package com.pdg.appasociados;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class PasadiaFragment extends Fragment {

    private View vista;
    private String destinoPasadia = "null";
    private String descripPasadia = "null";
    private String archivoPasadia = "null";
    private String fechaPasadia = "null";
    private  String idPasadia = "null";
    Bundle bundle = new Bundle();

    private Float calificacionPasadia ;
    private Integer numCali ;

    private DatabaseReference ref,refUser;

    private TextView titulo;
    private TextView subtitulo;
    private TextView descrip;
    private TextView fecha;
    private ImageView imagen;
    private RatingBar calificacion;
    private RatingBar calificable;
    private Button agendar;
    private Button calificar;
    private Button confirmar;
    private Button cancelar;

    private Boolean isCalificado =false;

    FirebaseAuth auth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase db;

    private Integer newNumCali;
    private Float calificacionTotal;
    private Integer puntosAsociado;
    private Integer newPuntosAsociado;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private EditText et_comentario;
    private Button btn_comentar;
    private Button btn_cancelar;
    private String nombreComment;

    private Date d = new Date();
    private SimpleDateFormat date = new SimpleDateFormat("d, MMMM, yyyy");


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            destinoPasadia = getArguments().getString("destino", "Pos es null");
            descripPasadia = getArguments().getString("descripcion", "Pos es null");
            fechaPasadia = getArguments().getString("fecha", "Pos es null");
            archivoPasadia = getArguments().getString("archivo", "Pos es null");
            calificacionPasadia = getArguments().getFloat("calificacion", 99);
            idPasadia = getArguments().getString("id", "Pos es null");
            numCali = getArguments().getInt("numCali", 99);

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
        imagen = vista.findViewById(R.id.img_pasadia);
        calificacion = vista.findViewById(R.id.rating_pasadia);
        agendar = vista.findViewById(R.id.btn_agendar);
        calificar = vista.findViewById(R.id.btn_calificar);
        et_comentario= vista.findViewById(R.id.txt_comentarioPasadia);
        btn_comentar = vista.findViewById(R.id.btn_comentarPasadia);
        btn_cancelar = vista.findViewById(R.id.btn_cancelarPasadia);

        ref = FirebaseDatabase.getInstance().getReference().child("Eventos").child(idPasadia);
        refUser = FirebaseDatabase.getInstance().getReference().child("usuarios");

        String sub = (String) subtitulo.getText();

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

       titulo.setText(destinoPasadia);
       descrip.setText(descripPasadia);
       subtitulo.setText(destinoPasadia);
       fecha.setText("Fecha: "+fechaPasadia);

       calificacion.setRating(calificacionPasadia);

        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostarPopCalificar();
            }
        });

        Picasso.with(getContext()).load(archivoPasadia).into(imagen);

        refUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String puntos = dataSnapshot.child(auth.getUid()).child("puntos").getValue().toString();
                puntosAsociado = Integer.parseInt((puntos));
                InfoUser info = dataSnapshot.getValue(InfoUser.class);
                String nombre = info.getNombre();

                nombreComment = nombre;
            }

            //  asasd
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });

        btn_comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et_comentario.getText().toString().trim();
                if(!text.equals("") ) {
                    enviarComentario();
                } else{
                    Toast.makeText(getActivity(), "Comentario vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_comentario.setText("");
                Drawable d = getResources().getDrawable(R.drawable.btn_comentar);
                btn_comentar.setBackground(d);
            }
        });

        et_comentario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                reaccionBtnComentar();
            }
        });

        return vista;

    }

    private void mostarPopCalificar(){

        dialogBuilder = new AlertDialog.Builder(getContext());

        final View popUpView = getLayoutInflater().inflate(R.layout.popup_calificar, null);
        calificable = popUpView.findViewById(R.id.rate_pasadia);
        cancelar = popUpView.findViewById(R.id.btn_cancelar);
        confirmar = popUpView.findViewById(R.id.btn_confirmar);

        dialogBuilder.setView(popUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        calificarActividad();

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("calificacion").setValue(calificacionTotal);
                ref.child("numCali").setValue(newNumCali);
                calificacion.setRating(calificacionTotal);
                dialog.dismiss();
                if(isCalificado == false){
                    mostarPopPuntos();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void mostarPopPuntos() {
        refUser.child(auth.getUid()).child("puntos").setValue(newPuntosAsociado);

        dialogBuilder = new AlertDialog.Builder(getContext());

        final View popUpView = getLayoutInflater().inflate(R.layout.popup_puntos, null);
        final TextView felicidades = popUpView.findViewById(R.id.tv_felicitaciones);
        Button omitir = popUpView.findViewById(R.id.btn_omitir);
        Button ver = popUpView.findViewById(R.id.btn_ver);

        String a  = "Por dejar tu comentario haz ganado " +
                "<span style='font-weight:bold; text-align:center; color:#FF7F00;'>100 puntos.</span>" +
                " Sigue acumulando puntos para ganar más premios del fondo.";

        felicidades.setText(Html.fromHtml(a));

        dialogBuilder.setView(popUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCalificado = true;

                Fragment fragment = new PricesFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

        omitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCalificado = true;
                dialog.dismiss();
            }
        });

    }

    private void calificarActividad(){
        calificable.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                         newPuntosAsociado = puntosAsociado + 100;
                         newNumCali = numCali + 1;
                         calificacionTotal = ( (calificacionPasadia*numCali) +rating) /newNumCali ;

                    }
                }
        );

    }

    private void reaccionBtnComentar(){
        Drawable d = getResources().getDrawable(R.drawable.btn_siguiente);
        Drawable d1 = getResources().getDrawable(R.drawable.btn_comentar);

        String text = et_comentario.getText().toString().trim();
        if(!text.equals("") ){
            btn_comentar.setBackground(d);
            Log.d("AAA","Reaccion dentro");
        }else{
            btn_comentar.setBackground(d1);
        }
    }

    private void enviarComentario() {
        final String coment = et_comentario.getText().toString().trim();
        final String fecha = date.format(d);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ref.child("comentarios").push().setValue(new Coment(user.getUid(), nombreComment, fecha, coment, destinoPasadia));
                    Toast.makeText(getActivity(), "Comentario enviado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });
        et_comentario.setText("");
    }

}
