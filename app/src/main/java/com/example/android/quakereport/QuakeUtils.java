package com.example.android.quakereport;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/*Class utility for requesting the connection*/
public final class QuakeUtils {
    //Creating the url and searching for it
    public static ArrayList<Quake> fetchURL(String requestURL) {
        //Create Url Object
        URL url = createURL(requestURL);

        ArrayList<Quake> quakes = new ArrayList<>();

        return quakes;
    }

    //Creating the URL
    private static URL createURL(String stringURL) {
        URL url = null;

        //Creating the Url Method
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
