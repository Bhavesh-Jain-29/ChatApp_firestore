package com.example.firestore.models;

public class MessageModel {
    public String message;
    public String timeStamp;
    public String userId;

    public MessageModel(String message, String timeStamp, String userId) {
        this.message = message;
        this.timeStamp = timeStamp;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getUserId() {
        return userId;
    }
}
