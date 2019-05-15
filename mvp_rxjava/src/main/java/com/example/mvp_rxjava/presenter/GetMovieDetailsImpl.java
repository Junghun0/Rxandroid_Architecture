package com.example.mvp_rxjava.presenter;

import android.util.Log;

import com.example.mvp_rxjava.data.MovieDetail;
import com.example.mvp_rxjava.util.RetrofitInstance;
import com.example.mvp_rxjava.util.RetrofitService;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMovieDetailsImpl implements MainContractor.GetMovieDetails {
    @Override
    public void getMovieDetail(final OnFinishedListener onFinishedListener, String names, HashMap<String, String> maps) {
        RetrofitService retrofit = RetrofitInstance.getRetrofitInstance2().create(RetrofitService.class);
        retrofit.sendShortURL(names).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        maps.put(names, response.body().getItems().get(0).getImage());
                        onFinishedListener.onFinished(response.body().getItems().get(0).getImage(), maps);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                onFinishedListener.onFailure(t);
                Log.e("name search fail", "" + t.toString());
            }
        });
    }
}
