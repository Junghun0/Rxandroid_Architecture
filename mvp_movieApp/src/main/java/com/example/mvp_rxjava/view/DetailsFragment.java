package com.example.mvp_rxjava.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvp_rxjava.R;
import com.example.mvp_rxjava.data.MovieDetail;
import com.example.mvp_rxjava.util.RetrofitInstance;
import com.example.mvp_rxjava.util.RetrofitService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsFragment extends Fragment {

    private static final String MOVIE_NAME = "names";

    private String movieName;

    @BindView(R.id.fragment_movieNm)
    TextView fragment_movieNm;

    public static DetailsFragment newInstance(String movieName) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(MOVIE_NAME,movieName);

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment_layout,container,false);
        ButterKnife.bind(this, view);
        if (getArguments() != null){
            movieName = getArguments().getString(MOVIE_NAME);
            fragment_movieNm.setText(movieName);
            getDetailData(movieName);
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void getDetailData(String movieName){
        RetrofitService retrofit = RetrofitInstance.getRetrofitInstance2().create(RetrofitService.class);
        retrofit.sendShortURL(movieName).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        Log.e("success",""+response.body().getItems().get(0).getActor());
                        Log.e("success",""+response.body().getItems().get(0).getDirector());
                    }
                }

            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.e("success",""+ t.toString());
            }
        });
    }
}
