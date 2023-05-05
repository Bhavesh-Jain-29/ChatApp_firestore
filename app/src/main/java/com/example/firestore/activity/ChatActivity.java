package com.example.firestore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.firestore.databinding.ActivityChatBinding;
import com.example.firestore.databinding.ActivityHomeBinding;
import com.example.firestore.databinding.FragmentProfileBinding;
import com.example.firestore.models.MessageModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {

    FirebaseUser user;
    FirebaseFirestore db;
    String userImage;
    String userName;
    String recieverID;
    private ActivityChatBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        userImage = getIntent().getStringExtra("uImage");
        userName = getIntent().getStringExtra("uName");
        recieverID = getIntent().getStringExtra("RID");

        binding.userName.setText(userName);
        Glide.with(this).load(userImage).into(binding.userImage);


        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.message.getText().toString().trim().isEmpty()){
                    binding.message.setError("Empty");
                    binding.message.requestFocus();
                }
                else
                {
                    sendMessage(binding.message.getText().toString().trim());
                    binding.message.setText("");
                }

            }
        });



    }

    public void sendMessage(String message){
        updateReciever(message);
        updateSender(message);
    }

    public void updateSender(String message){
        DateTimeFormatter dtf = null;
        LocalDateTime now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            now = LocalDateTime.now();

        }

        String time = new Date().toString();
        MessageModel messageModel = new MessageModel(message, time, user.getUid());

        db.collection("personalChat").document(user.getUid().toString())
                .collection("myChat").document(recieverID).collection("messages")
                .document().set(messageModel);

        HashMap map = new HashMap();
        map.put("online", true);
        HashMap lastMessage= new HashMap();
        map.put("lastMessage", message);
        db.collection("personalChat").document(user.getUid().toString()).set(map);
        db.collection("personalChat").document(user.getUid().toString()).collection("myChat").document(recieverID).set(lastMessage);

    }
    public void updateReciever(String message) {

        DateTimeFormatter dtf = null;
        LocalDateTime now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            now = LocalDateTime.now();

        }
        String time = new Date().toString();
        MessageModel messageModel = new MessageModel(message, time, user.getUid());

        db.collection("personalChat").document(recieverID)
                .collection("myChat").document(user.getUid().toString()).collection("messages")
                .document().set(messageModel);
        HashMap map = new HashMap();
        map.put("online", true);
        HashMap lastMessage= new HashMap();
        map.put("lastMessage", message);
        db.collection("personalChat").document(recieverID).set(map);
        db.collection("personalChat").document(recieverID).collection("myChat").document(user.getUid().toString()).set(lastMessage);


    }

}