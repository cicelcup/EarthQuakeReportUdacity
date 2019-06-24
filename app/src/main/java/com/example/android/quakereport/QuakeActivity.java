/*Main activity of the project*/

package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class QuakeActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {

    //URL for the query
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query";

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quake_list);
        //progressBar of loading
        progressBar = findViewById(R.id.progress);

        //Check if there's internet, else show the not connection text
        if (isNetworkAvailable()) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(1, null, this);
        } else {
            progressBar.setVisibility(View.GONE);
            TextView textView = findViewById(R.id.not_internet);
            textView.setVisibility(View.VISIBLE);
        }

    }

    //creating the option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Menu main is a layout created
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //open the menu selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Check if setting was pressed
        if (id == R.id.actions_settings) {
            //Open a new activity
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void updateList(String jsonQuakes) {
        //Empty View for not found list
        TextView emptyTextView = findViewById(R.id.empty_view);
        emptyTextView.setText(R.string.not_quake_found);

        //Progress Bar not visible
        progressBar.setVisibility(View.GONE);

        // Find a reference to the ListView
        ListView quakeListView = findViewById(R.id.quake_list);
        //Set empty view to the list
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

    //Create the Loader (quake Loader thread)
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        //Getting the preference
        SharedPreferences sharedPreferences = PreferenceManager.
                getDefaultSharedPreferences(this);

        //min magnitude value
        String minMag = sharedPreferences.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));

        //order by value
        String orderBy = sharedPreferences.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default));

        //Quantity value
        String quakeNumber = sharedPreferences.getString(
                getString(R.string.settings_quake_number_key),
                getString(R.string.settings_quake_number_default));

        //Creating the URI to search the JSON
        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder quakeQuery = baseUri.buildUpon();

        quakeQuery.appendQueryParameter("format", "geojson");
        quakeQuery.appendQueryParameter("limit", quakeNumber);
        quakeQuery.appendQueryParameter("minmag", minMag);
        quakeQuery.appendQueryParameter("orderby", orderBy);
        //quakeQuery.appendQueryParameter("minlatitude","7.101");
        //quakeQuery.appendQueryParameter("maxlatitude","18.605");
        //quakeQuery.appendQueryParameter("minlongitude","-92.285");
        //quakeQuery.appendQueryParameter("maxlongitude","-77.036");
        //quakeQuery.appendQueryParameter("starttime","2018-01-01");
        //quakeQuery.appendQueryParameter("endtime","2018-12-31");

        Log.i("JAPM", quakeQuery.toString());
        return new QuakeLoader(this, quakeQuery.toString());
    }

    //Finish the loader
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String quakeList) {
        updateList(quakeList);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }

    //Function that check if there's connection or not
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
