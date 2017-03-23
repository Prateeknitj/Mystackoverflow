package com.example.prateek.mystackoverflow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.example.prateek.mystackoverflow.models.Items;
import com.example.prateek.mystackoverflow.models.Question;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment implements Callback<Items<Question>> {

    RecyclerView rvSearch;

    HomeAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Items<Question> questionList = new Items<>();

    EditText etSearch;
    Call<Items<Question>> call;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment,
                container, false);

        ButterKnife.bind(this, view);

        etSearch=(EditText) view.findViewById(R.id.etSearch);
        rvSearch=(RecyclerView) view.findViewById(R.id.rvSearch);
        rvSearch.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        rvSearch.setLayoutManager(layoutManager);

        adapter = new HomeAdapter(questionList, getActivity());
        rvSearch.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etSearch.getText().toString().length() > 2) {

                    StackOverflowLoader stackOverflowLoader = new StackOverflowLoader();
                    call = stackOverflowLoader.loadSearchQuestions(etSearch.getText().toString());
                    call.enqueue(SearchFragment.this);
                } else {
                    adapter.clearQuestionList();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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

}
