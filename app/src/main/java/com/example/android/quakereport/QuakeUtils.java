package com.example.android.quakereport;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/*Class utility for requesting the connection*/
final class QuakeUtils {

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QuakeUtils.class.getSimpleName();
    //Variable to check if the time out was expired or not
    static boolean timeOut = false;

    //Method for fetching the json
    static String fetchURL(String requestURL) {

        //Create Url Object
        URL url = createURL(requestURL);

        //initialize the jsonQuakes result to null
        String jsonQuakes = null;

        //making the request between try and catch to got possibles errors
        try {
            jsonQuakes = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    //Make the request to the URL
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        //If there's not url, return a jsonResponse empty
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            //Making the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            //Using the GET parameter
            urlConnection.setRequestMethod("GET");
            //Setting the times
            urlConnection.setReadTimeout(15000 /*milliseconds*/);
            urlConnection.setConnectTimeout(20000 /*milliseconds*/);
            //Connecting
            urlConnection.connect();

            //If the connection is ok
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream(); //get the stream
                jsonResponse = readFromStream(inputStream); //Convert the stream
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            //getting the time out exception and setting to true the timeOut variable
            if (e.getMessage().equals("timeout")) {
                timeOut = true;
            }
            Log.e(LOG_TAG, e.getMessage());
        } finally {

            //Closing the connection
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            //Closing the stream
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        //Constructor del String
        StringBuilder quakeOutput = new StringBuilder();

        //If inputStream is different of null

        if (inputStream != null) {
            //Read the stream using UTF-8 format
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, Charset.forName("UTF-8"));
            //Read the file efficiently
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();

            while (line != null) {
                quakeOutput.append(line);
                line = reader.readLine();
            }

        }
        //return the json
        return quakeOutput.toString();
    }
}
