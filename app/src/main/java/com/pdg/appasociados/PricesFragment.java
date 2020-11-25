package com.pdg.appasociados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class PricesFragment extends Fragment {

    ImageView btn_back;
    CardView btn_camiseta, btn_chaqueta, btn_gorra, btn_descuento, btn_helado, btn_maleta;
    TextView txt_puntos;
    View vista;
    Bundle bundle = new Bundle();
    FirebaseDatabase db;
    
    FirebaseAuth auth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference refUser,ref;
    
    private Integer puntosAsociado;
    private Integer newPuntosAsociado;
    private Integer canjeosChaqueta,canjeosCamisa,canjeosGorra,canjeosHelado,canjeosMaleta,canjeosDescuento;
    private Integer newCanjeosPremio;
    private Integer costo = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_premios, container, false);

        btn_back = vista.findViewById(R.id.btn_back);
        btn_camiseta = vista.findViewById(R.id.btn_camiseta);
        btn_chaqueta = vista.findViewById(R.id.btn_chaqueta);
        btn_gorra = vista.findViewById(R.id.btn_gorra);
        btn_descuento = vista.findViewById(R.id.btn_descuento);
        btn_helado= vista.findViewById(R.id.btn_helado);
        btn_maleta = vista.findViewById(R.id.btn_maletin);
        txt_puntos= vista.findViewById(R.id.txt_puntos);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        refUser = FirebaseDatabase.getInstance().getReference().child("usuarios");
        ref = FirebaseDatabase.getInstance().getReference().child("Premios");

        refUser.keepSynced(true);

        refUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String puntos = dataSnapshot.child(auth.getUid()).child("puntos").getValue().toString();
                puntosAsociado = Integer.parseInt((puntos));
                txt_puntos.setText("Tus puntos: "+puntosAsociado);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });

        canjeosPremios();
        adquirirPremio();

        return vista;
    }

    private void canjeosPremios(){
        //Chaqueta
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String canjeos = dataSnapshot.child("nikeChaqueta").child("canjeos").getValue().toString();
                canjeosChaqueta = Integer.parseInt((canjeos));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });
        //Camisa
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String canjeos = dataSnapshot.child("camisaAdidas").child("canjeos").getValue().toString();
                canjeosCamisa = Integer.parseInt((canjeos));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });
        //Descuento
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String canjeos = dataSnapshot.child("descuento").child("canjeos").getValue().toString();
                canjeosDescuento = Integer.parseInt((canjeos));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });
        //Gorra
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String canjeos = dataSnapshot.child("gorraAdidas").child("canjeos").getValue().toString();
                canjeosGorra = Integer.parseInt((canjeos));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });
        //Helado
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String canjeos = dataSnapshot.child("heldadoPopsy").child("canjeos").getValue().toString();
                canjeosHelado = Integer.parseInt((canjeos));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });
        //Maleta
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String canjeos = dataSnapshot.child("nikeMaleta").child("canjeos").getValue().toString();
                canjeosMaleta = Integer.parseInt((canjeos));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });
    }
    
    private void adquirirPremio(){
        btn_camiseta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                costo = 2000;
                if(costo<=puntosAsociado){
                    btn_camiseta.setEnabled(false);
                    btn_camiseta.setAlpha((float) 0.5);
                    newPuntosAsociado = puntosAsociado - costo;
                    newCanjeosPremio = canjeosCamisa + 1;
                    ref.child("camisaAdidas").child("canjeos").setValue(newCanjeosPremio);
                    refUser.child(auth.getUid()).child("puntos").setValue(newPuntosAsociado);
                    Toast.makeText(getActivity(), "Premio canjeado, el fondo se pondra en contacto pronto para ajustar detalles", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), "Puntos insuficientes", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_descuento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                costo = 800;
                if(costo<=puntosAsociado){
                    btn_descuento.setEnabled(false);
                    btn_descuento.setAlpha((float) 0.5);
                    newPuntosAsociado = puntosAsociado - costo;
                    newCanjeosPremio = canjeosDescuento + 1;
                    ref.child("descuento").child("canjeos").setValue(newCanjeosPremio);
                    refUser.child(auth.getUid()).child("puntos").setValue(newPuntosAsociado);
                    Toast.makeText(getActivity(), "Premio canjeado, el fondo se pondra en contacto pronto para ajustar detalles", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), "Puntos insuficientes", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_gorra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                costo = 1000;
                if(costo<=puntosAsociado){
                    btn_gorra.setEnabled(false);
                    btn_gorra.setAlpha((float) 0.5);
                    newPuntosAsociado = puntosAsociado - costo;
                    newCanjeosPremio = canjeosGorra + 1;
                    ref.child("gorraAdidas").child("canjeos").setValue(newCanjeosPremio);
                    refUser.child(auth.getUid()).child("puntos").setValue(newPuntosAsociado);
                    Toast.makeText(getActivity(), "Premio canjeado, el fondo se pondra en contacto pronto para ajustar detalles", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), "Puntos insuficientes", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_chaqueta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                costo = 2100;
                if(costo<=puntosAsociado){
                    btn_chaqueta.setEnabled(false);
                    btn_chaqueta.setAlpha((float) 0.5);
                    newPuntosAsociado = puntosAsociado - costo;
                    newCanjeosPremio = canjeosChaqueta + 1;
                    ref.child("nikeChaqueta").child("canjeos").setValue(newCanjeosPremio);
                    refUser.child(auth.getUid()).child("puntos").setValue(newPuntosAsociado);
                    Toast.makeText(getActivity(), "Premio canjeado, el fondo se pondra en contacto pronto para ajustar detalles", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), "Puntos insuficientes", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_helado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                costo = 500;
                if(costo<=puntosAsociado){
                    btn_helado.setEnabled(false);
                    btn_helado.setAlpha((float) 0.5);
                    newPuntosAsociado = puntosAsociado - costo;
                    newCanjeosPremio = canjeosHelado + 1;
                    ref.child("heldadoPopsy").child("canjeos").setValue(newCanjeosPremio);
                    refUser.child(auth.getUid()).child("puntos").setValue(newPuntosAsociado);
                    Toast.makeText(getActivity(), "Premio canjeado, el fondo se pondra en contacto pronto para ajustar detalles", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), "Puntos insuficientes", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_maleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                costo = 2500;
                if(costo<=puntosAsociado){
                    newPuntosAsociado = puntosAsociado - costo;
                    btn_maleta.setEnabled(false);
                    btn_maleta.setAlpha((float) 0.5);
                    newCanjeosPremio = canjeosMaleta + 1;
                    ref.child("nikeMaleta").child("canjeos").setValue(newCanjeosPremio);
                    refUser.child(auth.getUid()).child("puntos").setValue(newPuntosAsociado);
                    Toast.makeText(getActivity(), "Premio canjeado, el fondo se pondra en contacto pronto para ajustar detalles", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), "Puntos insuficientes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
