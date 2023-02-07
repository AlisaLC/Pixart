package ir.webkhoon.pixart.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Post {
    public static List<Post> posts;

    static {
        posts = new ArrayList<>();
    }

    public Bitmap bitmap;
    public User owner;
    public String description;
    public Set<User> likes;

    public Post(User user, Bitmap bitmap, String description) {
        this.owner = user;
        this.bitmap = bitmap;
        this.description = description;
        this.likes = new HashSet<>();
    }
}
