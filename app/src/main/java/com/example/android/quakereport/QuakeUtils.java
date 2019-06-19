package com.example.android.quakereport;

import java.net.MalformedURLException;
import java.net.URL;

/*Class utility for requesting the connection*/
public final class QuakeUtils {
    //Creating the url and searching for it
    public static String fetchURL(String requestURL) {
        //Create Url Object
        URL url = createURL(requestURL);

        String jsonQuakes = "";

        return jsonQuakes;
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
