package com.pdg.appasociados;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pdg.appasociados.ModelChatUser.ChatUser;

public class HelpFragment extends Fragment {

    CardView btn_chat, btn_llamar;

    String nombre, id;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref = db.getReference("Soporte");
    DatabaseReference refKey = db.getReference("chatId").child(user.getUid());

    View vista;
    Bundle bundle = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_help, container, false);

        btn_chat = vista.findViewById(R.id.btn_chat);
        btn_llamar = vista.findViewById(R.id.btn_llamar);

        id = "eBAAUuCdfhYCgXsvJbSITzRdvc12";

        botonesHelp();
        datos();
        //getSoporteUid();

        return vista;
    }

    public void datos(){
        ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        nombre = snapshot.child(id).child("nombre").getValue().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("The read failed: ");
                    }
                });

    }

    /*private void getSoporteUid() {


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String uidSoporte = dataSnapshot.child("eBAAUuCdfhYCgXsvJbSITzRdvc12").child("uid").getValue().toString();
                Log.d("AAA",""+uidSoporte);
            }

            //  asasd
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });

    }*/


    public void botonesHelp(){

        btn_chat.setOnClickListener(new View.OnClickListener() {
            final String id_chat = refKey.push().getKey();

            @Override
            public void onClick(View v) {

                bundle.putString("titulo", nombre);
                bundle.putString("id", id);
                bundle.putString("chatId", id_chat);

                Fragment fragment = new ChatFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_contenedor, fragment);
                transaction.commit();
            }
        });

    }
}
