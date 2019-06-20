/*Main activity of the project*/

package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class QuakeActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {


    //URL for the last 10 earthquake with more than 6 of magnitude
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query" +
                    "?format=geojson&eventtype=earthquake&orderby=time&minmag=8&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quake_list);

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(1, null, this);
    }


    void updateList(String jsonQuakes) {
        TextView emptyTextView = findViewById(R.id.empty_view);
        emptyTextView.setText(R.string.not_quake_found);

        // Find a reference to the ListView
        ListView quakeListView = findViewById(R.id.quake_list);
        quakeListView.setEmptyView(emptyTextView);

        // Create a new QuakeAdapter
        final QuakeAdapter adapter = new QuakeAdapter(this,
                QuakeQuery.extractQuakes(jsonQuakes));

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

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new QuakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String quakeList) {
        updateList(quakeList);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
