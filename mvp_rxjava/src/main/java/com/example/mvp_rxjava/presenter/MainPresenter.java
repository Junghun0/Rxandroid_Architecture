package com.example.mvp_rxjava.presenter;

import android.content.Context;
import android.util.Log;

import com.example.mvp_rxjava.data.MovieDetail;
import com.example.mvp_rxjava.data.ServerResponse;

import java.util.List;

public class MainPresenter implements MainContractor.Presenter, MainContractor.GetServerResponse.OnFinishedListener, MainContractor.GetMovieDetails.OnFinishedListener {

    private MainContractor.View view;
    private MainContractor.GetServerResponse getNoticeIntractor;
    private MainContractor.GetMovieDetails getMovieDetails;

    public MainPresenter(MainContractor.View view, MainContractor.GetServerResponse getNoticeIntractor, MainContractor.GetMovieDetails getMovieDetails) {
        this.view = view;
        this.getNoticeIntractor = getNoticeIntractor;
        this.getMovieDetails = getMovieDetails;
    }

    @Override
    public void attachView(MainContractor.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void getMovieInfo(Context context, String key, String todayDate) {
        getNoticeIntractor.getMovieInfo(this, "f8528e508b93d59e755310d63eb0455a", todayDate);
    }

    @Override
    public void getMovieThumbNail(Context context, String query) {
        getMovieDetails.getMovieDetail(this, query);
    }

    @Override
    public void onFinished(ServerResponse serverResponse) {
        view.setMovieInfo(serverResponse);
    }

    @Override
    public void onFinished(MovieDetail movieDetail) {
        view.setMovieDetails(movieDetail);
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("network Fail", "" + t.toString());
    }

    @Override
    public void sendMovieNames(List<String> movieNames) {
        if (movieNames.size() == 10){
            for (int i = 0; i < movieNames.size(); i++) {
                Log.e("at mainpresenter",""+movieNames.get(i));
            }
        }

    }
}
