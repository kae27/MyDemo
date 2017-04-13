package com.example.retrofit.senddatawithretrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 11/3/2560.
 */

public class userModel
{
    @SerializedName("username")
    String UserName;

    @SerializedName("password")
    String Password;


    public userModel(String userName, String password)
    {
        UserName = userName;
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
