package com.mahere.m_chat.Activities.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mahere.m_chat.Adapters.MessageAdapter;
import com.mahere.m_chat.Models.MessageModel;
import com.mahere.m_chat.Models.User;
import com.mahere.m_chat.R;
import com.mahere.m_chat.databinding.ActivityDemoGroupBinding;

import java.util.ArrayList;
import java.util.Date;

public class DemoGroup extends AppCompatActivity {

    ActivityDemoGroupBinding binding;
    ArrayList<MessageModel> messageModels;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDemoGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        messageModels = new ArrayList<>();


        final String senderId = FirebaseAuth.getInstance().getUid();

        final MessageAdapter messageAdapter = new MessageAdapter(messageModels, this);
        binding.rvUserchat.setAdapter(messageAdapter);

        RecyclerView.LayoutManager linerLayoutManager = new LinearLayoutManager(this);
        binding.rvUserchat.setLayoutManager(linerLayoutManager);


        database.getReference().child("Group Chat")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageModels.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren() ) {
                    MessageModel messageModel = dataSnapshot.getValue(MessageModel.class);
                    messageModel.setUserId(dataSnapshot.getKey());
                    messageModels.add(messageModel);

                }
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.sendBtn.setOnClickListener(view -> {
            String msg = binding.edMessage.getText().toString();
            final MessageModel msgModel = new MessageModel(senderId, msg);
            msgModel.setTimeStamp(new Date().getTime());
            binding.edMessage.setText("");

            database.getReference().child("Group Chat")
                    .push()
                    .setValue(msgModel).addOnSuccessListener(unused -> {
                    });
        });
    }

}