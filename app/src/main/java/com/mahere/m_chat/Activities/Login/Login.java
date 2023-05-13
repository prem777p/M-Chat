package com.mahere.m_chat.Activities.Login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mahere.m_chat.Activities.Home.Home;
import com.mahere.m_chat.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.acceptAndContinueBtn.setOnClickListener(view -> startActivity(new Intent(this, Login_Phone.class)));
//        binding.acceptAndContinueBtn.setOnClickListener(view -> startActivity(new Intent(this, Login_Profile.class)));

    }
}