/*Main activity of the project*/

package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquakelist);

        // Find a reference to the ListView
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new EarthQuakeAdapter
        EarthQuakeAdapter adapter = new EarthQuakeAdapter(this,
                QueryEarthQuake.extractEarthquakes());

        // Set the adapter
        earthquakeListView.setAdapter(adapter);
    }
}
