package com.example.mvp_sample2.util;

import com.example.mvp_sample2.model.ResultUrl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitService {

    String URL = "https://openapi.naver.com/v1/util/";

    @Headers({"X-Naver-Client-Id: eaDhg_XFv_WzmBETR68q","X-Naver-Client-Secret: XKI44UthqV"})
    @GET("shorturl.json")
    Call<ResultUrl> sendShortURL(@Query("url") String url);
}
