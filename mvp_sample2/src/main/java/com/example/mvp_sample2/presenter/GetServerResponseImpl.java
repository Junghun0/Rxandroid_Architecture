package com.example.mvp_sample2.presenter;

import android.util.Log;

import com.example.mvp_sample2.model.ResultUrl;
import com.example.mvp_sample2.util.RetrofitInstance;
import com.example.mvp_sample2.util.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetServerResponseImpl implements MainContractor.GetServerResponse {

    @Override
    public void getNoticeURL(final OnFinishedListener onFinishedListener, String url) {

        RetrofitService retrofit = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);

        retrofit.sendShortURL(url).enqueue(new Callback<ResultUrl>() {
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
