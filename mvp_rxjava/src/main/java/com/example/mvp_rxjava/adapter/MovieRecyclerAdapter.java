package com.example.mvp_rxjava.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvp_rxjava.MainActivity;
import com.example.mvp_rxjava.R;
import com.example.mvp_rxjava.data.DailyBoxOfficeList;

import java.util.ArrayList;
import java.util.List;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder> {

    interface MovieRecyclerClickListener {
        void onDetailClickListener();
    }
    
    private MovieRecyclerClickListener mListener;
    private MainActivity mContext;
    
    private List<DailyBoxOfficeList> mItems = new ArrayList<>();
    private List<String> mThumNailsList = new ArrayList<>();

    public MovieRecyclerAdapter(MainActivity mContext) {this.mContext = mContext;}

    public MovieRecyclerAdapter(MovieRecyclerClickListener listener) {
        mListener = listener;
    }

    public void setItems(List<DailyBoxOfficeList> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    public void addThumNail(String imageURL){
        mThumNailsList.add(imageURL);
    }

    public void setItemThumbnail(List<String> thumbnail){
        this.mThumNailsList = thumbnail;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_layout, parent, false);
        final MovieViewHolder viewHolder = new MovieViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    final int pos = position;
                    viewHolder.movie_imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.onDetailClickListener();
                        }
                    });
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
         DailyBoxOfficeList dailyBoxOfficeList = mItems.get(position);

         holder.movie_rank_textView.setText(dailyBoxOfficeList.getRank());
         holder.movie_title_textView.setText(dailyBoxOfficeList.getMovieNm());
         holder.movie_audience_rate_textView.setText(dailyBoxOfficeList.getAudiChange());
         holder.movie_openDate_textView.setText(dailyBoxOfficeList.getOpenDt());
         holder.movie_sales_textView.setText(dailyBoxOfficeList.getSalesAmt());
         holder.movie_totalSales_textView.setText(dailyBoxOfficeList.getSalesAcc());
         holder.movie_audience_textView.setText(dailyBoxOfficeList.getAudiAcc());
         holder.movie_today_audience_textView.setText(dailyBoxOfficeList.getAudiCnt());

         if (mThumNailsList.size() == 10){
             Glide.with(mContext).load(mThumNailsList.get(position)).into(holder.movie_imageView);
         }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movie_rank_textView;
        TextView movie_title_textView;
        TextView movie_audience_rate_textView;
        TextView movie_openDate_textView;
        TextView movie_sales_textView;
        TextView movie_totalSales_textView;
        TextView movie_audience_textView;
        TextView movie_today_audience_textView;
        ImageView movie_imageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_rank_textView = itemView.findViewById(R.id.movie_rank_textView);
            movie_title_textView = itemView.findViewById(R.id.movie_title_textView);
            movie_audience_rate_textView = itemView.findViewById(R.id.movie_audience_rate_textView);
            movie_openDate_textView = itemView.findViewById(R.id.movie_openDate_textView);
            movie_sales_textView = itemView.findViewById(R.id.movie_sales_textView);
            movie_totalSales_textView = itemView.findViewById(R.id.movie_totalSales_textView);
            movie_audience_textView = itemView.findViewById(R.id.movie_audience_textView);
            movie_today_audience_textView = itemView.findViewById(R.id.movie_today_audience_textView);
            movie_imageView = itemView.findViewById(R.id.movie_imageView);
            // TODO : 뷰홀더 완성하시오
        }
    }
}
