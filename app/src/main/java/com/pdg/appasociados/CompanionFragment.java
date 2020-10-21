package com.pdg.appasociados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CompanionFragment extends Fragment {

    View vista;

    FirebaseAuth auth;
    FirebaseDatabase db;

    EditText et_correo;

    String subject;
    String message;

    Button btn_registrar;

    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_companion, container, false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        et_correo = vista.findViewById(R.id.et_correo);

        btn_registrar = vista.findViewById(R.id.btn_registrar);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        return vista;
    }

    public void sendEmail(){
        String correo = et_correo.getText().toString();
        subject = "Invitación de acompañante FonaviApp - Fonavemcali";
        message = "El Pepe te ha invitado para que seas su acompañante en FonaviApp, la aplicación móvil de Fonaviemcali, descargala a continiación!";

        JavaMailAPI javaMailAPI = new JavaMailAPI(getActivity(), correo, subject, message);

        javaMailAPI.execute();

        Toast.makeText(getActivity(), "Correo enviado", Toast.LENGTH_SHORT).show();

    }

}
