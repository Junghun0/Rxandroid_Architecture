package com.example.databinding_sample;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.databinding_sample.data.User;
import com.example.databinding_sample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements User_Bind_Fragment.OnFragmentInteractionListener{

    User user = new User("JungHoon", "Park");
    private ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setUser(user);


//        binding.showButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                User_Bind_Fragment fragment = new User_Bind_Fragment();
//                getSupportFragmentManager().beginTransaction().add(binding.frameLayoutRecyclerview.getId(), fragment).commit();
//            }
//        });
    }
    //observable objects
    public void onValueChanged(View view){
        Log.e("binding onclick","called");
        //데이터만 변경되어도 자동으로 layout 에도 반영된다.
        user.setFirstName("변경된 FirstName");
        user.setLastName("변경된 LastName");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
