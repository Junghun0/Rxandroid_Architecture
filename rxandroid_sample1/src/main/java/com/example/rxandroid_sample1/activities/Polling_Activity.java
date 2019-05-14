package com.example.rxandroid_sample1.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rxandroid_sample1.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Polling_Activity extends AppCompatActivity {
    private static final long INITIAL_DELAY = 0L;
    private static final long PERIOD = 3L;

    @BindView(R.id.lv_polling_log)
    ListView mLogView;

    Unbinder mUnbinder;
    // Log
    private LogAdapter mLogAdapter;
    private List<String> mLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polling);
        mUnbinder = ButterKnife.bind(this);
        setupLogger();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @OnClick(R.id.btn_polling)
    void polling() { startPollingV1(); }

    @OnClick(R.id.btn_polling2)
    void polling2() { startPollingV2(); }

    private void startPollingV1() {

        Observable<String> ob = Observable.interval(INITIAL_DELAY, PERIOD, TimeUnit.SECONDS)
                .flatMap(o -> Observable.just("polling #1 " + o.toString()));

        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::log);
    }

    private void startPollingV2() {

        Observable<String> ob2 = Observable.just("polling #2")
                .repeatWhen(o -> o.delay(PERIOD, TimeUnit.SECONDS));

        ob2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::log);
    }

    private void log(String log) {
        mLogs.add(log);
        mLogAdapter.clear();
        mLogAdapter.addAll(mLogs);
    }

    private void setupLogger() {
        mLogs = new ArrayList<>();
        mLogAdapter = new LogAdapter(this, new ArrayList<>());
        mLogView.setAdapter(mLogAdapter);
    }

    private class LogAdapter extends ArrayAdapter<String> {
        public LogAdapter(Context context, List<String> logs) {
            super(context, R.layout.textview_log, R.id.tv_log, logs);
        }
    }
}
