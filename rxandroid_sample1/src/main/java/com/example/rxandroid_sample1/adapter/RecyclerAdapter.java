package com.example.rxandroid_sample1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rxandroid_sample1.R;
import com.example.rxandroid_sample1.model.RecyclerItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    List<RecyclerItem> mItemList = new ArrayList<>();
    Context context;

    private PublishSubject<RecyclerItem> mPublishSubject;

    public RecyclerAdapter(List<RecyclerItem> mItemList, Context context) {
        this.mItemList = mItemList;
        this.context = context;
    }

    public RecyclerAdapter(){
        this.mPublishSubject = PublishSubject.create();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RecyclerItem item = mItemList.get(position);
        holder.item_image.setImageDrawable(item.getImage());
        holder.item_text.setText(item.getTitle());
        holder.getClickObserver(item).subscribe(mPublishSubject);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void updateItems(List<RecyclerItem> items){
        mItemList.addAll(items);
    }

    public void updateItems(RecyclerItem items){
        mItemList.add(items);
    }

    public PublishSubject<RecyclerItem> getItemPublishSubject(){
        return mPublishSubject;
    }



    class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_imageview)
        ImageView item_image;
        @BindView(R.id.item_title)
        TextView item_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        Observable<RecyclerItem> getClickObserver(RecyclerItem item){
            return Observable.create(e -> itemView.setOnClickListener(
                    view -> e.onNext(item)
            ));
        }
    }

}


