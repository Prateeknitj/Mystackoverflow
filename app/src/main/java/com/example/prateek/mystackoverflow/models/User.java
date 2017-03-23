package com.example.prateek.mystackoverflow.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maxwell on 15/08/2016.
 */
public class User {
    @SerializedName("badge_counts")
    BadgeCounts badgeCounts;
    int account_id;
    boolean is_employee;
    String last_access_date;
    int age;
    int reputation;
    String creation_date;
    int user_id;
    String user_type;
    int accept_rate;
    String location;
    @SerializedName("website_url")
    String websiteUrl;
    String link;
    String profile_image;
    String display_name;

    public BadgeCounts getBadgeCounts() {
        return badgeCounts;
    }

    public int getAccount_id() {
        return account_id;
    }

    public boolean is_employee() {
        return is_employee;
    }

    public String getLast_access_date() {
        return last_access_date;
    }

    public int getAge() {
        return age;
    }

    public int getReputation() {
        return reputation;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public int getAccept_rate() {
        return accept_rate;
    }

    public String getLocation() {
        return location;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String getLink() {
        return link;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public String getDisplay_name() {
        return display_name;
    }
}
