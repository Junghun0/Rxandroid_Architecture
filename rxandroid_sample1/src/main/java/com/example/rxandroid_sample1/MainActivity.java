package com.example.rxandroid_sample1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.rxandroid_sample1.activities.AsyncTask_Activity;
import com.example.rxandroid_sample1.activities.HelloActivityV1;
import com.example.rxandroid_sample1.activities.Loop_Activity;
import com.example.rxandroid_sample1.activities.Onclick_Activity;
import com.example.rxandroid_sample1.activities.Polling_Activity;
import com.example.rxandroid_sample1.activities.RecyclerView_sample_Activity;
import com.example.rxandroid_sample1.activities.Search_Activity;
import com.example.rxandroid_sample1.activities.Timer_Sample_Activity;

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
    @BindView(R.id.onclick_search_btn)
    Button onclick_search_btn;
    @BindView(R.id.onclick_recycler_btn)
    Button onclick_recycler_btn;
    @BindView(R.id.timer_activity_btn)
    Button timer_activity_btn;
    @BindView(R.id.polling_activity_btn)
    Button polling_activity_btn;


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

    @OnClick(R.id.onclick_search_btn)
    public void goToSearchActivity(){
        Intent intent = new Intent(getApplicationContext(), Search_Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.onclick_recycler_btn)
    public void goToRecyclerViewActivity(){
        Intent intent = new Intent(getApplicationContext(), RecyclerView_sample_Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.asynctask_activity_btn)
    public void goToAsyncTaskActivity(){
        Intent intent = new Intent(getApplicationContext(), AsyncTask_Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.timer_activity_btn)
    public void goToTimerActivity(){
        Intent intent = new Intent(getApplicationContext(), Timer_Sample_Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.polling_activity_btn)
    public void goToPollingActivity(){
        Intent intent = new Intent(getApplicationContext(), Polling_Activity.class);
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
