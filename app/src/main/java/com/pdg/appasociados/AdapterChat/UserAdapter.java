package com.pdg.appasociados.AdapterChat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pdg.appasociados.ModelChatUser.ChatUser;
import com.pdg.appasociados.R;
import com.pdg.appasociados.User;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolderAdapter> {

    List<ChatUser> userList;
    Context context;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public UserAdapter(List<ChatUser> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.userchat_item, parent, false);
        viewHolderAdapter holder = new viewHolderAdapter(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderAdapter holder, int position) {
        ChatUser userss = userList.get(position);

        holder.tv_chatTitle.setText(userss.getNombre());

        if(userss.getId().equals(user.getUid())){
            holder.cardView.setVisibility(View.GONE);
        }else{
            holder.cardView.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class viewHolderAdapter extends RecyclerView.ViewHolder {

        TextView tv_chatTitle;
        CardView cardView;

        public viewHolderAdapter(@NonNull View itemView) {
            super(itemView);

            tv_chatTitle = itemView.findViewById(R.id.tv_chatTitle);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
