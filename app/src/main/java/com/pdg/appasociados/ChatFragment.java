package com.pdg.appasociados;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pdg.appasociados.AdapterChat.AdatpterChat;
import com.pdg.appasociados.ModelChatUser.ChatModel;

import java.util.ArrayList;
import java.util.Calendar;

public class ChatFragment extends Fragment {

    String chatTitle  = "null";
    String id  = "null";
    String chat_id  = "null";

    String receptorId;
    String idChat,nombre;

    RecyclerView rv_chat;
    AdatpterChat adapter;
    ArrayList<ChatModel> chatList;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref = db.getReference("Chats");
    DatabaseReference refUser ;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    TextView tv_chatTitle;
    EditText et_txtmsg;
    Button btn_send;

    View vista;
    Bundle bundle = new Bundle();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            chatTitle = getArguments().getString("titulo", "Pos es null");
            id = getArguments().getString("id", "Pos es null");
            chat_id = getArguments().getString("chatId", "Pos es null");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_chat, container, false);

        tv_chatTitle = vista.findViewById(R.id.tv_chatTitle);

        et_txtmsg = vista.findViewById(R.id.et_txtmsg);
        btn_send = vista.findViewById(R.id.btn_send);

        refUser= db.getReference("usuarios").child(auth.getUid());

        rv_chat = vista.findViewById(R.id.rv_listaMensajes);
        rv_chat.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);

        rv_chat.setLayoutManager(linearLayoutManager);
        chatList = new ArrayList<>( );
        adapter = new AdatpterChat(chatList, this.getContext());

        idChat = chat_id;

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg();
            }
        });

        tv_chatTitle.setText(chatTitle);
        receptorId = id;

        rv_chat.setAdapter(adapter);
        getNombre();
        leerMensajes();

        return vista;
    }

    public void sendMsg (){
        String msg = et_txtmsg.getText().toString();

        if (!msg.isEmpty()){
            final Calendar c = Calendar.getInstance();
            final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String idPush = ref.push().getKey();

            ChatModel chatMsg = new ChatModel(user.getUid(), receptorId, msg, "no", timeFormat.format(c.getTime()));

            ref.child(idChat).child(idPush).setValue(chatMsg);
            ref.child(idChat).child("id").setValue(idChat);

            et_txtmsg.setText("");

            Toast.makeText(getContext(), "Mensaje enviado", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getContext(), "El mensaje está vacío", Toast.LENGTH_SHORT).show();
        }
    }

    public void leerMensajes(){
        ref.child(idChat).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    chatList.removeAll(chatList);
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        if (!dataSnapshot.getValue().equals(idChat)) {
                            ChatModel chat = dataSnapshot.getValue(ChatModel.class);
                            chatList.add(chat);
                            Log.d("models",""+chatList);
                            setScroll();
                        }
                    }
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getNombre() {
        refUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                InfoUser info = dataSnapshot.getValue(InfoUser.class);
                nombre = info.getNombre();

                Log.d("aaa",nombre);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: ");
            }
        });

    }

    public void setScroll(){
        rv_chat.scrollToPosition(adapter.getItemCount()-1);

    }

}
