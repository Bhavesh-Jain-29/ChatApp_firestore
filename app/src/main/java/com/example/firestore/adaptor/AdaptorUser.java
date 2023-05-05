package com.example.firestore.adaptor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firestore.R;
import com.example.firestore.activity.ChatActivity;
import com.example.firestore.activity.ui.fragments.ChatFragment;
import com.example.firestore.activity.ui.fragments.UsersFragment;
import com.example.firestore.models.UserModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AdaptorUser extends RecyclerView.Adapter<AdaptorUser.ViewHolder> {

    ArrayList<UserModel> list;
    Context context;

    public AdaptorUser(ArrayList<UserModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void updateList(ArrayList<UserModel> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_users, parent, false);

        ViewHolder VH = new ViewHolder(view);

        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel model = list.get(position);

        holder.name.setText(model.getUserMame());
//        Thread thread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                try  {
//                    //Your code goes here
//                    holder.icon.setImageBitmap(getBitmapFromURL(model.getUserImage()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        Glide.with(context)
                .load(model.getUserImage())
                .into(holder.icon);

//        thread.start();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("uName", model.userMame);
                intent.putExtra("RID", model.userId);
                intent.putExtra("uImage", model.userImage);
                context.startActivity(intent);

            }
        });

//        userImage = getIntent().getStringExtra("uImage");
//        userName = getIntent().getStringExtra("uName");
//        recieverID = getIntent().getStringExtra("RID");



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Name);
            icon = itemView.findViewById(R.id.iconID);
        }

    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

}
