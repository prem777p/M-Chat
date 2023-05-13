package com.mahere.m_chat.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mahere.m_chat.Activities.Home.UserChat;
import com.mahere.m_chat.Models.User;
import com.mahere.m_chat.R;
import com.mahere.m_chat.databinding.UserItemLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    ArrayList<User> userList;
    Context context;

    public ChatAdapter(ArrayList<User>userList, Context context) {
        this.userList = userList;
        this.context = context;
    }


    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_item_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        User user = userList.get(position);

        Picasso.get()
               .load(user.getProfileImage())
               .placeholder(R.drawable.img_user)
               .into(holder.binding.userImage);

        holder.binding.tvUsername.setText(user.getUserName());

        // set message on user item
        FirebaseDatabase.getInstance().getReference().child("chats")
                        .child(FirebaseAuth.getInstance().getUid() + user.getUserId())
                        .orderByChild("timestamp")
                        .limitToLast(1)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChildren()){
                                    for (DataSnapshot dataSnapshot : snapshot.getChildren()  ) {
                                        holder.binding.tvLastmessage.setText(dataSnapshot.child("message").getValue(String.class));
                                        holder.binding.tvTime.setText(dataSnapshot.child("timestamp").getValue(String.class));
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



        holder.itemView.setOnClickListener(view -> {
            context.startActivity( new Intent(context, UserChat.class)
                    .putExtra("userId", user.getUserId())
                    .putExtra("userImg", user.getProfileImage())
                    .putExtra("userName", user.getUserName()) );
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        UserItemLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = UserItemLayoutBinding.bind(itemView);
        }
    }

}
