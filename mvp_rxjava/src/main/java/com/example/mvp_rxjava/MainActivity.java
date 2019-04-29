package com.example.mvp_rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.mvp_rxjava.adapter.MovieRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //key f8528e508b93d59e755310d63eb0455a

    @BindView(R.id.movie_recyclerView)
    RecyclerView movie_recyclerView;
    @BindView(R.id.date_textView)
    TextView date_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        

        MovieRecyclerAdapter adapter = new MovieRecyclerAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        movie_recyclerView.setLayoutManager(linearLayoutManager);
        movie_recyclerView.setAdapter(adapter);
    }
}


