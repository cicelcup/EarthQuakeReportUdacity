/*Main activity of the project*/

package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class QuakeActivity extends AppCompatActivity {

    //URL for the last 10 earthquake with more than 6 of magnitude
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query" +
                    "?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quake_list);

        QuakeRequest quakeRequest = new QuakeRequest();
        //quakeRequest.execute(USGS_REQUEST_URL);

        // Find a reference to the ListView
        ListView quakeListView = findViewById(R.id.quake_list);

        // Create a new QuakeAdapter
        final QuakeAdapter adapter = new QuakeAdapter(this,
                QuakeQuery.extractQuakes());

        // Set the adapter
        quakeListView.setAdapter(adapter);

        //Set the click into an item of the list

        quakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent openQuake = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        adapter.getItem(position).getUrl()));
                startActivity(openQuake);
            }
        });
    }

    private class QuakeRequest extends AsyncTask<String, Void, String> {
        @Override
        //Starting the thread
        protected String doInBackground(String... urls) {

            String quakes;

            //Verifying the url is not empty
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            quakes = QuakeUtils.fetchURL(urls[0]);
            return quakes;
        }

        //after the thread finish to execute
        @Override
        protected void onPostExecute(String quakes) {
            super.onPostExecute(quakes);
        }
    }

}
