package com.example.chatbot.modals;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbot.R;

import java.util.ArrayList;

public class Rvadapter extends RecyclerView.Adapter {

    private ArrayList<ChatsModal> chatsModalArrayList;
    private Context context;

    public Rvadapter(ArrayList<ChatsModal> chatsModalArrayList, Context context) {
        this.chatsModalArrayList = chatsModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch(viewType){
            case 0:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.usermsg,parent,false);
                 return  new UserViewHolder(view);

            case 1:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.botlayout,parent,false);
                return  new BotViewHolder(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ChatsModal chatsModal=chatsModalArrayList.get(position);

        switch (chatsModal.getSender()){
            case "user":
                ((UserViewHolder)holder).usertv.setText(chatsModal.getMessage());
                break;

            case "bot":
                ((BotViewHolder)holder).botmsgtv.setText(chatsModal.getMessage());
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (chatsModalArrayList.get(position).getSender())
        {
            case "user":
                return 0;

            case"bot":
                return  1;


            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return chatsModalArrayList.size();
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder{

        TextView usertv;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            usertv=itemView.findViewById(R.id.tvuser);

        }
    }

    public static class  BotViewHolder extends RecyclerView.ViewHolder{
         TextView botmsgtv;
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botmsgtv=itemView.findViewById(R.id.tvbot);
        }
    }
}
