package test.byjay.myapplication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by K550J on 17/3/2559.
 */
public class myhouse
{
    @SerializedName("field2")
    double temperature;

    @SerializedName("field1")
    double light;

    @SerializedName("created_at")
    String date;

    public myhouse(double temperature, double light, String date)
    {
        this.temperature = temperature;
        this.light = light;
        this.date = date;

    }


    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public String getDate() {
        return date.substring(11,date.length()-1);
    }

    public void setDate(String date) {
        this.date = date;
    }
}
