package com.example.mvp_sample2.presenter;

import android.content.Context;

public interface MainContractor {
    interface View {
        void showResult();

        void setResultURL(String url);

    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void loadURL(Context context, String url);
    }

    interface GetServerResponse {

        interface OnFinishedListener {
            void onFinished(String resultURL);
            void onFailure(Throwable t);
        }

        void getNoticeURL(OnFinishedListener onFinishedListener, String url);
    }
}
