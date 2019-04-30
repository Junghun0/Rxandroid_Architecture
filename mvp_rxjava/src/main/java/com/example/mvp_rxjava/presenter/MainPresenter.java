package com.example.mvp_rxjava.presenter;

import android.content.Context;
import android.util.Log;

import com.example.mvp_rxjava.data.ServerResponse;

public class MainPresenter implements MainContractor.Presenter, MainContractor.GetServerResponse.OnFinishedListener{

    private MainContractor.View view;
    private MainContractor.GetServerResponse getNoticeIntractor;

    public MainPresenter(MainContractor.View view, MainContractor.GetServerResponse getNoticeIntractor) {
        this.view = view;
        this.getNoticeIntractor = getNoticeIntractor;
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
    public void getMovieInfo(Context context, String key) {
        getNoticeIntractor.getMovieInfo(this,"f8528e508b93d59e755310d63eb0455a");
    }


    @Override
    public void onFinished(ServerResponse serverResponse) {
        view.setMovieInfo(serverResponse);
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("network Fail",""+t.toString());
    }
}
