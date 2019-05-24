package com.example.viewmodelsample.sample1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CountViewModel extends ViewModel {
    public MutableLiveData<Integer> counter = new MutableLiveData<>();

    public CountViewModel() {
        counter.setValue(0);
    }

    public void increase() {
        counter.setValue(counter.getValue() + 1);
    }

    public void decrease() {
        counter.setValue(counter.getValue() - 1);
    }
}
