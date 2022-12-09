package com.example.chatbot.modals;

//this class has an interface which fetches the requests from the api

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Retrofitapi {

    @GET
    Call<MsgModal> getMessage(@Url String url);   //we will store the requests in the msg modal class
}
