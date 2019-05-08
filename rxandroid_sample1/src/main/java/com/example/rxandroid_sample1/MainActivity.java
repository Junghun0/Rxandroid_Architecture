package com.example.rxandroid_sample1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.rxandroid_sample1.activities.HelloActivityV1;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.sample_1_btn)
    Button sample_1_btn;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.sample_1_btn)
    public void goToSample_1(){
        Intent intent = new Intent(getApplicationContext(), HelloActivityV1.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
