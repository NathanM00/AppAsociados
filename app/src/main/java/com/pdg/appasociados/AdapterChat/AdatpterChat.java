package com.pdg.appasociados.AdapterChat;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pdg.appasociados.ModelChatUser.ChatModel;
import com.pdg.appasociados.R;

import java.util.Calendar;
import java.util.List;

public class AdatpterChat extends RecyclerView.Adapter<AdatpterChat.viewHolderAdapter> {

    List<ChatModel> chatList;
    Context context;

    public static final int msg_right = 1;
    public static final int msg_left = 0;

    Boolean soloRight = false;
    FirebaseUser fUser;

    public AdatpterChat(List<ChatModel> chatList, Context context) {
        this.chatList = chatList;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == msg_right){
            View vista = LayoutInflater.from(context).inflate(R.layout.chat_item_right,parent, false);
            return new AdatpterChat.viewHolderAdapter(vista);
        }else{
            View vista = LayoutInflater.from(context).inflate(R.layout.chat_item_left,parent, false);
            return new AdatpterChat.viewHolderAdapter(vista);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderAdapter holder, int position) {
        ChatModel chats = chatList.get(position);

        holder.tv_msg.setText(chats.getMensaje());

        if(soloRight){

            if(chats.getVisto().equals("si")){
                holder.tv_estado.setText("Visto");
            }else{
                holder.tv_estado.setText("Entregado");
            }

            final Calendar c = Calendar.getInstance();
            final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            holder.tv_hora.setText(timeFormat.format(c.getTime()));

        }//fin del solo right

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class viewHolderAdapter extends RecyclerView.ViewHolder {

        TextView tv_msg;
        TextView tv_estado;
        TextView tv_hora;

        public viewHolderAdapter(@NonNull View itemView) {
            super(itemView);

            tv_msg = itemView.findViewById(R.id.tv_msg);
            tv_estado = itemView.findViewById(R.id.tv_estado);
            tv_hora = itemView.findViewById(R.id.tv_hora);
        }
    }

    @Override
    public int getItemViewType(int position) {
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        if(chatList.get(position).getEnvia().equals(fUser.getUid())){
            soloRight = true;
            return msg_right;
        }else{
            soloRight = false;
            return msg_left;
        }
    }
}
