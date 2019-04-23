package com.example.android_mvp_sample.presenter;

import android.content.Context;

import com.example.android_mvp_sample.model.ImageItem;
import com.example.android_mvp_sample.model.SampleImageData;

import java.util.ArrayList;

public interface MainContract {

    interface View {

        void addItems(ArrayList<ImageItem> items, boolean isClear);

        void notifyAdapter();
    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void setSampleImageData(SampleImageData sampleImageData);

        void loadItems(Context context, boolean isClear);
    }

}
