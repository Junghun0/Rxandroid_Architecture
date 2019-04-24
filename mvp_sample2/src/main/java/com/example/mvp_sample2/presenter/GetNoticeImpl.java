package com.example.mvp_sample2.presenter;

import android.util.Log;

import com.example.mvp_sample2.model.ResultUrl;
import com.example.mvp_sample2.util.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetNoticeImpl implements MainContractor.GetNoticeIntractor {

    @Override
    public void getNoticeURL(final OnFinishedListener onFinishedListener, String url) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.sendShortURL(url).enqueue(new Callback<ResultUrl>() {
            @Override
            public void onResponse(Call<ResultUrl> call, Response<ResultUrl> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        onFinishedListener.onFinished(response.body().getResult().getUrl());
                        Log.e("result url",""+response.body().getResult().getUrl());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultUrl> call, Throwable t) {
                onFinishedListener.onFailure(t);
                Log.e("shotURL Failure",""+t.toString());
            }
        });

    }
}
