package com.mahere.m_chat.Activities.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mahere.m_chat.R;
import com.mahere.m_chat.Utilities.SessionManager;
import com.mahere.m_chat.databinding.ActivityOptBinding;

import java.util.concurrent.TimeUnit;

public class OPT_activity extends AppCompatActivity {

    ActivityOptBinding binding;
    String phoneNo;
    String otpId;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOptBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.wrongNumberTv.setOnClickListener(view -> {
            onDestroy();
            startActivity(new Intent(this, Login_Phone.class));
        });

        mAuth = FirebaseAuth.getInstance();
        Bundle bundle = getIntent().getExtras();
        phoneNo = bundle.getString("phoneNo");

        String text =  getString(R.string.wait_to_automatically_detect_an_sms_sent_to) + " " + phoneNo;
        binding.textView3.setText(text);

        Log.e("phoneNO",phoneNo);
        EditText otp = binding.otpEdt;
        generateOpt();

     binding.buttonTest.setOnClickListener(view -> {
         if(otp.getText().toString().isEmpty()){
             Toast.makeText(this, "Enter the OTP", Toast.LENGTH_SHORT).show();
         } else if (otp.getText().toString().length() != 6) {
             Toast.makeText(this, "Wrong OTP", Toast.LENGTH_SHORT).show();
         }
         else {
             PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpId,binding.otpEdt.getText().toString());
             signInWithPhoneAuthCredential(credential);
         }
     });

    }

    public void generateOpt(){

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
                            {
                                otpId = s;// when sim is not in same mobile
                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Toast.makeText(OPT_activity.this, "working", Toast.LENGTH_SHORT).show();
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(OPT_activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = task.getResult().getUser();
                        // Update UI

                        startActivity(new Intent(OPT_activity.this, Login_Profile.class));

                        Toast.makeText(OPT_activity.this, "Successful", Toast.LENGTH_SHORT).show();

                        new SessionManager(this).localStoreLoginDetail(phoneNo);
                    } else {
                        // Sign in failed, display a message and update the UI
                        Toast.makeText(OPT_activity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}