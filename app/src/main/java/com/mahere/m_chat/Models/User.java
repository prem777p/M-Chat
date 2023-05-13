package com.mahere.m_chat.Models;

public class User {

    String profileImage, userName, userId, lastMessage, about;

    public User(String profileImage, String userName, String mail, String userId, String lastMessage) {
        this.profileImage = profileImage;
        this.userName = userName;
        this.userId = userId;
        this.lastMessage = lastMessage;
    }

    public  User(){}

    public User(String profileImage, String userName, String userId,String about) {
        this.profileImage = profileImage;
        this.userName = userName;
        this.about = about;
        this.userId = userId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String lastMessage) {
        this.about = about;
    }
}
