package com.example.mvp_rxjava.util;

import com.example.mvp_rxjava.data.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    //key=f8528e508b93d59e755310d63eb0455a
    //targetDt = &targetDt=20190427
    @GET("searchDailyBoxOfficeList.json")
    Call<ServerResponse> getMovieInfo(@Query("key") String key, @Query("targetDt") String targetDt);
}
