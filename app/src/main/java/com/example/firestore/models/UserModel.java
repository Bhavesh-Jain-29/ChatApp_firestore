package com.example.firestore.models;

public class UserModel {

    public String userMame;
    public String userImage;
    public String userId;
    public String userEmail;

    public UserModel(){

    }

    public UserModel(String userMame, String userImage, String userId, String userEmail) {
        this.userMame = userMame;
        this.userImage = userImage;
        this.userId = userId;
        this.userEmail = userEmail;
    }

    public String getUserMame() {
        return userMame;
    }

    public void setUserMame(String userMame) {
        this.userMame = userMame;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
