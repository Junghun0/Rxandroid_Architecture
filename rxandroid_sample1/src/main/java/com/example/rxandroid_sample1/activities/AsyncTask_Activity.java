package com.example.rxandroid_sample1.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.example.rxandroid_sample1.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AsyncTask_Activity extends AppCompatActivity {
    private MyTask myTask;

    @BindView(R.id.async_test_txtview)
    TextView asynctask_txtview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        ButterKnife.bind(this);

        initAndroidAsync();
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
}


