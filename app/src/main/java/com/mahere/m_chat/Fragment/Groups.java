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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mahere.m_chat.Adapters.ChatAdapter;
import com.mahere.m_chat.Models.User;
import com.mahere.m_chat.R;
import com.mahere.m_chat.databinding.FragmentChatBinding;
import com.mahere.m_chat.databinding.FragmentGroupsBinding;

import java.util.ArrayList;

public class Groups extends Fragment {

    public Groups() {
        // Required empty public constructor
    }

    ArrayList<User> groupList;
    FirebaseDatabase database;
    FragmentGroupsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_groups, container, false);


//        database = FirebaseDatabase.getInstance();
//        groupList = new ArrayList<>();
//
//        ChatAdapter adapter = new ChatAdapter(groupList, getContext());
//
//        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        binding.rvGroups.setLayoutManager(linearLayoutManager);
//        binding.rvGroups.setAdapter(adapter);
//
//
//        database.getReference().child("group").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                int i =0;
//                for (DataSnapshot dataSnapshot : snapshot.getChildren() ) {
//                    User user = dataSnapshot.getValue(User.class);
//                    user.setUserId(dataSnapshot.getKey());
//                    groupList.add(user);
//
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        return view;
    }
}