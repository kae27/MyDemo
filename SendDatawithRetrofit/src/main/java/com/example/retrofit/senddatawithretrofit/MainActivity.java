package com.example.retrofit.senddatawithretrofit;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.retrofit.senddatawithretrofit.databinding.ActivityMainBinding;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class MainActivity extends Activity {


    ActivityMainBinding binding;

    callback callback = new callback()
    {
        @Override
        public void onResponse(userModel model, Retrofit retrofit) {

        }

        @Override
        public void onResponse(ArrayList<userModel> myhouses, Retrofit retrofit) {

        }

        @Override
        public void onBodyError(ResponseBody responseBodyError) {

        }

        @Override
        public void onBodyErrorIsNull() {

        }

        @Override
        public void onFailure(Throwable t) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);

        binding.btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new Restful().createService(callback);
            }
        });

    }


}
