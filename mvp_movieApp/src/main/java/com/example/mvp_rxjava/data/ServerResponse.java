package com.example.mvp_rxjava.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerResponse {

    @SerializedName("boxOfficeResult")
    @Expose
    private BoxOfficeResult boxOfficeResult;

    public BoxOfficeResult getBoxOfficeResult() {
        return boxOfficeResult;
    }

    public void setBoxOfficeResult(BoxOfficeResult boxOfficeResult) {
        this.boxOfficeResult = boxOfficeResult;
    }
}
