package com.example.rxandroid_sample1.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rxandroid_sample1.R;
import com.example.rxandroid_sample1.model.Contributor;
import com.example.rxandroid_sample1.util.GitHubServiceApi;
import com.example.rxandroid_sample1.util.RestfulAdapter;

import java.util.ArrayList;
import java.util.List;

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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Retrofit_Sample_Activity extends AppCompatActivity {

    private static final String sName = "jungjoonpark-pandora";
    private static final String sRepo = "rxAndroid";

    @BindView(R.id.ohf_lv_log)
    ListView mLogView;

    private Unbinder mUnbinder;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit__sample);
        mUnbinder = ButterKnife.bind(this);
        setupLogger();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
        mCompositeDisposable.clear();
    }

    @OnClick(R.id.ohf_btn_retrofit)
    void getRetrofit() {
        startRetrofit();
    }

    @OnClick(R.id.ohf_btn_get_retrofit_okhttp)
    void getOkHttp() {
        startOkHttp();
    }

    @OnClick(R.id.ohf_btn_get_retrofit_okhttp_rx)
    void getRx() {
        startRx();
    }


    /**
     * retrofit + okHttp( Call의 내부 )
     */
    private void startRetrofit() {
        GitHubServiceApi service = RestfulAdapter.getInstance().getSimpleApi();
        Call<List<Contributor>> call = service.getCallContributors(sName, sRepo);
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                if (response.isSuccessful()) {
                    List<Contributor> contributors = response.body();
                    for (Contributor c : contributors) {
                        log(c.toString());
                    }
                } else {
                    log("not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                log(t.getMessage());
            }
        });
    }


    /**
     * retrofit + okHttp
     */
    private void startOkHttp() {
        GitHubServiceApi service = RestfulAdapter.getInstance().getServiceApi();
        Call<List<Contributor>> call = service.getCallContributors(sName, sRepo);

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                if (response.isSuccessful()) {
                    List<Contributor> contributors = response.body();
                    for (Contributor c : contributors) {
                        log(c.toString());
                    }
                } else {
                    log("not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                log(t.getMessage());
            }
        });
    }


    /**
     * retrofit + okHttp + rxJava
     */
    private void startRx() {
        GitHubServiceApi service = RestfulAdapter.getInstance().getServiceApi();
        Observable<List<Contributor>> observable = service.getObContributors(sName, sRepo);

        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Contributor>>() {
                    @Override
                    public void onNext(List<Contributor> contributors) {
                        for (Contributor c : contributors) {
                            log(c.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        log(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        log("complete");
                    }
                })


        );
    }


    // Log
    private LogAdapter mLogAdapter;
    private List<String> mLogs;

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
