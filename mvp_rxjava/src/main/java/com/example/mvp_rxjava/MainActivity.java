package com.example.mvp_rxjava;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mvp_rxjava.adapter.MovieRecyclerAdapter;
import com.example.mvp_rxjava.data.ServerResponse;
import com.example.mvp_rxjava.presenter.GetMovieDetailsImpl;
import com.example.mvp_rxjava.presenter.GetServerResponseImpl;
import com.example.mvp_rxjava.presenter.MainContractor;
import com.example.mvp_rxjava.presenter.MainPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContractor.View {
    //key f8528e508b93d59e755310d63eb0455a

    private MainPresenter mainPresenter;
    private MovieRecyclerAdapter adapter;
    private final String key = "f8528e508b93d59e755310d63eb0455a";
    private SimpleDateFormat todayFormat;
    private String targetDt;
    private List<String> movieNames;
    private List<String> thumbNailList;
    private int count = 0;

    @BindView(R.id.movie_recyclerView)
    RecyclerView movie_recyclerView;
    @BindView(R.id.date_textView)
    TextView date_textView;

    private HashMap<String, String> thumbNailsMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        movieNames = new ArrayList<>();
        thumbNailList = new ArrayList<>();

        mainPresenter = new MainPresenter(this, new GetServerResponseImpl(), new GetMovieDetailsImpl());
        mainPresenter.attachView(this);

        initRecyclerView();
        getDateInfo();
        showResult();
    }

    @Override
    public void initRecyclerView() {
        adapter = new MovieRecyclerAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        movie_recyclerView.setLayoutManager(linearLayoutManager);
        movie_recyclerView.setAdapter(adapter);
    }

    @Override
    public void getThumbNailMap(Map<String, String> maps) {
        count++;
        if (count == 10) {
            for (int i = 0 ; i < 10 ; i++){
                thumbNailList.add(maps.get(movieNames.get(i)));
                if (thumbNailList.size() == 10) {
                    adapter.setItemThumbnail(thumbNailList);
                    break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        mainPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showResult() {
        mainPresenter.getMovieInfo(this, key, targetDt);
    }

    @Override
    public void showImageURL(ServerResponse serverResponse) {
        for (int i = 0 ; i<serverResponse.getBoxOfficeResult().getDailyBoxOfficeList().size(); i++){
            thumbNailsMap.put(serverResponse.getBoxOfficeResult().getDailyBoxOfficeList().get(i).getMovieNm(),"");
            movieNames.add(serverResponse.getBoxOfficeResult().getDailyBoxOfficeList().get(i).getMovieNm());
            mainPresenter.getMovieThumbNail(this, serverResponse.getBoxOfficeResult().getDailyBoxOfficeList().get(i).getMovieNm(),thumbNailsMap);
        }
    }

    @Override
    public void setMovieInfo(ServerResponse serverResponse) {
        adapter.setItems(serverResponse.getBoxOfficeResult().getDailyBoxOfficeList());
        showImageURL(serverResponse);
    }

    @Override
    public void setTodayDate(String todayDate) {
        date_textView.setText(todayDate + " 박스오피스 순위");
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


