package com.example.rxandroid_sample1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.rxandroid_sample1.activities.HelloActivityV1;
import com.example.rxandroid_sample1.activities.Loop_Activity;
import com.example.rxandroid_sample1.activities.Onclick_Activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.sample_1_btn)
    Button sample_1_btn;
    @BindView(R.id.loop_activity_btn)
    Button loop_activity_btn;
    @BindView(R.id.onclick_activity_btn)
    Button onclick_activity_btn;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);

    }

    @OnClick(R.id.sample_1_btn)
    public void goToSample_1(){
        Intent intent = new Intent(getApplicationContext(), HelloActivityV1.class);
        startActivity(intent);
    }

    @OnClick(R.id.loop_activity_btn)
    public void goToRoopActivity(){
        Intent intent = new Intent(getApplicationContext(), Loop_Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.onclick_activity_btn)
    public void goToOnclickActivity(){
        Intent intent = new Intent(getApplicationContext(), Onclick_Activity.class);
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
