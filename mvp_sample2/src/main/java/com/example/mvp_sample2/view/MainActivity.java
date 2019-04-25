package com.example.mvp_sample2.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mvp_sample2.R;
import com.example.mvp_sample2.presenter.GetServerResponseImpl;
import com.example.mvp_sample2.presenter.MainContractor;
import com.example.mvp_sample2.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContractor.View {

    private MainPresenter mainPresenter;

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
        mainPresenter = new MainPresenter(this, new GetServerResponseImpl());
        mainPresenter.attachView(this);
    }

    @OnClick(R.id.send_server_btn)
    public void sendURLOnClick() {
        mainPresenter.loadURL(this, send_url_txtview.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void setResultURL(String url) {
        if (url != null){
            result_url_txtview.setText(url);
            showResult();
        }
    }

    @Override
    public void showResult() {
        result_linearlayout.setVisibility(View.VISIBLE);
    }
}
