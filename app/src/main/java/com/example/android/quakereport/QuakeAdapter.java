package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class QuakeAdapter extends ArrayAdapter<Quake> {

    //Constructor for the earthquake
    QuakeAdapter(Context context, List<Quake> earthQuakeList) {
        super(context, 0, earthQuakeList);
    }

    //get item of the adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View currentView = convertView;

        if (currentView == null) {
            //if it's not created, inflate the layout
            currentView = LayoutInflater.from(getContext()).
                    inflate(R.layout.quake_information_test2, parent, false);
        }

        //get the current position from the adapter
        Quake quake = getItem(position);

        //Set the magnitude text
        TextView textViewMagnitude = currentView.findViewById(R.id.magnitude);
        textViewMagnitude.setText(Double.toString(quake.getMagnitude()));

        //set the place text
        TextView textViewPlace = currentView.findViewById(R.id.location);
        textViewPlace.setText(quake.getLocation());

        //set the date text
        TextView textViewDate = currentView.findViewById(R.id.date);
        textViewDate.setText(quake.getDate());

        return currentView;
    }
}
