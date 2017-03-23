package com.example.prateek.mystackoverflow;

import com.example.prateek.mystackoverflow.models.Answer;
import com.example.prateek.mystackoverflow.models.Badges;
import com.example.prateek.mystackoverflow.models.Comment;
import com.example.prateek.mystackoverflow.models.Items;
import com.example.prateek.mystackoverflow.models.Question;
import com.example.prateek.mystackoverflow.models.Tag;
import com.example.prateek.mystackoverflow.models.TagScore;
import com.example.prateek.mystackoverflow.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StackOverflowLoader {
    StackOverflowAPI stackOverflowAPI;

    public StackOverflowLoader(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        stackOverflowAPI = retrofit.create(StackOverflowAPI.class);
    }

    public Call<Items<Question>> loadQuestionsByTag(String tag, String sort){
        Call<Items<Question>> call = stackOverflowAPI.loadQuestionsByTag(tag, sort);
        return call;
    }

    public Call<Items<Question>> loadAllQuestions(String sort){
        Call<Items<Question>> call = stackOverflowAPI.loadAllQuestions(sort.toLowerCase());
        return call;
    }

    public Call<Items<Answer>> loadAnswersFromQuestion(int id){
        Call<Items<Answer>> call = stackOverflowAPI.loadAnswersFromQuestion(id);
        return call;
    }

    public Call<Items<Comment>> loadCommentsFromQuestion(int id){
        Call<Items<Comment>> call = stackOverflowAPI.loadCommentsFromQuestion(id);
        return call;
    }

    public Call<Items<Tag>> loadPopularTags(){
        Call<Items<Tag>> call = stackOverflowAPI.loadPopularTags();
        return call;
    }

    public Call<Items<TagScore>> loadTopAskersByTag(String tag){
        Call<Items<TagScore>> call = stackOverflowAPI.loadTopAskersByTag(tag);
        return call;
    }

    public Call<Items<TagScore>> loadTopAnswerersByTag(String tag){
        Call<Items<TagScore>> call = stackOverflowAPI.loadTopAnswerersByTag(tag);
        return call;
    }

    public Call<Items<User>> loadUsersByReputation(){
        Call<Items<User>> call = stackOverflowAPI.loadUsersByReputation();
        return call;
    }

    public Call<Items<Answer>> loadAnsersFromUserId(int id){
        Call<Items<Answer>> call = stackOverflowAPI.loadAnsersFromUserId(id);
        return call;
    }

    public Call<Items<Badges>> loadBadgesFromUserId(int id){
        Call<Items<Badges>> call = stackOverflowAPI.loadBadgesFromUserId(id);
        return call;
    }

    public Call<Items<Comment>> loadCommentsFromUserId(int id){
        Call<Items<Comment>> call = stackOverflowAPI.loadCommentsFromUserId(id);
        return call;
    }

    public Call<Items<Tag>> loadTagsFormUserId(int id){
        Call<Items<Tag>> call = stackOverflowAPI.loadTagsFormUserId(id);
        return call;
    }

    public Call<Items<Badges>> loadBadges(){
        Call<Items<Badges>> call = stackOverflowAPI.loadBadges();
        return call;
    }

    public Call<Items<Question>> loadSearchQuestions(String intitle){
        Call<Items<Question>> call = stackOverflowAPI.loadSearchQuestions(intitle);
        return call;
    }
}
