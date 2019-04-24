package com.example.mvp_sample2.presenter;

import android.content.Context;

public class MainPresenter implements MainContractor.Presenter{

    private MainContractor.View view;

    @Override
    public void attachView(MainContractor.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {view = null;}

    @Override
    public void loadItems(Context context, boolean isClear) {

    }
}
