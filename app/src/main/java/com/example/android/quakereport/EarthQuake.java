/*Class where is storage all the earthquake information*/

package com.example.android.quakereport;

public class EarthQuake {
    //Magnitude of earthquake
    private double mMagnitude;
    //Place of earthquake
    private String mLocation;
    //Date of earthquake
    private String mDate;

    public EarthQuake(double mMagnitude, String mLocation, String mDate) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mDate = mDate;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getDate() {
        return mDate;
    }
}
