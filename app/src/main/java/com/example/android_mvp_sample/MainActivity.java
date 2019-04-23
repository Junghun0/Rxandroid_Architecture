package com.example.android_mvp_sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android_mvp_sample.presenter.MainContract;
import com.example.android_mvp_sample.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }
}
