package com.example.rxandroid_sample1.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.rxandroid_sample1.R;
import com.example.rxandroid_sample1.util.LocalVolley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Volley_Sample_Activity extends AppCompatActivity {

    public static final String URL = "http://time.jsontest.com/";
    @BindView(R.id.vf_lv_log)
    ListView mLogView;
    private Unbinder mUnbinder;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private LogAdapter mLogAdapter;
    private List<String> mLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley__sample);
        /* volley 사용법
        1. RequestQueue 생성.
        2. Request Object 생성.
        3. Reqeust Object를 RequestQueue 에 추가.
        4. 설정한 Callback으로 응답수신.
        */

        //localvolley 초기화하기
        LocalVolley.init(getApplicationContext());
        mUnbinder = ButterKnife.bind(this);
        setupLogger();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mCompositeDisposable.clear();
    }

    @OnClick(R.id.vf_btn_get)
    void getTime() {
        post(getObservable());
    }

    @OnClick(R.id.vf_btn_get2)
    void getTimeCallable() {
        post(getObservableFromCallable());
    }

    @OnClick(R.id.vf_btn_get3)
    void getTimeFuture() {
        post(getObservableFromFuture());
    }

    private void post(Observable<JSONObject> observable) {
        DisposableObserver<JSONObject> observer = getObserver();

        mCompositeDisposable.add(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer)
        );
    }

    // lambda expression
    private Observable<JSONObject> getObservable() {
        return Observable.defer(() -> {
            try {
                return Observable.just(getData());
            } catch (InterruptedException e) {
                log("error : " + e.getMessage());
                return Observable.error(e);
            } catch (ExecutionException e) {
                log("error : " + e.getCause());
                return Observable.error(e.getCause());
            }
        });
    }

    /**
     * public static <T> Observable<T> fromCallable(Callable<? extends T> supplier)
     * defer + just 과 같은 효과를 제공.
     * <p>
     * Returns an Observable that, when an observer subscribes to it,
     * invokes a function you specify and then emits the value returned from that function.
     * This allows you to defer the execution of the function you specify until an observer subscribes to the ObservableSource.
     * That is to say, it makes the function "lazy."
     *
     * @return
     */
    private Observable<JSONObject> getObservableFromCallable() {
        return Observable.fromCallable(this::getData);
    }

    /**
     * Converts a Future into an ObservableSource.
     * <p>
     * You can convert any object that supports the Future interface into an ObservableSource
     * that emits the return value of the Future.get() method of that object,
     * by passing the object into the from method.
     * <p>
     * Important note: This ObservableSource is blocking; you cannot dispose it.
     */
    private Observable<JSONObject> getObservableFromFuture() {
        return Observable.fromFuture(getFuture());
    }

    private JSONObject getData() throws ExecutionException, InterruptedException {
        return getFuture().get();
    }

    /**
     * Converts the Asynchronous Request into a Synchronous Future that can be used to block via
     * {@code Future.get()}. Observables require blocking/synchronous functions
     * 2. Request Object생성 3.RequestQueue에 추가 4. Callback 등록
     */
    private RequestFuture<JSONObject> getFuture() {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest req = new JsonObjectRequest(URL, null, future, future);
        LocalVolley.getRequestQueue().add(req);
        return future;
    }

    private DisposableObserver<JSONObject> getObserver() {
        return new DisposableObserver<JSONObject>() {
            @Override
            public void onNext(JSONObject jsonObject) {
                log(" >> " + jsonObject.toString());
            }

            @Override
            public void onError(Throwable t) {
                log(t.toString());
            }

            @Override
            public void onComplete() {
                log("complete");
            }
        };
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
