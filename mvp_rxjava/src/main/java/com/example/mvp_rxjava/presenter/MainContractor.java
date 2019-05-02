package com.example.mvp_rxjava.presenter;

import android.content.Context;

import com.example.mvp_rxjava.data.MovieDetail;
import com.example.mvp_rxjava.data.ServerResponse;

public interface MainContractor {

    interface View {
        void showResult();

        void showImageURL(ServerResponse serverResponse);

        void setMovieInfo(ServerResponse serverResponse);

        void setMovieDetails(MovieDetail movieDetails);

        void setTodayDate(String todayDate);

        void getDateInfo();

        void initRecyclerView();
    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void getMovieInfo(Context context, String key, String todayDate);

        void getMovieThumnail(Context context, String query);

    }

    interface GetServerResponse {

        interface OnFinishedListener {
            void onFinished(ServerResponse serverResponse);
            void onFailure(Throwable t);
        }
        void getMovieInfo(OnFinishedListener onFinishedListener, String key, String targetDate);
    }

    interface GetMovieDetails{

        interface OnFinishedListener{
            void onFinished(MovieDetail movieDetail);
            void onFailure(Throwable t);
        }
        void getMovieDetail(GetMovieDetails.OnFinishedListener onFinishedListener, String names);
    }

}
