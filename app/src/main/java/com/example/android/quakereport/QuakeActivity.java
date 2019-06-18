/*Main activity of the project*/

package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class QuakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quake_list);

        // Find a reference to the ListView
        ListView quakeListView = findViewById(R.id.quake_list);

        // Create a new QuakeAdapter
        QuakeAdapter adapter = new QuakeAdapter(this,
                QuakeQuery.extractQuakes());

        // Set the adapter
        quakeListView.setAdapter(adapter);

        quakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Prueba", QuakeQuery.extractQuakes().get(position).getUrl());

                Intent openQuake = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        QuakeQuery.extractQuakes().get(position).getUrl()));
                startActivity(openQuake);
            }
        });
    }
}
