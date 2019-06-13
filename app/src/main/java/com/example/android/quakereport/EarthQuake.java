package com.example.android.quakereport;

public class EarthQuake {
    private double mMagnitude;
    private String mPlace;
    private int mDate;

    public EarthQuake(String mPlace) {
        this.mPlace = mPlace;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getPlace() {
        return mPlace;
    }

    public int getDate() {
        return mDate;
    }
}
