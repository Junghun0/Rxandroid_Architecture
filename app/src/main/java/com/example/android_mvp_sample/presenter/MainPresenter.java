package com.example.android_mvp_sample.presenter;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
}
