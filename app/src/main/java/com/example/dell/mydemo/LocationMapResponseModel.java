package com.example.dell.mydemo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Dell on 3/17/2017.
 */

public class LocationMapResponseModel
{

    @SerializedName("date")
    String Date;

    @SerializedName("WAM_DRAG_CO")
    int WamDragCo;

    @SerializedName("WAM_M_PERIO")
    int Wam_mPerio;

    @SerializedName("WAM_P_PERIO")
    int Wam_pPerio;

    @SerializedName("WAM_M_FREQY")
    int Wam_mFreqy;

    @SerializedName("WAM_P_FREQY")
    int Wam_pFreqy;

    @SerializedName("WAM_USTAR10")
    int WamUstar10;

    @SerializedName("WAM_WAVEDIR")
    int WamWaveDir;

    @SerializedName("WAM_WAVEHGT")
    int WamWaveHgt;

    @SerializedName("WAM_WAVESTE")
    int WamWaveSte;

    @SerializedName("WAM_WINDDIR")
    int WamWindDir;

    @SerializedName("WAM_WINDSPD")
    int WamWindSpd;

    @SerializedName("success")
    int success;




    @SerializedName("tmd")
    ArrayList<LocationMapResponseModel> responseModels;

    public LocationMapResponseModel(String date, int wamDragCo, int wam_mPerio, int wam_pPerio, int wam_mFreqy,
                                    int wam_pFreqy, int wamUstar10, int wamWaveDir, int wamWaveHgt, int wamWaveSte, int wamWindDir, int wamWindSpd) {
        Date = date;
        WamDragCo = wamDragCo;
        Wam_mPerio = wam_mPerio;
        Wam_pPerio = wam_pPerio;
        Wam_mFreqy = wam_mFreqy;
        Wam_pFreqy = wam_pFreqy;
        WamUstar10 = wamUstar10;
        WamWaveDir = wamWaveDir;
        WamWaveHgt = wamWaveHgt;
        WamWaveSte = wamWaveSte;
        WamWindDir = wamWindDir;
        WamWindSpd = wamWindSpd;

    }

    public ArrayList<LocationMapResponseModel> getResponseModels() {
        return responseModels;
    }

    public void setResponseModels(ArrayList<LocationMapResponseModel> responseModels) {
        this.responseModels = responseModels;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getWamDragCo() {
        return WamDragCo;
    }

    public void setWamDragCo(int wamDragCo) {
        WamDragCo = wamDragCo;
    }

    public int getWam_mPerio() {
        return Wam_mPerio;
    }

    public void setWam_mPerio(int wam_mPerio) {
        Wam_mPerio = wam_mPerio;
    }

    public int getWam_pPerio() {
        return Wam_pPerio;
    }

    public void setWam_pPerio(int wam_pPerio) {
        Wam_pPerio = wam_pPerio;
    }

    public int getWam_mFreqy() {
        return Wam_mFreqy;
    }

    public void setWam_mFreqy(int wam_mFreqy) {
        Wam_mFreqy = wam_mFreqy;
    }

    public int getWam_pFreqy() {
        return Wam_pFreqy;
    }

    public void setWam_pFreqy(int wam_pFreqy) {
        Wam_pFreqy = wam_pFreqy;
    }

    public int getWamUstar10() {
        return WamUstar10;
    }

    public void setWamUstar10(int wamUstar10) {
        WamUstar10 = wamUstar10;
    }

    public int getWamWaveDir() {
        return WamWaveDir;
    }

    public void setWamWaveDir(int wamWaveDir) {
        WamWaveDir = wamWaveDir;
    }

    public int getWamWaveHgt() {
        return WamWaveHgt;
    }

    public void setWamWaveHgt(int wamWaveHgt) {
        WamWaveHgt = wamWaveHgt;
    }

    public int getWamWaveSte() {
        return WamWaveSte;
    }

    public void setWamWaveSte(int wamWaveSte) {
        WamWaveSte = wamWaveSte;
    }

    public int getWamWindDir() {
        return WamWindDir;
    }

    public void setWamWindDir(int wamWindDir) {
        WamWindDir = wamWindDir;
    }

    public int getWamWindSpd() {
        return WamWindSpd;
    }

    public void setWamWindSpd(int wamWindSpd) {
        WamWindSpd = wamWindSpd;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
