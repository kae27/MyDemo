package com.example.retrofit.senddatawithretrofit;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Admin on 11/3/2560.
 */

public class Restful
{

    private final String url = "http://192.168.43.140:8080/";
    ArrayList<userModel> temp = new ArrayList<userModel>();

    userModel requestModel;

    public  void createService(final callback CallbackListener)
    {
        requestModel =  new userModel("jay","1234");

        final Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create()).build();


        final MyApiEndpointInterface loginService = retrofit.create(MyApiEndpointInterface.class);
        Call<userModel>call = loginService.login(requestModel);


        call.enqueue(new Callback<userModel>()
        {
            @Override
            public void onResponse(Call<userModel> call, Response<userModel> response)
            {
                int statusCode = response.code();
                userModel responseModel = response.body();

                if (responseModel==null)
                {

                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null)
                    {
                        CallbackListener.onBodyError(responseBody);
                    } else
                    {
                        CallbackListener.onBodyErrorIsNull();
                    }



                }
                else
                {
                    Log.e("jay", "onResponse: "+responseModel.getUserName() );
                    Log.e("jay", "onResponse: "+responseModel.getPassword() );
                    CallbackListener.onResponse( responseModel , retrofit);
                }




            }

            @Override
            public void onFailure(Call<userModel> call, Throwable t)
            {
                Log.i("Restful", "onFailure:" + t.toString());
                CallbackListener.onFailure(t);
            }


        });



    }


    public interface MyApiEndpointInterface
    {



        @POST("/register")
        Call<userModel> login(@Body userModel user);
    }

}





