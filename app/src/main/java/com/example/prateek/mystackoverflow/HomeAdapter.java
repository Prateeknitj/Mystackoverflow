package com.example.prateek.mystackoverflow;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.prateek.mystackoverflow.models.Items;
import com.example.prateek.mystackoverflow.models.Question;


public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    Items<Question> questionList;
    Context ctx;

    public HomeAdapter(Items<Question> questionList, Context ctx){
        this.questionList = questionList;
        this.ctx = ctx;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent, false);

        return new HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        final Question question = questionList.getItems().get(position);
        String score = String.valueOf(question.getScore()) + System.getProperty ("line.separator") + "Votes";
        holder.tvTitleQuestion.setText(Html.fromHtml(question.getTitle()));
        holder.tvAuthor.setText(question.getOwner().getDisplay_name());
        holder.tvVotes.setText(score);

        holder.cvItemQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionDetailFragment questionDetailFragment = new QuestionDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("idQuestion",question.getQuestion_id());
                bundle.putString("titleQuestion", question.getTitle());
                bundle.putString("bodyQuestion", question.getBody());
                bundle.putString("urlQuestion", question.getLink());

                questionDetailFragment.setArguments(bundle);

                ((MainActivity)ctx).setFragment(questionDetailFragment, Constants.FRAGMENT_QUESTION_DETAIL);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(questionList != null && questionList.getItems() != null)
            return questionList.getItems().size();
        else
            return 0;
    }

    public void clearQuestionList(){
        if(questionList != null && questionList.getItems() != null)
            questionList.getItems().clear();
    }

    public void setQuestionList(Items<Question> questionList) {
        this.questionList = questionList;
    }
}
