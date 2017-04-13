package com.example.dell.mydemo;

import android.app.Activity;
import android.os.AsyncTask;

/**
 * Created by Dell on 3/8/2017.
 */

public class Background extends AsyncTask<Void,Void, Void>
{

    Activity activity;
    public Background() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }
}
