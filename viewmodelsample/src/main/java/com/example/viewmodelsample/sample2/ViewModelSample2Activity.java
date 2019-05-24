package com.example.viewmodelsample.sample2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodelsample.R;

import java.util.ArrayList;
import java.util.List;

public class ViewModelSample2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmodel_sample2);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        RecyclerView_Adapter adapter = new RecyclerView_Adapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        List<Person> recyclerItems = new ArrayList<>();
        recyclerItems.add(new Person(12,"dd"));
        recyclerItems.add(new Person(12,"dd"));
        recyclerItems.add(new Person(12,"dd"));
        recyclerItems.add(new Person(12,"dd"));
        recyclerItems.add(new Person(12,"dd"));
        recyclerItems.add(new Person(12,"dd"));

        adapter.setItem(recyclerItems);
    }
}
