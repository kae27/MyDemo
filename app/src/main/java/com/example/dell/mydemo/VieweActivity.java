package com.example.dell.mydemo;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.dell.mydemo.databinding.SelecteButtonBinding;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;


/**
 * Created by Dell on 2/6/2017.
 */

public class VieweActivity extends AppCompatActivity
{
    final String[] quarters = new String[] { "160817", "160818", "160819", "160820","160821" };
    SelecteButtonBinding binding;
    ArrayList<RelativeLayout> relativeLayouts = new ArrayList<RelativeLayout>();
    ArrayList<TextView> textViews = new ArrayList<TextView>();
    ArrayList<Entry> entries = new ArrayList<>();
    ArrayList<Integer> minOfList = new ArrayList<Integer>();
    int size;
    double temp;




    MyMarkerView markerView;
    LineChart chart;
    ArrayList<LocationMapResponseModel> list;

    callback callback = new callback()
    {
        @Override
        public void onResponse(LocationMapResponseModel responseModel, Retrofit retrofit)
        {

            list = responseModel.getResponseModels();
            Log.e("ViewActivity","size of temp "+list.size());  //size=33 object

            size = list.size();
            Log.e("ViewActivity","Success "+list.get(size-1).getSuccess());  





            temp = 0.0;
            for (int i = 0; i < size; i++) {
                entries.add(new Entry((float) temp, (float) list.get(i).getWamDragCo()));
                temp = temp + 0.125;
                minOfList.add(list.get(i).getWamDragCo());
            }

            setCustomChart();

        }

        @Override
        public void onResponse(ArrayList<LocationMapResponseModel> myhouses, Retrofit retrofit) {

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
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.viewforcast);

        binding = DataBindingUtil.setContentView(VieweActivity.this,R.layout.selecte_button);

//        setContentView(R.layout.selecte_button);

        binding.dragCoefficient.setOnClickListener(Onclick);
        binding.fictionVeiocity.setOnClickListener(Onclick);
        binding.meanFrequency.setOnClickListener(Onclick);
        binding.meanPeriod.setOnClickListener(Onclick);
        binding.meanWaveDirection.setOnClickListener(Onclick);
        binding.normallizedWave.setOnClickListener(Onclick);
        binding.significantWaveHight.setOnClickListener(Onclick);
        binding.wavePeakFrequency.setOnClickListener(Onclick);
        binding.wavePeakPeriod.setOnClickListener(Onclick);
        binding.windDirection.setOnClickListener(Onclick);
        binding.windSpeed.setOnClickListener(Onclick);

        relativeLayouts.add(binding.dragCoefficient);
        relativeLayouts.add(binding.fictionVeiocity);
        relativeLayouts.add(binding.meanFrequency);
        relativeLayouts.add(binding.meanPeriod);
        relativeLayouts.add(binding.meanWaveDirection);
        relativeLayouts.add(binding.normallizedWave);
        relativeLayouts.add(binding.significantWaveHight);
        relativeLayouts.add(binding.wavePeakFrequency);
        relativeLayouts.add(binding.wavePeakPeriod);
        relativeLayouts.add(binding.windDirection);
        relativeLayouts.add(binding.windSpeed);

        textViews.add(binding.textView1);
        textViews.add(binding.textView2);
        textViews.add(binding.textView3);
        textViews.add(binding.textView4);
        textViews.add(binding.textView5);
        textViews.add(binding.textView6);
        textViews.add(binding.textView7);
        textViews.add(binding.textView8);
        textViews.add(binding.textView9);
        textViews.add(binding.textView10);
        textViews.add(binding.textView11);
        setActive(relativeLayouts.get(0).getId());


        markerView = new MyMarkerView(this, R.layout.custom_marker_chart);
        markerView.setChartView(binding.chart);
        binding.chart.setMarker(markerView);


        // drawChart();
        Log.e("ViewActivity","size of temp ");

        new Restful().createService(callback);
    }

    View.OnClickListener Onclick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            setDefault();

