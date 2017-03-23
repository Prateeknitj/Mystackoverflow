package com.example.prateek.mystackoverflow.models;

public class Question {
    Owner owner;
    int answer_count;
    int score;
    int question_id;
    String title;
    String link;
    String body;


    public Owner getOwner() {
        return owner;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public int getScore() {
        return score;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getBody() {
        return body;
    }
}
