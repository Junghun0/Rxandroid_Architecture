package com.example.viewmodelsample.sample1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.viewmodelsample.MainActivity;
import com.example.viewmodelsample.R;
import com.example.viewmodelsample.databinding.ActivityContainSamplesBinding;
import com.example.viewmodelsample.sample2.ViewModelSample2Activity;
import com.example.viewmodelsample.sample3.Viewpager_Recycler_Activity;

public class ContainSamplesActivity extends AppCompatActivity {

    private ActivityContainSamplesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contain_samples);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contain_samples);
        binding.showViewModelSample.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
        binding.showRecyclerViewSample.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ViewModelSample2Activity.class);
            startActivity(intent);
        });
        binding.showRecyclerView2Sample.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Viewpager_Recycler_Activity.class);
            startActivity(intent);
        });
    }
}
