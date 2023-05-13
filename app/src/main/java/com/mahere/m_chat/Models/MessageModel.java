package com.mahere.m_chat.Models;

public class MessageModel {

    String userId , megId;
    String message;
    long timeStamp;

    public MessageModel(){}

    public MessageModel(String userId, String message, long timeStamp) {
        this.userId = userId;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public MessageModel(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public String getMegId() {
        return megId;
    }

    public void setMegId(String megId) {
        this.megId = megId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
