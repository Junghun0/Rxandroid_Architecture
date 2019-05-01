package com.example.mvp_rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.mvp_rxjava.adapter.MovieRecyclerAdapter;
import com.example.mvp_rxjava.data.MovieDetail;
import com.example.mvp_rxjava.data.ServerResponse;
import com.example.mvp_rxjava.presenter.GetServerResponseImpl;
import com.example.mvp_rxjava.presenter.MainContractor;
import com.example.mvp_rxjava.presenter.MainPresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContractor.View {
    //key f8528e508b93d59e755310d63eb0455a

    private MainPresenter mainPresenter;
    private MovieRecyclerAdapter adapter;
    private final String key = "f8528e508b93d59e755310d63eb0455a";
    private SimpleDateFormat todayFormat;
    private String targetDt;

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

        initRecyclerView();
        getDateInfo();
        showResult();
    }


    @Override
    public void initRecyclerView() {
        adapter = new MovieRecyclerAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        movie_recyclerView.setLayoutManager(linearLayoutManager);
        movie_recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showResult() {
        mainPresenter.getMovieInfo(this, key, targetDt);
    }

    @Override
    public void setMovieInfo(ServerResponse serverResponse) {
        adapter.setItems(serverResponse.getBoxOfficeResult().getDailyBoxOfficeList());
    }

    @Override
    public void setMovieDetails(MovieDetail movieDetails) {

    }

    @Override
    public void setTodayDate(String todayDate) {
        date_textView.setText(todayDate + R.string.boxoffice);
    }

    @Override
    public void getDateInfo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        todayFormat = new SimpleDateFormat("yyyyMMdd");

        Calendar calendar = new GregorianCalendar(Locale.KOREA);
        calendar.add(Calendar.DATE, -1);
        String todayDate = dateFormat.format(calendar.getTime());
        targetDt = todayFormat.format(calendar.getTime());
        setTodayDate(todayDate);
    }


}


