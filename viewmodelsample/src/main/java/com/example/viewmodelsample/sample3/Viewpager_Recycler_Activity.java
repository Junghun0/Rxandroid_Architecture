package com.example.viewmodelsample.sample3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodelsample.R;

import java.util.ArrayList;
import java.util.List;

public class Viewpager_Recycler_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager__recycler);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ImageFragment());
        fragmentList.add(new ImageFragment());
        fragmentList.add(new ImageFragment());
        fragmentList.add(new ImageFragment());
        fragmentList.add(new ImageFragment());


        List<Item> items = new ArrayList<>();
        items.add(new Item<>(fragmentList));
        for (int i = 0; i < 30; i++) {
            items.add(new Item<>("item"));
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getSupportFragmentManager(), items);
        recyclerView.setAdapter(adapter);
    }
}
