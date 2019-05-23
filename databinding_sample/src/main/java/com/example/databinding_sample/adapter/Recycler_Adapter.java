package com.example.databinding_sample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.databinding_sample.R;
import com.example.databinding_sample.data.User;
import com.example.databinding_sample.databinding.RecyclerItemLayoutBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.BindingViewHolder> {

    private List<User> mList = new ArrayList<>();

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout,parent,false);
        return new BindingViewHolder(view);
    }

    public void setmList(List<User> list){
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder holder, int position) {
        User user = mList.get(position);
        holder.binding.setUser(user);
//      holder.binding.setUser(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class BindingViewHolder extends RecyclerView.ViewHolder {
        public RecyclerItemLayoutBinding binding;

        public BindingViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
