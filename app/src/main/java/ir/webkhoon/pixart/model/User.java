package ir.webkhoon.pixart.model;

import android.graphics.Bitmap;

import java.util.List;

public class User {
    public static User currentUser;
    public String username;
    public String password;
    public Bitmap profile;
    public String description;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
