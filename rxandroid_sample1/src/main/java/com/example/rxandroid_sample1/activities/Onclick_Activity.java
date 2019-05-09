package com.example.rxandroid_sample1.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.rxandroid_sample1.R;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.DisposableObserver;

public class Onclick_Activity extends AppCompatActivity {
    public static final String TAG = Onclick_Activity.class.getSimpleName();
    private static final int SEVEN = 7;
    @BindView(R.id.lv_log)
    ListView mLogView;
    @BindView(R.id.btn_click_observer)
    Button mButton;
    @BindView(R.id.btn_click_observer_lambda)
    Button mButtonLambda;
    @BindView(R.id.btn_click_observer_binding)
    Button mButtonBinding;
    @BindView(R.id.btn_click_observer_extra)
    Button mButtonExtra;
    private Unbinder mUnbinder;
    private LogAdapter mLogAdapter;
    private List<String> mLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclick_);
        mUnbinder = ButterKnife.bind(this);
        setupLogger();

//        getClickEventObservable()
//                .map(s -> "clicked")
//                .subscribe(getObserver());

//        getClickEventObservableWithLambda()
//                .map(s -> "clicked lambda")
//                .subscribe(this::log);

//        getClickEventObservableWithRxBinding()
//                .subscribe(this::log);

//        getClickEventObservableExtra()
//                .map(local -> SEVEN)
//                .flatMap(this::compareNumbers)
//                .subscribe(this::log);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @OnClick(R.id.btn_click_observer)
    void normalBtnOnclick() {
        log("clicked");
    }

    @OnClick(R.id.btn_click_observer_lambda)
    void lambdaBtnOnClick() {
        log("clicked lambda");
    }

    @OnClick(R.id.btn_click_observer_binding)
    void bindingBtnOnClick() {
        log("Clicked Rxbinding");
    }

    @OnClick(R.id.btn_click_observer_extra)
    void extraBtnOnclick() {
        Random random = new Random();
        int data = random.nextInt(10);

        log("local : " + String.valueOf(data) + "remote : " + String.valueOf(data) + "result = " + data);
    }

    private Observable<View> getClickEventObservable() {
        return Observable.create(new ObservableOnSubscribe<View>() {
            @Override
            public void subscribe(ObservableEmitter<View> e) throws Exception {
                //Normal button
                mButton.setOnClickListener(e::onNext);
            }
        });
    }

    private Observable<View> getClickEventObservableWithLambda() {
        //Lambda button
        return Observable.create(s -> mButtonLambda.setOnClickListener(s::onNext));
    }

    private Observable<String> getClickEventObservableWithRxBinding() {
        //rxbinding
        return RxView.clicks(mButtonBinding)
                .map(s -> "Clicked Rxbinding");
    }

    private Observable<View> getClickEventObservableExtra() {
        //random 7
        return Observable.create(s -> mButtonExtra.setOnClickListener(s::onNext));
    }


    private Observable<String> compareNumbers(int input) {
        return Observable.just(input)
                .flatMap(in -> {
                    Random random = new Random();
                    int data = random.nextInt(10);
                    return Observable.just("local : " + in, "remote : " + data, "result = " + (in == data));
                });
    }


    private DisposableObserver<? super String> getObserver() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                log(s);
            }

            @Override
            public void onError(Throwable e) {
                log(e.getMessage());
            }

            @Override
            public void onComplete() {
                log("complete");
            }
        };
    }

    //getClickEventObservable().subscribe();
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
