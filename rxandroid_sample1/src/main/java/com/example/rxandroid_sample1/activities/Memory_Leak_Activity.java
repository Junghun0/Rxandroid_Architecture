package com.example.rxandroid_sample1.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.rxandroid_sample1.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class Memory_Leak_Activity extends RxAppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;

    private Disposable mDisposable;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory__leak);


        mUnbinder = ButterKnife.bind(this);

        DisposableObserver<String> observer = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() { }
        };

        mDisposable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello world!");
                e.onComplete();
            }
        }).subscribeWith(observer);

        /*RxLifecycle 을 이용한 해결책
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext("Hello World");
                emitter.onComplete();
            }
        })
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(textView::setText);*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
