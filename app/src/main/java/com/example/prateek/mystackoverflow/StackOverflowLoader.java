package com.example.prateek.mystackoverflow;

import com.example.prateek.mystackoverflow.models.Answer;
import com.example.prateek.mystackoverflow.models.Items;
import com.example.prateek.mystackoverflow.models.Question;
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


    public Call<Items<Question>> loadAllQuestions(String sort){
        Call<Items<Question>> call = stackOverflowAPI.loadAllQuestions(sort.toLowerCase());
        return call;
    }

    public Call<Items<Answer>> loadAnswersFromQuestion(int id){
        Call<Items<Answer>> call = stackOverflowAPI.loadAnswersFromQuestion(id);
        return call;
    }

    public Call<Items<Question>> loadSearchQuestions(String intitle){
        Call<Items<Question>> call = stackOverflowAPI.loadSearchQuestions(intitle);
        return call;
    }
}
