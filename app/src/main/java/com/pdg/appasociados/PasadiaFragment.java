package com.pdg.appasociados;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PasadiaFragment extends Fragment {

    private View vista;
    private String destinoPasadia = "null";
    private String descripPasadia = "null";
    private String archivoPasadia = "null";
    private String fechaPasadia = "null";
    private  String idPasadia = "null";

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
    FirebaseDatabase db;

    private Integer newNumCali;
    private Float calificacionTotal;
    private Integer puntosAsociado;
    private Integer newPuntosAsociado;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

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
            }

            //  asasd
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
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
                " Sigue acumulando puntos para ganar premios del fondo.";

        felicidades.setText(Html.fromHtml(a));

        dialogBuilder.setView(popUpView);
        dialog = dialogBuilder.create();
        dialog.show();


        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCalificado = true;
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

}
