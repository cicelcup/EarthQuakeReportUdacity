/*Class where is storage all the earthquake information*/

package com.example.android.quakereport;

public class EarthQuake {
    //Magnitude of earthquake
    private double mMagnitude;
    //Place of earthquake
    private String mPlace;
    //Date of earthquake
    private int mDate;

    public EarthQuake(double mMagnitude, String mPlace, int mDate) {
        this.mMagnitude = mMagnitude;
        this.mPlace = mPlace;
        this.mDate = mDate;
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
