package com.example.prateek.mystackoverflow;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class HomeViewHolder extends RecyclerView.ViewHolder {
    public TextView tvTitleQuestion, tvAuthor, tvVotes, tvAnswer;
    public CardView cvItemQuestion;

    public HomeViewHolder(View v) {
        super(v);

        tvTitleQuestion = (TextView) v.findViewById(R.id.tvTitleQuestion);
        tvAuthor = (TextView) v.findViewById(R.id.tvAuthor);
        tvVotes = (TextView) v.findViewById(R.id.tvVotes);
        tvAnswer = (TextView) v.findViewById(R.id.tvAnswer);
        cvItemQuestion = (CardView) v.findViewById(R.id.cvItemQuestion);
    }
}
