/*Class where is storage all the earthquake information*/

package com.example.android.quakereport;

class Quake {
    //Magnitude of earthquake
    private double mMagnitude;
    //Place of earthquake
    private String mLocation;
    //Date of earthquake
    private Long mDate;

    //Constructor
    Quake(double mMagnitude, String mLocation, Long mDate) {
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

    Long getDate() {
        return mDate;
    }
}
