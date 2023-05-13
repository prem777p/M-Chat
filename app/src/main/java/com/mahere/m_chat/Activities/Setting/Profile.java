package com.mahere.m_chat.Activities.Setting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mahere.m_chat.Models.User;
import com.mahere.m_chat.R;
import com.mahere.m_chat.databinding.ActivityProfileBinding;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Objects;

public class Profile extends AppCompatActivity {

    ActivityProfileBinding binding;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();


        binding.userImg.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 33);
        });


        database.getReference().child("user").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);

                        Picasso.get()
                                .load(user.getProfileImage())
                                .placeholder(R.drawable.img_user)
                                .into(binding.userImg);

                        binding.userNameEdt.setText(user.getUserName());
                        binding.userAboutEdt.setText(user.getAbout());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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

        binding.saveBtn.setOnClickListener(view -> {
            String username = binding.userNameEdt.getText().toString();
            String about = binding.userAboutEdt.getText().toString();

            HashMap<String, Object> hm = new HashMap<>();
            hm.put("userName", username);
            hm.put("about", about);

            database.getReference().child("users").child(FirebaseAuth.getInstance().getUid()).updateChildren(hm);
        });
    }
}