package com.example.mvp_rxjava.presenter;

import android.content.Context;

import com.example.mvp_rxjava.data.ServerResponse;

import java.util.HashMap;
import java.util.Map;

public interface MainContractor {

    interface View {
        void showResult();

        void showImageURL(ServerResponse serverResponse);

        void setMovieInfo(ServerResponse serverResponse);

        void setTodayDate(String todayDate);

        void getDateInfo();

        void initRecyclerView();

        void getThumbNailMap(Map<String, String> maps);
    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void getMovieInfo(Context context, String key, String todayDate);

        void getMovieThumbNail(Context context, String query, HashMap<String, String> maps);

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
            void onFinished(String thumbNailURL, HashMap<String, String> maps);
            void onFailure(Throwable t);
        }
        void getMovieDetail(GetMovieDetails.OnFinishedListener onFinishedListener, String names, HashMap<String,String> maps);
    }

}
