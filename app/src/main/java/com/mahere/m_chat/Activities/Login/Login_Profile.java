package com.mahere.m_chat.Activities.Login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mahere.m_chat.Activities.Home.Home;
import com.mahere.m_chat.Models.User;
import com.mahere.m_chat.databinding.ActivityLoginProfileBinding;

public class Login_Profile extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;
    ActivityLoginProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        binding.userImg.setOnClickListener(view -> {
            Toast.makeText(Login_Profile.this, "upload successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 33);
        });

        binding.nextBtn.setOnClickListener(view -> {
            if (binding.userNameEdt.getText().toString().isEmpty() || binding.userAboutEdt.getText().toString().isEmpty()) {
                Toast.makeText(this, "Fill the Fields", Toast.LENGTH_SHORT).show();
            }
            else {
                nextButton();
            }
        });

    }

    private void nextButton(){
        String username = binding.userNameEdt.getText().toString();
        String about = binding.userAboutEdt.getText().toString();
        String userId = auth.getCurrentUser().getUid();
        String phoneNumber = auth.getCurrentUser().getPhoneNumber();
        String img = "6";

        User user = new User("img", username,userId,about);

        database.getReference().child("users").child(userId).setValue(user);

        binding.nextBtn.setOnClickListener(view -> startActivity(new Intent(this, Home.class)) );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data.getData() != null) {
            Uri sFile = data.getData();
            binding.userImg.setImageURI(sFile);

            final StorageReference storageReference = storage.getReference().child("profileImg")
                    .child(FirebaseAuth.getInstance().getUid());
            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    database.getReference().child("users").child(FirebaseAuth.getInstance().getUid())
                            .child("profileImg").setValue(uri.toString());

                }
            });
        }
    }
}