package com.example.mvp_sample2.presenter;

import android.content.Context;

public class MainPresenter implements MainContractor.Presenter, MainContractor.GetNoticeIntractor.OnFinishedListener{

    private MainContractor.View view;
    private MainContractor.GetNoticeIntractor getNoticeIntractor;

    public MainPresenter(MainContractor.View view, MainContractor.GetNoticeIntractor getNoticeIntractor) {
        this.view = view;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void attachView(MainContractor.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {view = null;}

    @Override
    public void loadURL(Context context, String url) {
    }

    @Override
    public void onFinished(String resultURL) {
        getNoticeIntractor.getNoticeURL(this,resultURL);
        view.setResultURL(resultURL);
    }

    @Override
    public void onFailure(Throwable t) {
        view.showResult();
    }
}
