package com.example.android_mvp_sample.presenter;

import android.content.Context;

import com.example.android_mvp_sample.model.ImageItem;
import com.example.android_mvp_sample.model.SampleImageData;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    private SampleImageData sampleImageData;

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void setSampleImageData(SampleImageData sampleImageData) {
        this.sampleImageData = sampleImageData;
    }

    @Override
    public void loadItems(Context context, boolean isClear) {
        ArrayList<ImageItem> items = sampleImageData.getImages(context, 10);
        view.addItems(items, isClear);
        view.notifyAdapter();
    }
}
