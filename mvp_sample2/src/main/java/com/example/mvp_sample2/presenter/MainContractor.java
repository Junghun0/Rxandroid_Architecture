package com.example.mvp_sample2.presenter;

import android.content.Context;

public interface MainContractor {
    interface View {
        void notifyAdapter();
    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void loadItems(Context context, boolean isClear);
    }
}
