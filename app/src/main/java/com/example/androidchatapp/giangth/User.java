package com.example.androidchatapp.giangth;

public class User {
    private String username;
    private String id;
    private String imageURL;
    private String status;
    private String search;

    public User(String userName, String id, String urlImage, String status, String search) {
        this.username = userName;
        this.id = id;
        this.imageURL = urlImage;
        this.status = status;
        this.search = search;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
