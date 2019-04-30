package com.example.mvp_rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.mvp_rxjava.adapter.MovieRecyclerAdapter;
import com.example.mvp_rxjava.data.ServerResponse;
import com.example.mvp_rxjava.presenter.GetServerResponseImpl;
import com.example.mvp_rxjava.presenter.MainContractor;
import com.example.mvp_rxjava.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContractor.View {
    //key f8528e508b93d59e755310d63eb0455a

    private MainPresenter mainPresenter;
    private MovieRecyclerAdapter adapter;
    private final String key = "f8528e508b93d59e755310d63eb0455a";

    @BindView(R.id.movie_recyclerView)
    RecyclerView movie_recyclerView;
    @BindView(R.id.date_textView)
    TextView date_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this, new GetServerResponseImpl());
        mainPresenter.attachView(this);

        adapter = new MovieRecyclerAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        movie_recyclerView.setLayoutManager(linearLayoutManager);
        movie_recyclerView.setAdapter(adapter);

        showResult();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showResult() {
        mainPresenter.getMovieInfo(this, key);
    }

    @Override
    public void setMovieInfo(ServerResponse serverResponse) {
        adapter.setItems(serverResponse.getBoxOfficeResult().getDailyBoxOfficeList());
    }


}


