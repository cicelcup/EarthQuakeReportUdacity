package com.example.android.quakereport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
final class QuakeQuery {


    private QuakeQuery() { //avoiding the creating of objects based in this class
    }


    static ArrayList<Quake> extractQuakes(String json) {
        //Array for the earthQuake
        ArrayList<Quake> arrayOfQuakes = new ArrayList<>();

        try {
            JSONObject resultOfQuery = new JSONObject(json);

            JSONArray quakeList = resultOfQuery.getJSONArray("features");

            for (int i = 0; i < quakeList.length(); i++) {
                JSONObject oneQuake = quakeList.getJSONObject(i);

                JSONObject quakeProperties = oneQuake.getJSONObject("properties");

                arrayOfQuakes.add(new Quake(
                        Float.valueOf(quakeProperties.getString("mag")),
                        quakeProperties.getString("place"),
                        quakeProperties.getLong("time"),
                        quakeProperties.getString("url")));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return arrayOfQuakes;
    }

}
