package com.example.prateek.mystackoverflow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.prateek.mystackoverflow.models.Answer;
import com.example.prateek.mystackoverflow.models.Items;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuestionDetailFragment extends Fragment implements Callback<Items<Answer>> {
    RecyclerView.LayoutManager layoutManager;
    AnswerAdapter adapter;
    Items<Answer> answerList = new Items<>();
    int id;
    String title, body, url;
    Call<Items<Answer>> call;
    TextView tvTitle;
    RecyclerView rvAswers;
    TextView tvBody;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(((MainActivity) getActivity()).getSupportActionBar() != null)
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(Constants.ACTION_BAR_QUESTIONS);

        id = getArguments().getInt("idQuestion");
        title = getArguments().getString("titleQuestion");
        body = getArguments().getString("bodyQuestion");
        url = getArguments().getString("urlQuestion");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_question_fragment,
                container, false);

        ButterKnife.bind(this, view);

        tvTitle=(TextView) view.findViewById(R.id.tvTitle);
        tvBody=(TextView) view.findViewById(R.id.tvBody);
        rvAswers=(RecyclerView) view.findViewById(R.id.rvAswers);
        tvTitle.setText(Html.fromHtml(title));
        tvBody.setText(Html.fromHtml(body));

        rvAswers.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        rvAswers.setLayoutManager(layoutManager);

        adapter = new AnswerAdapter(answerList, getActivity());
        rvAswers.setAdapter(adapter);

        StackOverflowLoader stackOverflowLoader = new StackOverflowLoader();
        call = stackOverflowLoader.loadAnswersFromQuestion(id);
        call.enqueue(this);

        return view;
    }

    @Override
    public void onResponse(Call<Items<Answer>> call, Response<Items<Answer>> response) {
        adapter.setAnswerItems(response.body());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<Items<Answer>> call, Throwable t) {
        Log.d("debug_max", t.getLocalizedMessage());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if(call != null)
            call.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();

        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    ((MainActivity)getActivity()).setFragment(new HomeFragment(), Constants.FRAGMENT_HOME);
                    return true;
                }

                return false;
            }
        });
    }
}
