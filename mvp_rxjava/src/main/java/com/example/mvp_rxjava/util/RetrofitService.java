package com.example.mvp_rxjava.util;

import com.example.mvp_rxjava.data.MovieData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitService {
    @Headers({"X-Naver-Client-Id: eaDhg_XFv_WzmBETR68q", "X-Naver-Client-Secret: XKI44UthqV"})
    @GET("shorturl.json")
    Call<MovieData> sendShortURL(@Query("url") String url);
}
