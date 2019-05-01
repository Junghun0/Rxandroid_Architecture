package com.example.mvp_rxjava.presenter;

import com.example.mvp_rxjava.data.ServerResponse;
import com.example.mvp_rxjava.util.RetrofitInstance;
import com.example.mvp_rxjava.util.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetServerResponseImpl implements MainContractor.GetServerResponse {
    private static final String targetDt = "20190429";
    ServerResponse boxOfficeResult;

    @Override
    public void getMovieInfo(final OnFinishedListener onFinishedListener, String key, String targetDt) {
        RetrofitService retrofit = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);

        retrofit.getMovieInfo("f8528e508b93d59e755310d63eb0455a", targetDt).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        onFinishedListener.onFinished(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
