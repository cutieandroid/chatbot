package com.example.chatbot.modals;

///This class holds the data coming from the api

public class MsgModal {

    private String cnt;

    public MsgModal(String cnt) {
        this.cnt = cnt;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }
}
