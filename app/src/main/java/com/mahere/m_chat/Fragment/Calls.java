package com.mahere.m_chat.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.mahere.m_chat.Adapters.ChatAdapter;
import com.mahere.m_chat.R;
import com.mahere.m_chat.databinding.FragmentCallsBinding;


public class Calls extends Fragment {

    FragmentCallsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_calls, container, false);
        binding = FragmentCallsBinding.bind(view);


        return view;
    }
}