package com.example.firestore.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.firestore.R;
import com.example.firestore.models.MessageModel;
import com.example.firestore.models.UserModel;

import java.util.ArrayList;


public class AdaptorMessage extends RecyclerView.Adapter<AdaptorMessage.ViewHolder> {

    ArrayList<MessageModel> list;
    Context context;
    MessageModel model;

    public AdaptorMessage(ArrayList<MessageModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void updateList(ArrayList<MessageModel> list){
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //if();
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);

        ViewHolder VH = new ViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView rMessage;
        TextView rTime;
        TextView sMessage;
        TextView sTime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rMessage = itemView.findViewById(R.id.recieverMessage);
            rTime = itemView.findViewById(R.id.recieverTime);
            sMessage = itemView.findViewById(R.id.senderMessage);
            sTime = itemView.findViewById(R.id.senderTime);
        }

    }
}


