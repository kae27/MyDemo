package test.byjay.myapplication;


import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class Restful {

    private final String url = "https://thingspeak.com/";
    ArrayList<myhouse> temp = new ArrayList<myhouse>();
    result result;

    public  void createService(final callback CallbackListener)
    {

        final Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create()).build();


        final houseInterface house = retrofit.create(houseInterface.class);
        Call<result>call = house.getData(9);


        call.enqueue(new Callback<result>()
        {
            @Override
            public void onResponse(Call<result> call, Response<result> response)
            {
                int statusCode = response.code();
                result = response.body();

                if (result==null)
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
                    temp = result.getTodos();
                    CallbackListener.onResponse( temp , retrofit);
                }


            }

            @Override
            public void onFailure(Call<result> call, Throwable t)
            {
                Log.i("Restful", "onFailure:" + t.toString());
                CallbackListener.onFailure(t);
            }


        });



    }

    interface houseInterface
    {

        @GET("channels/{id}/feed.json")
        Call<result> getData(@Path("id") int id);
    }

}


