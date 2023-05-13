package com.mahere.m_chat.Activities.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mahere.m_chat.Adapters.MessageAdapter;
import com.mahere.m_chat.Models.MessageModel;
import com.mahere.m_chat.R;
import com.mahere.m_chat.databinding.ActivityUserChatBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class UserChat extends AppCompatActivity {

    ActivityUserChatBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        final String senderId = auth.getUid();
        String receiverId = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("userName");
        String userImg = getIntent().getStringExtra("userImg");

        binding.tvUserName.setText(userName);

        Picasso.get()
                .load(userImg)
                .placeholder(R.drawable.img_user)
                .into(binding.ivUserImg);


        binding.backBtn.setOnClickListener(view -> {

           startActivity(new Intent(this, Home.class));
        });


        final ArrayList<MessageModel> messageModels = new ArrayList<>();

        final MessageAdapter messageAdapter = new MessageAdapter(messageModels, this, receiverId);
        binding.rvUserchat.setAdapter(messageAdapter);

        RecyclerView.LayoutManager linerLayoutManager = new LinearLayoutManager(this);
        binding.rvUserchat.setLayoutManager(linerLayoutManager);


        final String senderRoom = senderId + receiverId;
        final String receiverRoom = receiverId + senderId;

        // get data from firebase and refresh recycler view
        database.getReference().child("chats")
                        .child(senderRoom)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                messageModels.clear();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren() ) {
                                    MessageModel msgModel1 = dataSnapshot.getValue(MessageModel.class);
                                    msgModel1.setMegId(snapshot.getKey());

                                    messageModels.add(msgModel1);
                                }
                                messageAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

        binding.sendBtn.setOnClickListener(view -> {
            String msg = binding.edMessage.getText().toString();
            final  MessageModel msgModel = new MessageModel(senderId, msg);
            msgModel.setTimeStamp(new Date().getTime());

            binding.edMessage.setText("");
            database.getReference().child("chats")
                    .child(senderRoom)
                    .push()
                    .setValue(msgModel).addOnSuccessListener(unused -> database.getReference().child("chats")
                            .child(receiverRoom)
                            .push()
                            .setValue(msgModel).addOnSuccessListener(unused1 -> {
                            }));
        });
    }
}