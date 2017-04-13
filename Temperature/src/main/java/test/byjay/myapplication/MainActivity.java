package test.byjay.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener
{
    LineChart chart;
    ArrayList<ILineDataSet>  dataset;

    callback networkCallBack = new callback()
    {
        @Override
        public void onResponse(ArrayList<myhouse> myhouses, Retrofit retrofit)
        {
                    Log.e("MainActivity","size of temp"+myhouses.size());

                    dataset = new ArrayList<>();
                    ArrayList<Entry> light = new ArrayList<>();
                    ArrayList<Entry> temperature = new ArrayList<>();
                    ArrayList<String> labels = new ArrayList<String>();

                    int size = myhouses.size();

                    for(int i = 0 ; i <size ; i=i+3)
                    {

                        light.add(new Entry((float) myhouses.get(i).getLight(), i ) );
                        temperature.add(new Entry((float) myhouses.get(i).getTemperature() , i ) );
                        labels.add( myhouses.get(i).getDate().toString() );

                    }

                    LineDataSet temp1 = new LineDataSet(light,"Light");
                    temp1.setColor(Color.RED);
                    temp1.setCircleColorHole(Color.RED);
                    temp1.setCircleColor(Color.RED);


                    LineDataSet temp2 = new LineDataSet(temperature,"Temperature");

                    temp2.setColor(Color.BLUE);
                    temp2.setCircleColorHole(Color.BLUE);
                    temp2.setCircleColor(Color.BLUE);



                    dataset.add(temp1);
                    dataset.add(temp2);



                    LineData data = new LineData(labels, dataset);
                    YAxis Yleft = chart.getAxisLeft();


                    YAxis Yright = chart.getAxisRight();
                    Yright.setEnabled(false);

                    XAxis x = chart.getXAxis();
                    x.setPosition(XAxis.XAxisPosition.BOTTOM);

                    Yleft.setAxisMaxValue(400);
                    Yleft.setAxisMinValue(0);

                    chart.setData(data);
                    chart.setDescription("Outside Temperature and Light Plugin");
                    chart.setDragDecelerationEnabled(true);
                    chart.setDrawBorders(true);
                    chart.setTouchEnabled(true);
                    chart.invalidate();
        }

        @Override
        public void onBodyError(ResponseBody responseBodyError)
        {

        }

        @Override
        public void onBodyErrorIsNull()
        {

        }


        @Override
        public void onFailure(Throwable t)
        {

        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        chart = (LineChart) findViewById(R.id.chart);
        new Restful().createService(networkCallBack);

//        Background test = new Background(MainActivity.this , networkCallBack );
//        test.execute();



    }


    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h)
    {
        Toast.makeText(this, ""+dataSetIndex, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected()
    {

    }

    
}








