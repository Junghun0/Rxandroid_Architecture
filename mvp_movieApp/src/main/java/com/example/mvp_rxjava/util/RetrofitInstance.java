package com.example.mvp_rxjava.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static Retrofit moiverRetrofit;
    private static final String BASE_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/";
    private static final String BASE_URL_MOVIE = "https://openapi.naver.com/v1/search/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofitInstance2(){
        if (moiverRetrofit == null) {
            moiverRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL_MOVIE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return moiverRetrofit;
    }
}
