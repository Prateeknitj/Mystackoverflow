package com.example.prateek.mystackoverflow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.prateek.mystackoverflow.models.Items;
import com.example.prateek.mystackoverflow.models.Question;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements Callback<Items<Question>> {

    RecyclerView rvHome;
    HomeAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Items<Question> questionList = new Items<>();


    Call<Items<Question>> callWOFilter, call;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.home_fragment,
                container, false);

        ButterKnife.bind(this, view);
        rvHome=(RecyclerView) view.findViewById(R.id.rvHome);
        rvHome.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        rvHome.setLayoutManager(layoutManager);

        adapter = new HomeAdapter(questionList, getActivity());
        rvHome.setAdapter(adapter);

        StackOverflowLoader stackOverflowLoader = new StackOverflowLoader();
        callWOFilter = stackOverflowLoader.loadAllQuestions("votes");
        callWOFilter.enqueue(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        callWOFilter.cancel();
        if(call != null)
            call.cancel();
    }

    @Override
    public void onResponse(Call<Items<Question>> call, Response<Items<Question>> response) {
        adapter.clearQuestionList();
        adapter.setQuestionList(response.body());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<Items<Question>> call, Throwable t) {
        Log.d("debug_max", t.getLocalizedMessage());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();

        inflater.inflate(R.menu.toolbar_menu, menu);

    }
}
