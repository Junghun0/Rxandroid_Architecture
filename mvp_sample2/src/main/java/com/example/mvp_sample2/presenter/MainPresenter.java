package com.example.mvp_sample2.presenter;

import android.content.Context;
import android.util.Log;

public class MainPresenter implements MainContractor.Presenter, MainContractor.GetServerResponse.OnFinishedListener {

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
    public void loadURL(Context context, String url) {
        getNoticeIntractor.getNoticeURL(this, url);
    }

    @Override
    public void onFinished(String resultURL) {
        view.setResultURL(resultURL);
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("network onFailure", "" + t.toString());
    }
}

