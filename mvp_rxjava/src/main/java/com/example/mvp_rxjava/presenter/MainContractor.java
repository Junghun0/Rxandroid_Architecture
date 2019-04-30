package com.example.mvp_rxjava.presenter;

import android.content.Context;

import com.example.mvp_rxjava.data.ServerResponse;

public interface MainContractor {

    interface View {
        void showResult();

        void setMovieInfo(ServerResponse serverResponse);
    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void getMovieInfo(Context context, String key);

    }

    interface GetServerResponse {

        interface OnFinishedListener {
            void onFinished(ServerResponse serverResponse);
            void onFailure(Throwable t);
        }

        void getMovieInfo(OnFinishedListener onFinishedListener, String key);
    }

}
