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
            //Initiating the JSONObject
            JSONObject resultOfQuery = new JSONObject(json);

            //Getting the features array
            JSONArray quakeList = resultOfQuery.getJSONArray("features");

            //Checking all the JSON items
            for (int i = 0; i < quakeList.length(); i++) {
                //getting and quake item
                JSONObject oneQuake = quakeList.getJSONObject(i);

                //getting the properties JSON
                JSONObject quakeProperties = oneQuake.getJSONObject("properties");

                //Adding to the quake class the values of the JSON
                arrayOfQuakes.add(new Quake(
                        Float.valueOf(quakeProperties.getString("mag") /*string magnitude*/),
                        quakeProperties.getString("place" /*string place*/),
                        quakeProperties.getLong("time" /*Long time*/),
                        quakeProperties.getString("url" /*string url*/)));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //return the array of quakes
        return arrayOfQuakes;
    }

}
