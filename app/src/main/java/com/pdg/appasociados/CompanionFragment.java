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
import com.google.firebase.database.FirebaseDatabase;

public class CompanionFragment extends Fragment {

    View vista;

    FirebaseAuth auth;
    FirebaseDatabase db;

    EditText et_nombre;
    EditText et_correo;
    EditText et_parentezco;

    Button btn_registrar;

    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_companion, container, false);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        et_nombre = vista.findViewById(R.id.et_nombre);
        et_correo = vista.findViewById(R.id.et_correo);
        et_parentezco = vista.findViewById(R.id.et_parentezco);

        btn_registrar = vista.findViewById(R.id.btn_registrar);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarCompa();
            }
        });

        return vista;
    }

    private void registrarCompa() {
        final String nombre = et_nombre.getText().toString().trim();
        final String correo = et_correo.getText().toString().trim();
        final String parentezco = et_parentezco.getText().toString().trim();

        if(nombre.isEmpty()){
            et_correo.setError("Por favor ingresar un correo valido");
            et_correo.requestFocus();
            return;
        }

        if(correo.isEmpty()){
            et_correo.setError("Por favor ingresar un correo valido");
            et_correo.requestFocus();
            return;
        }

        if(parentezco.isEmpty()){
            et_correo.setError("Por favor ingresar un correo valido");
            et_correo.requestFocus();
            return;
        }


        auth.createUserWithEmailAndPassword(correo, nombre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Compa compa = new Compa(nombre, correo, parentezco);

                    db.getReference("Acompanantes").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(compa).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();

                                Fragment fragment = new PerfilFragment();
                                fragment.setArguments(bundle);
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.fragment_contenedor, fragment);
                                transaction.commit();
                            }else{
                                Toast.makeText(getActivity(), "Registro fallido", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }
}
