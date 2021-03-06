package com.example.android_mvp_sample.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutManager;
import androidx.appcompat.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android_mvp_sample.R;
import com.example.android_mvp_sample.adapter.ImageAdapter;
import com.example.android_mvp_sample.model.ImageItem;
import com.example.android_mvp_sample.model.SampleImageData;
import com.example.android_mvp_sample.presenter.MainContract;
import com.example.android_mvp_sample.presenter.MainPresenter;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ImageAdapter imageAdapter;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(getApplication());

        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.setSampleImageData(SampleImageData.getInstance());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imageAdapter = new ImageAdapter(this);
        recyclerView.setAdapter(imageAdapter);

        mainPresenter.loadItems(this, false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_reload:
                mainPresenter.loadItems(this, true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addItems(ArrayList<ImageItem> items, boolean isClear) {
        if (isClear) {
            imageAdapter.clear();
        }
        imageAdapter.setImageItems(items);
    }

    @Override
    public void notifyAdapter() {
        imageAdapter.notifyDataSetChanged();
    }
}
