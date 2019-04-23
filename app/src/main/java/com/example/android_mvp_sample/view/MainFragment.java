package com.example.android_mvp_sample.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_mvp_sample.R;
import com.example.android_mvp_sample.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {
    @BindView(R.id.view_txtview)
    TextView main_textView;

    private MainPresenter mainPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_layout,container,false);
        ButterKnife.bind(this,view);

        mainPresenter = new MainPresenter();

        return view;
    }
}
