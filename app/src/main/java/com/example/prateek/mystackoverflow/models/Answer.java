package com.example.prateek.mystackoverflow.models;

/**
 * Created by Maxwell on 12/08/2016.
 */
public class Answer {
    Owner owner;
    String answer_id;
    String question_id;
    String body;
    String link;

    public String getAnswer_id() {
        return answer_id;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public String getBody() {
        return body;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getLink() {
        return link;
    }
}
