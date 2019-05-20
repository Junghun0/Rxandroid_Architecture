package com.example.databinding_sample;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.databinding_sample.data.User;
import com.example.databinding_sample.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity implements User_Bind_Fragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User("JungHoon", "Park");
        binding.setUser(user);

        binding.showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User_Bind_Fragment fragment = new User_Bind_Fragment();
                getSupportFragmentManager().beginTransaction().add(binding.frameLayoutRecyclerview.getId(), fragment).commit();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
