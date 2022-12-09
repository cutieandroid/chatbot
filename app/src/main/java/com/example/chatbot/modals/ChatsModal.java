package com.example.chatbot.modals;
//This is class for recycler view which will hold the data to be displayed in rv

public class ChatsModal {
    private String message;
    private String sender;

    public ChatsModal(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
