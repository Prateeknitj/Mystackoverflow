package com.example.prateek.mystackoverflow;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class AnswerViewHolder extends RecyclerView.ViewHolder {
    public TextView tvAnswer, tvAuthor, tvCountGold, tvCountSilver, tvCountBronze;
    public LinearLayout llAnswer;

    public AnswerViewHolder(View v) {
        super(v);

        tvAnswer = (TextView) v.findViewById(R.id.tvAnswer);
        tvAuthor = (TextView) v.findViewById(R.id.tvAuthor);
        tvCountGold = (TextView) v.findViewById(R.id.tvCountGold);
        tvCountSilver = (TextView) v.findViewById(R.id.tvCountSilver);
        tvCountBronze = (TextView) v.findViewById(R.id.tvCountBronze);
        llAnswer = (LinearLayout) v.findViewById(R.id.llAnswer);
    }
}
