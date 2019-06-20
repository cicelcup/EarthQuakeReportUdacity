package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

//Class to start the fetching in another thread
public class QuakeLoader extends AsyncTaskLoader<String> {
    private String mUrl;

    //Constructor
    public QuakeLoader(@NonNull Context context, String url) {
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

        return QuakeUtils.fetchURL(mUrl);
    }

    //Starting the process
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
