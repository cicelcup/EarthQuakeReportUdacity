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

import java.net.URL;
import java.util.ArrayList;

public class QuakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quake_list);

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

    private class RequestQuake extends AsyncTask<URL, Void, ArrayList<Quake>> {
        @Override
        protected ArrayList<Quake> doInBackground(URL... urls) {
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Quake> quakes) {
            super.onPostExecute(quakes);
        }
    }

}
