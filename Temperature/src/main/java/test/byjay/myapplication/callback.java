package test.byjay.myapplication;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Created by K550J on 18/3/2559.
 */
public interface callback
{
    public void onResponse(ArrayList<myhouse> myhouses, Retrofit retrofit);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);

}
