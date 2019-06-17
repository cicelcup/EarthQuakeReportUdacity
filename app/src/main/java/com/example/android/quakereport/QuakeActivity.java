/*Main activity of the project*/

package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    }
}
