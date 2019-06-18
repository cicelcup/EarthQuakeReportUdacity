/*Class where is storage all the earthquake information*/

package com.example.android.quakereport;

class Quake {
    //Magnitude of earthquake
    private float mMagnitude;
    //Place of earthquake
    private String mLocation;
    //Date of earthquake
    private Long mDate;

    //Constructor
    Quake(float mMagnitude, String mLocation, Long mDate) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mDate = mDate;
    }

    //Getters for locals variables
    float getMagnitude() {
        return mMagnitude;
    }

    String getLocation() {
        return mLocation;
    }

    Long getDate() {
        return mDate;
    }
}
