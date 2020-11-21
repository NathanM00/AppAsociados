package com.pdg.appasociados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ChatFragment extends Fragment {

    String chatTitle  = "null";
    //String id  = "null";

    TextView tv_chatTitle, tv_id;

    View vista;
    Bundle bundle = new Bundle();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            chatTitle = getArguments().getString("titulo", "Pos es null");
            //id = getArguments().getString("id", "Pos es null");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_chat, container, false);

        tv_chatTitle = vista.findViewById(R.id.tv_chatTitle);
        tv_id = vista.findViewById(R.id.tv_id);

        tv_chatTitle.setText(chatTitle);
        //tv_id.setText(id);

        return vista;
    }

}
