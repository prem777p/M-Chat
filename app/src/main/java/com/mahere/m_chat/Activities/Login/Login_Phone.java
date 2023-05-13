package com.mahere.m_chat.Activities.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mahere.m_chat.databinding.ActivityLoginPhoneBinding;

public class Login_Phone extends AppCompatActivity {

    String phoneNo;
    ActivityLoginPhoneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginPhoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.countryCode.getSelectedCountryCode();
        String countryName = binding.countryCode.getSelectedCountryName();
        binding.countryName.setText(countryName);
        binding.countryCode.setOnCountryChangeListener(() -> binding.countryName.setText(binding.countryCode.getSelectedCountryName()));


        binding.nextBtn.setOnClickListener(view -> {
            if (binding.phoneNoEdt.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
            }
            else {
                phoneNo = "+" + binding.countryCode.getSelectedCountryCode() + binding.phoneNoEdt.getText().toString();
                startActivity(new Intent(this,OPT_activity.class)
                        .putExtra("phoneNo",phoneNo)) ;
            }
        });
//        binding.nextBtn.setOnClickListener(view -> startActivity(new Intent(this, Login_Profile.class)));
    }
}