            switch(v.getId()) {

                case R.id.dragCoefficient:
                    setActive(v.getId());
                    size = list.size();
                    entries.clear();
                    minOfList.clear();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp, (float) list.get(i).getWamDragCo()));
                        temp = temp + 0.125;
                        minOfList.add(list.get(i).getWamDragCo());
                    }
                    Log.i("Button","dragCoefficient"+ Collections.min(minOfList));
                    break;

                case R.id.fictionVeiocity:
                    setActive(v.getId());
                    size = list.size();
                    entries.clear();
                    minOfList.clear();
                    temp = 0.0;
                    for(int i = 0 ; i<size ; i++)
                    {
                        entries.add(new Entry((float) temp, (float) list.get(i).getWamUstar10()));
                        temp = temp + 0.125;
                        minOfList.add(list.get(i).getWamUstar10());
                    }
                    Log.i("Button","fictionVeiocity"+Collections.min(minOfList));
                    break;

                case R.id.meanFrequency:
                    setActive(v.getId());
                    size = list.size();
                    entries.clear();
                    minOfList.clear();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp, (float) list.get(i).getWam_mFreqy()));
                        temp = temp + 0.125;
                        minOfList.add(list.get(i).getWam_mFreqy());
                    }
                    Log.i("Button","meanFrequency"+Collections.min(minOfList));
                    break;

                case R.id.meanPeriod:
                    setActive(v.getId());
                    size = list.size();
                    entries.clear();
                    minOfList.clear();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp, (float) list.get(i).getWam_mPerio()));
                        temp = temp + 0.125;
                        minOfList.add(list.get(i).getWam_mPerio());
                    }
                    Log.i("Button","meanPeriod"+Collections.min(minOfList));
                    break;

                case R.id.meanWaveDirection:
                    setActive(v.getId());
                    size = list.size();
                    entries.clear();
                    minOfList.clear();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp, (float) list.get(i).getWamWaveDir()));
                        temp = temp + 0.125;
                        minOfList.add(list.get(i).getWamWaveDir());
                    }
                    Log.i("Button","meanWaveDirection"+Collections.min(minOfList));
                    break;

                case R.id.normallizedWave:
                    setActive(v.getId());
                    size = list.size();
                    entries.clear();
                    minOfList.clear();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp, (float) list.get(i).getWamWaveSte()));
                        temp = temp + 0.125;
                        minOfList.add(list.get(i).getWamWaveSte());
                    }
                    Log.i("Button","normallizedWave"+Collections.min(minOfList));
                    break;

                case R.id.significantWaveHight:
                    setActive(v.getId());
                    size = list.size();
                    entries.clear();
                    minOfList.clear();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp, (float) list.get(i).getWamWaveHgt()));
                        temp = temp + 0.125;
                        minOfList.add(list.get(i).getWamWaveHgt());
                    }
                    Log.i("Button","significantWaveHight"+Collections.min(minOfList));
                    break;

                case R.id.wavePeakFrequency:
                    setActive(v.getId());
                    size = list.size();
                    entries.clear();
                    minOfList.clear();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp, (float) list.get(i).getWam_pFreqy()));
                        temp = temp + 0.125;
                        minOfList.add(list.get(i).getWam_pFreqy());
                    }
                    Log.i("Button","wavePeakFrequency"+Collections.min(minOfList));
                    break;

                case R.id.wavePeakPeriod:
                    setActive(v.getId());
                    size = list.size();
                    entries.clear();
                    minOfList.clear();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp, (float) list.get(i).getWam_pPerio()));
                        temp = temp + 0.125;
                        minOfList.add(list.get(i).getWam_pPerio());
                    }
                    Log.i("Button","wavePeakPeriod"+Collections.min(minOfList));
                    break;

                case R.id.windDirection:
                    setActive(v.getId());
                    size = list.size();
                    entries.clear();
                    minOfList.clear();
                    temp = 0.0;
                    for (int i = 0; i < size; i++)
                    {
                        entries.add(new Entry((float) temp, (float) list.get(i).getWamWindDir()));
                        temp = temp + 0.125;
                        minOfList.add(list.get(i).getWamWindDir());
                    }
                    Log.i("Button","windDirection"+Collections.min(minOfList));
                    break;

                case R.id.windSpeed:
                    setActive(v.getId());
                    size = list.size();
                    entries.clear();
                    minOfList.clear();
                    temp = 0.0;
                    for(int i = 0 ; i<size ; i++)
                    {
                        entries.add(new Entry((float) temp, (float) list.get(i).getWamWindSpd()));
                        temp = temp + 0.125;
                        minOfList.add(list.get(i).getWamWindSpd());
                    }
                    Log.i("Button","windSpeed"+Collections.min(minOfList));
                    break;
            }

            binding.chart.invalidate();//refresh


        }


    };


    public void setDefault()
    {
        int size=0;
        size = relativeLayouts.size();


        for(int i = 0 ; i<size ; i++)
        {
            relativeLayouts.get(i).setBackground( ContextCompat.getDrawable(VieweActivity.this,R.drawable.unactive ) );
//            relativeLayouts.get(i).setBackground( getResources().getDrawable( R.drawable.unactive) );
            textViews.get(i).setTextColor(ContextCompat.getColor(VieweActivity.this,R.color.colorGreen));
        }

    }

    public void setActive(int id)
    {

        int size=0;
        size = relativeLayouts.size();


        for(int i = 0 ; i<size ; i++)
        {
            if(id == relativeLayouts.get(i).getId())
            {
                relativeLayouts.get(i).setBackground( ContextCompat.getDrawable(VieweActivity.this,R.drawable.active ) );
//                relativeLayouts.get(i).setBackground(getResources().getDrawable(R.drawable.active));
                textViews.get(i).setTextColor(ContextCompat.getColor(VieweActivity.this, R.color.colorWhite));
            }
        }

    }


    public void setCustomChart()
    {

        IAxisValueFormatter formatterX = new IAxisValueFormatter() { //ปรับแกน X
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

//            @Override
//            public int getDecimalDigits() {  return 0; }

        };



        XAxis xAxis =  binding.chart.getXAxis();
        YAxis yAxis = binding.chart.getAxisLeft();
        xAxis.setGranularity(0.125f); // minimum axis-step (interval) is 0.125
        xAxis.setValueFormatter(formatterX);


        //ขอบเขตของกราฟ

//        yAxis.setAxisMinimum((float)Collections.min(minOfList)-10);
        xAxis.setAxisMaximum(4f);
        xAxis.setLabelCount(3);
        yAxis.setSpaceMin(100);
        yAxis.setLabelCount(10);





//       xAxis.enableGridDashedLine(10f, 10f, 0f); //ทำเส้นปะ
//       xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//ย้าย txet แกน X มาอยู่ข้างล่าง

        binding.chart.getAxisRight().setEnabled(false);// Hide right y-axis line
//       binding.chart.setAutoScaleMinMaxEnabled(true);



        LineDataSet dataset = new LineDataSet(entries,"");
        dataset.setLineWidth(2f);
        dataset.setCircleRadius(3.5f);
        dataset.setHighLightColor(Color.rgb(244, 117, 117));
        dataset.setDrawFilled(true); // fill color under chart
        dataset.setDrawValues(false); //hide value chart


        LineData data = new LineData(dataset);
        binding.chart.setData(data);
        binding.chart.setTouchEnabled(true);
        binding.chart.invalidate(); //refresh




    }



}
