package test.byjay.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by K550J on 18/3/2559.
 */
public class result
{

    @SerializedName("feeds")
    private ArrayList<myhouse> todos = new ArrayList<myhouse>();

    public ArrayList<myhouse> getTodos()
    {
        return todos;
    }

}
