package com.example.viewmodelsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.viewmodelsample.databinding.ActivityMainBinding;
import com.example.viewmodelsample.sample1.CountViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CountViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(CountViewModel.class);
        binding.setViewModel(viewModel);
    }
}
