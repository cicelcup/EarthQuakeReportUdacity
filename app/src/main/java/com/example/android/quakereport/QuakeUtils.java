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

    //Fetching the json
    static String fetchURL(String requestURL) {

        /*Just for remember how to stop a thread for seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/        //Create Url Object
        URL url = createURL(requestURL);

        String jsonQuakes = null;

        //making the request

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

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

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
            urlConnection.setReadTimeout(10000 /*miliseconds*/);
            urlConnection.setConnectTimeout(15000 /*miliseconds*/);
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
            Log.e(LOG_TAG, "Error retrieving the JSON result", e);
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
