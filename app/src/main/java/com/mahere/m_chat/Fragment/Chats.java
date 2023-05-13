package com.mahere.m_chat.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mahere.m_chat.Adapters.ChatAdapter;
import com.mahere.m_chat.Models.User;
import com.mahere.m_chat.R;
import com.mahere.m_chat.databinding.FragmentChatBinding;

import java.util.ArrayList;

public class Chats extends Fragment {

    public Chats() {
    }

    ArrayList<User> userList;
    FragmentChatBinding binding;
    FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        binding = FragmentChatBinding.bind(view);

        database = FirebaseDatabase.getInstance();
        userList = new ArrayList<>();

        ChatAdapter adapter = new ChatAdapter(userList, getContext());

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rvChats.setLayoutManager(linearLayoutManager);
        binding.rvChats.setAdapter(adapter);


        database.getReference().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren() ) {
                    User user = dataSnapshot.getValue(User.class);
                    user.setUserId(dataSnapshot.getKey());

                    if (!user.getUserId().equals(FirebaseAuth.getInstance().getUid())) {
                        userList.add(user);
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}