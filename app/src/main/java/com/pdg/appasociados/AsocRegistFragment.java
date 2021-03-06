package com.pdg.appasociados;

import android.content.Intent;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AsocRegistFragment extends Fragment {

    View vista;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    EditText et_nombre;
    EditText et_correo;
    EditText et_contra;

    Button btn_registrar;

    Bundle bundle = new Bundle();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_regist_asociado, container, false);

        et_nombre = vista.findViewById(R.id.et_nombre);
        et_correo = vista.findViewById(R.id.et_correo);
        et_contra = vista.findViewById(R.id.et_contra);
        btn_registrar = vista.findViewById(R.id.btn_registrar);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registraAsociado();
            }
        });

        return vista;
    }

    private void registraAsociado() {
        final String nombre = et_nombre.getText().toString().trim();
        final String correo = et_correo.getText().toString().trim();
        final String contra = et_contra.getText().toString().trim();


        if(nombre.isEmpty()){
            et_correo.setError("Por favor ingrese su nombre");
            et_correo.requestFocus();
            return;
        }

        if(correo.isEmpty()){
            et_correo.setError("Por favor ingresar un correo valido");
            et_correo.requestFocus();
            return;
        }

        if(contra.isEmpty()){
            et_contra.setError("Por favor ingresar una contrasena");
            et_contra.requestFocus();
            return;
        }


        auth.createUserWithEmailAndPassword(correo, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(nombre, correo, contra, true);

                    db.getReference("Acompanantes").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(getActivity(), "Registro fallido1", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(getActivity(), "Registro fallido2", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}

