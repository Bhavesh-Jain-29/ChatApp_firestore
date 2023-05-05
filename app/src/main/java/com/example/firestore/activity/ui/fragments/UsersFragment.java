package com.example.firestore.activity.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firestore.R;
import com.example.firestore.activity.HomeActivity;
import com.example.firestore.adaptor.AdaptorUser;
import com.example.firestore.databinding.FragmentUsersBinding;
import com.example.firestore.models.UserModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class UsersFragment extends Fragment {

    AdaptorUser adaptorUser;
    FirebaseFirestore db;
    private FragmentUsersBinding binding;

    ArrayList<UserModel> userList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUsersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        userList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        RecyclerView rv;




        getUsers();
        return root;
    }



    private void getUsers(){
        db.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Log.w("TAG", "listen:error", e);
                    return;
                }

                for (DocumentChange dc : snapshots.getDocumentChanges()) {
                    switch (dc.getType()) {
                        case ADDED:
                            UserModel userModel = dc.getDocument().toObject(UserModel.class);

                            userList.add(userModel);
                            Log.d("TAG", "New city: " + userModel.userMame);
                            break;
                        case MODIFIED:
                            Log.d("TAG", "Modified city: " + dc.getDocument().getData());
                            break;
                        case REMOVED:
                            Log.d("TAG", "Removed city: " + dc.getDocument().getData());
                            break;
                    }

                    //binding.recyclerUser.setAdapter(new AdaptorUser(userList, getContext()));
                }

                if(adaptorUser==null){
                    adaptorUser = new AdaptorUser(userList, getContext());
                    binding.recyclerUser.setAdapter(adaptorUser);
                }
                else
                {
                    adaptorUser.updateList(userList);
                }

            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adaptorUser = null;
    }
}