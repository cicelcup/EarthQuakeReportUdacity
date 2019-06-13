package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {

    //Constructor for the earthquake
    public EarthQuakeAdapter(Context context, List<EarthQuake> earthQuakeList) {
        super(context, 0, earthQuakeList);
    }

    //get item of the adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View currentView = convertView;

        if (currentView == null) {
            //if it's not created, inflate the layout
            currentView = LayoutInflater.from(getContext()).
                    inflate(R.layout.earthquake_information, parent, false);
        }

        //get the current position from the adapter
        EarthQuake earthQuake = getItem(position);

        //Set the magnitude text
        TextView textViewMagnitude = currentView.findViewById(R.id.magnitude);
        textViewMagnitude.setText(Double.toString(earthQuake.getMagnitude()));

        //set the place text
        TextView textViewPlace = currentView.findViewById(R.id.place);
        textViewPlace.setText(earthQuake.getPlace());

        //set the date text
        TextView textViewDate = currentView.findViewById(R.id.date);
        textViewDate.setText(earthQuake.getDate());

        return currentView;
    }
}
