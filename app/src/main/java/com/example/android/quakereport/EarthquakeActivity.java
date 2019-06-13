/*Main activity of the project*/

package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquakelist);

        // Create a fake list of earthquake locations.
        ArrayList<EarthQuake> earthquakes = new ArrayList<EarthQuake>();

        earthquakes.add(new EarthQuake(6.5, "San Francisco", "8 de Diciembre"));
        earthquakes.add(new EarthQuake(3.5, "Miami", "6 de Julio"));
        earthquakes.add(new EarthQuake(4.5, "San Jose", "8 de Febrero"));
        earthquakes.add(new EarthQuake(5.5, "San Antonio", "18 de Marzo"));
        earthquakes.add(new EarthQuake(7.5, "Los Angeles", "14 de Abril"));
        earthquakes.add(new EarthQuake(3.5, "Alajuela", "2 de Enero"));
        earthquakes.add(new EarthQuake(2.5, "Heredia", "6 de Mayo"));

        // Find a reference to the ListView
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new EarthQuakeAdapter
        EarthQuakeAdapter adapter = new EarthQuakeAdapter(this, earthquakes);

        // Set the adapter
        earthquakeListView.setAdapter(adapter);
    }
}
