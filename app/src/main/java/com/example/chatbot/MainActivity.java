package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.chatbot.modals.ChatsModal;
import com.example.chatbot.modals.MsgModal;
import com.example.chatbot.modals.Retrofitapi;
import com.example.chatbot.modals.Rvadapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView chatsrv;
    private EditText usermessages;
    private ImageView sendbtn;
    private final String BOT_KEY="bot";
    private  final String USER_KEY="user";
    private ArrayList<ChatsModal> chatsModalArrayList;

    private Rvadapter rvadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        chatsrv=findViewById(R.id.recylerchats);
        usermessages=findViewById(R.id.edtmsg);
        sendbtn=findViewById(R.id.sendbtn);
        chatsModalArrayList=new ArrayList<>();
        rvadapter= new Rvadapter(chatsModalArrayList,this);
        LinearLayoutManager manager= new LinearLayoutManager(this);
        chatsrv.setLayoutManager(manager);
        chatsrv.setAdapter(rvadapter);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usermessages.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please type  somethhing", Toast.LENGTH_SHORT).show();
                return;
                }
                else
                {
                    getResponse(usermessages.getText().toString());
                    usermessages.setText(null);
                }

            }
        });

    }

    private void getResponse(String messagetobesent)
    {
        chatsModalArrayList.add(new ChatsModal(messagetobesent,USER_KEY));
        rvadapter.notifyDataSetChanged();
        String url="http://api.brainshop.ai/get?bid=167751&key=3gj2Os22RSTvSxdY&uid=[uid]&msg="+messagetobesent;
        String BASE_URL="http://api.brainshop.ai/";
        Retrofit retrofit= new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Retrofitapi retrofitapi= retrofit.create(Retrofitapi.class);
        Call<MsgModal> call=retrofitapi.getMessage(url);
        call.enqueue(new Callback<MsgModal>() {
            @Override
            public void onResponse(Call<MsgModal> call, Response<MsgModal> response) {
                if(response.isSuccessful())
                {
                    MsgModal modal= response.body();
                    chatsModalArrayList.add(new ChatsModal(modal.getCnt(),BOT_KEY));
                    rvadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MsgModal> call, Throwable t) {
                chatsModalArrayList.add(new ChatsModal("Please revert your statement",BOT_KEY));
                rvadapter.notifyDataSetChanged();

            }
        });



    }

}