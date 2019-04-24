package com.example.mvp_sample2.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mvp_sample2.R;
import com.example.mvp_sample2.presenter.GetNoticeImpl;
import com.example.mvp_sample2.presenter.MainContractor;
import com.example.mvp_sample2.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainContractor.View {

    private MainPresenter mainPresenter;
    private Retrofit retrofit;

    @BindView(R.id.send_server_btn)
    Button send_server_btn;
    @BindView(R.id.send_url_txtview)
    TextView send_url_txtview;
    @BindView(R.id.result_linearlayout)
    LinearLayout result_linearlayout;
    @BindView(R.id.result_url_txtview)
    TextView result_url_txtview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this, new GetNoticeImpl());
        mainPresenter.attachView(this);
    }

    @OnClick(R.id.send_server_btn)
    public void sendURLOnClick() {
        mainPresenter.loadURL(this,send_url_txtview.getText().toString());
//        String sendURL = send_url_txtview.getText().toString();
//
//        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
//        retrofitService.sendShortURL(sendURL).enqueue(new Callback<ResultUrl>() {
//            @Override
//            public void onResponse(Call<ResultUrl> call, Response<ResultUrl> response) {
//                if (response.isSuccessful()){
//                    if (response.body() != null){
//                        showResult();
//                        setResultURL(response.body().getResult().getUrl());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResultUrl> call, Throwable t) {
//                Log.e("shotURL Failure",""+t.toString());
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void showResult() {
        result_linearlayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setResultURL(String url) {
        result_url_txtview.setText(url);
    }
}
