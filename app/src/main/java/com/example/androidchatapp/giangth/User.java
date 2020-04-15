package com.example.androidchatapp.giangth;

public class User {
    private String username;
    private String id;
    private String imageURL;

    public User(String userName, String id, String urlImage) {
        this.username = userName;
        this.id = id;
        this.imageURL = urlImage;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
