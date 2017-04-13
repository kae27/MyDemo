package test.byjay.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by K550J on 3/4/2559.
 */
public class Background extends AsyncTask<Void , Void , Void>
{

    Activity activity;
    private final String url = "https://thingspeak.com/";
    ArrayList<myhouse> temp = new ArrayList<myhouse>();
    result result;
    Retrofit retrofit;
    callback listener;
    ProgressDialog dialog;


    public Background(Activity act , final callback CallbackListener)
    {
        this.activity = act;
        listener = CallbackListener;
    }


    @Override
    protected Void doInBackground(Void... params)
    {
        final houseInterface house = retrofit.create(houseInterface.class);
        Call<result> call = house.getData(9);

        try
        {
            result = call.execute().body();

            if (result == null)
                listener.onBodyErrorIsNull();
            else
            {
                temp = result.getTodos();


            }

        }
        catch (IOException e)
        {
            listener.onFailure(e);
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute()
    {

        dialog = new ProgressDialog(activity);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Message");
        dialog.setTitle("title");
        dialog.show();

        retrofit =  new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }


    @Override
    protected void onProgressUpdate(Void... values)
    {

    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        try {
            dialog.dismiss();
            Log.i("Background", "before onRespond");
            listener.onResponse(temp, retrofit);
            Log.i("Background", "after onRespond");

        }
        catch (Exception e)
        {

        }


    }

    interface houseInterface
    {
        @GET("channels/{id}/feed.json")
        Call<result> getData(@Path("id") int id);
    }


}
