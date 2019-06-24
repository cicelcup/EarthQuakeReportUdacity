package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

//Class to start the fetching in another thread
public class QuakeLoader extends AsyncTaskLoader<String> {
    private String mUrl;

    //Constructor
    QuakeLoader(@NonNull Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Nullable
    @Override
    //Start the fetching process
    public String loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        //Quake utils where is the process of fetching the JSON
        return QuakeUtils.fetchURL(mUrl);
    }

    //Starting the process
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
