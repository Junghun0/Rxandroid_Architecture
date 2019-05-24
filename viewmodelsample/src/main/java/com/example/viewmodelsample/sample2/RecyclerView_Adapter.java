package com.example.viewmodelsample.sample2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodelsample.R;
import com.example.viewmodelsample.databinding.RecyclerItemLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.PersonViewHolder> {

    private List<Person> mList = new ArrayList<>();

    public void setItem(List<Person> list){
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout,parent,false);
        RecyclerItemLayoutBinding itemBinding =
                RecyclerItemLayoutBinding.inflate(layoutInflater, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = mList.get(position);
        holder.binding.setPerson(person);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        public RecyclerItemLayoutBinding binding;
        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
