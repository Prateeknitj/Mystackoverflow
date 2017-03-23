package com.example.prateek.mystackoverflow.models;

import java.util.List;

/**
 * Created by Maxwell on 15/08/2016.
 */
public class TagScore {
    List<User> user;
    int post_count;
    int score;

    public List<User> getUser() {
        return user;
    }

    public int getPost_count() {
        return post_count;
    }

    public int getScore() {
        return score;
    }
}
