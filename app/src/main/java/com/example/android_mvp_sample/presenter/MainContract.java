package com.example.android_mvp_sample.presenter;

public interface MainContract {

    interface View{

    }

    interface Presenter{
        void attachView(View view);
        void detachView();
    }
}
