/*Class where is storage all the earthquake information*/

package com.example.android.quakereport;

class Quake {
    //Magnitude of earthquake
    private double mMagnitude;
    //Place of earthquake
    private String mLocation;
    //Date of earthquake
    private String mDate;

    //Constructor
    Quake(double mMagnitude, String mLocation, String mDate) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mDate = mDate;
    }

    //Getters for locals variables
    double getMagnitude() {
        return mMagnitude;
    }

    String getLocation() {
        return mLocation;
    }

    String getDate() {
        return mDate;
    }
}
