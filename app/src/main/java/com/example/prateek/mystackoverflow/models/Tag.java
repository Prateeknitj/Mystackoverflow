package com.example.prateek.mystackoverflow.models;

/**
 * Created by Maxwell on 15/08/2016.
 */
public class Tag {
    boolean has_synonyms;
    boolean is_moderator_only;
    boolean is_required;
    int count;
    String name;

    public boolean isHas_synonyms() {
        return has_synonyms;
    }

    public boolean is_moderator_only() {
        return is_moderator_only;
    }

    public boolean is_required() {
        return is_required;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }
}
