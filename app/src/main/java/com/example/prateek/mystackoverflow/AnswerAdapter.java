package com.example.prateek.mystackoverflow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.prateek.mystackoverflow.models.Answer;
import com.example.prateek.mystackoverflow.models.Items;

import java.util.ArrayList;


public class AnswerAdapter extends RecyclerView.Adapter<AnswerViewHolder> {
    Items<Answer> answerItems;
    Context ctx;
    static String s=null;
    public AnswerAdapter(Items<Answer> answerItems, Context ctx) {
        this.answerItems = answerItems;
        this.ctx = ctx;
    }

    @Override
    public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_answer, parent, false);

        return new AnswerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AnswerViewHolder holder, int position) {
        final Answer answer = answerItems.getItems().get(position);

        String badgeBronze = String.valueOf(answer.getOwner().getBadge_counts().getBronze());
        String badgeSilver = String.valueOf(answer.getOwner().getBadge_counts().getSilver());
        String badgeGold = String.valueOf(answer.getOwner().getBadge_counts().getGold());
        String link = String.valueOf(answer.getLink());

        s=link;
        holder.tvAnswer.setText(Html.fromHtml(answer.getBody()));
        holder.tvAuthor.setText(answer.getOwner().getDisplay_name());
        holder.tvCountBronze.setText(badgeBronze);
        holder.tvCountSilver.setText(badgeSilver);
        holder.tvCountGold.setText(badgeGold);

        holder.shre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(ctx,ShareActivity.class);
                ctx.startActivity(it);
            }
        });
        holder.llAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(answer.getLink()));
                ctx.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(answerItems.getItems() != null)
            return answerItems.getItems().size();
        else
            return 0;
    }


    public void setAnswerItems(Items<Answer> answerItems) {
        this.answerItems = answerItems;
    }
}
