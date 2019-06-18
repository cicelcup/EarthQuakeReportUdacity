/*Class where is storage all the earthquake information*/

package com.example.android.quakereport;

class Quake {
    //Magnitude of earthquake
    private float mMagnitude;
    //Place of earthquake
    private String mLocation;
    //Date of earthquake
    private Long mDate;
    //URl of earthquake
    private String mUrl;

    //Constructor
    Quake(float mMagnitude, String mLocation, Long mDate, String mUrl) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mDate = mDate;
        this.mUrl = mUrl;
    }

    //Getters for locals variables
    float getMagnitude() {
        return mMagnitude;
    }

    String getLocation() {
        return mLocation;
    }

    String getUrl() {
        return mUrl;
    }

    Long getDate() {
        return mDate;
    }
}
