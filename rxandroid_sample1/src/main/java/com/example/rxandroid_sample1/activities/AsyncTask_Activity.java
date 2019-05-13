package com.example.rxandroid_sample1.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.rxandroid_sample1.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class AsyncTask_Activity extends AppCompatActivity {

    private static final String TAG = "AsyncTask_Activity";
    private MyTask myTask;

    @BindView(R.id.async_test_txtview)
    TextView asynctask_txtview;
    @BindView(R.id.rxandroid_async_txtview)
    TextView rxTextView;

    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        unbinder = ButterKnife.bind(this);

        initAndroidAsync();
        initRxAsync();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void initAndroidAsync(){
        myTask = new MyTask();
        myTask.execute("Hello","async","world");
    }

    class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String s: strings){
                stringBuilder.append(s).append(" ");
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            asynctask_txtview.setText(s);
        }
    }

    private void initRxAsync() {
        Observable.just("Hello", "rx", "world")
                .reduce((x,y) -> x + " " + y)
                .observeOn(AndroidSchedulers.mainThread())
                //.subscribe(getObserver());
                .subscribe(
                        rxTextView::setText,
                        e -> Log.e(TAG, e.getMessage()),
                        () -> Log.i(TAG, "done")
                );

    }


    private MaybeObserver<String> getObserver() {
        return new MaybeObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {}

            @Override
            public void onSuccess(String result) {
                rxTextView.setText(result);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "done");
            }
        };
    }
}
