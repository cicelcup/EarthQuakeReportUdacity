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
        //assign the url to the mUrl variable
        this.mUrl = url;
    }

    @Nullable
    @Override
    //Start the fetching process
    public String loadInBackground() {
        //Check if the mURL is not null
        if (mUrl == null) {
            return null;
        }
        //Quake utils where is the process of fetching the JSON
        return QuakeUtils.fetchURL(mUrl);
    }

    //Starting the process (through force load method)
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
