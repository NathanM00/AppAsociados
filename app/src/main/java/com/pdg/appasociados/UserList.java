package com.pdg.appasociados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pdg.appasociados.AdapterChat.UserAdapter;
import com.pdg.appasociados.ModelChatUser.ChatUser;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserList extends Fragment {

    RecyclerView rv_recyclerviewUsers;
    ArrayList<ChatUser> userArrayList;
    LinearLayoutManager mLayoutManager;

    UserAdapter userAdapter;

    View vista;
    Bundle bundle = new Bundle();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_userlist, container, false);

        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        rv_recyclerviewUsers = vista.findViewById(R.id.rv_recyclerviewUsers);
        rv_recyclerviewUsers.setLayoutManager(mLayoutManager);

        userArrayList = new ArrayList<>();
        userAdapter = new UserAdapter(userArrayList, getContext());
        rv_recyclerviewUsers.setAdapter(userAdapter);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getInstance().getReference("Soporte");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    userArrayList.removeAll(userArrayList);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        ChatUser user = snapshot.getValue(ChatUser.class);
                        userArrayList.add(user);
                    }
                    userAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "no existen usuarios", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return vista;
    }


}
