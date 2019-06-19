package com.example.android.quakereport;

import java.net.MalformedURLException;
import java.net.URL;

/*Class utility for requesting the connection*/
public final class QuakeUtils {

    //Fetching the json
    public static String fetchURL(String requestURL) {
        //Create Url Object
        URL url = createURL(requestURL);

        //making the request
        String jsonQuakes = makeHttpRequest(url);

        return jsonQuakes;
    }

    //Creating the URL Method
    private static URL createURL(String stringURL) {

        URL url = null;

        //Verifying the stringURL is valid
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url; //returning the correct URL
    }

    private static String makeHttpRequest(URL url) {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        return jsonResponse;
    }
}
