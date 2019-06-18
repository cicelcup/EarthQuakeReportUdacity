package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
                    inflate(R.layout.quake_information, parent, false);
        }

        //get the current position from the adapter
        Quake quake = getItem(position);

        //Set the magnitude text
        TextView textViewMagnitude = currentView.findViewById(R.id.magnitude);
        textViewMagnitude.setText(formatMag(quake.getMagnitude()));

        //set the place text
        TextView textViewOffSet = currentView.findViewById(R.id.location_offset);
        textViewOffSet.setText(formatLocation(quake.getLocation())[0]);

        TextView textViewLocation = currentView.findViewById(R.id.location);
        textViewLocation.setText(formatLocation(quake.getLocation())[1]);

        //set the date text
        TextView textViewDate = currentView.findViewById(R.id.date);
        textViewDate.setText(formatDate(quake.getDate()));

        TextView textViewTime = currentView.findViewById(R.id.time);
        textViewTime.setText(formatTime(quake.getDate()));

        return currentView;
    }

    /*Format the magnitude*/
    private String formatMag(float mag) {
        String floatMagnitude = String.format(
                Locale.getDefault(), "%.1f", mag);
        return floatMagnitude;
    }

    /*format the date*/
    private String formatDate(Long dateQuake) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY");
        String date = dateFormat.format(new Date(dateQuake));
        return date;
    }

    /*format the time*/
    private String formatTime(Long dateQuake) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        String date = dateFormat.format(new Date(dateQuake));
        return date;
    }

    /*format the location*/

    private String[] formatLocation(String location) {
        final String LOCATION_SEPARATOR = "of";

        String[] textLocation = new String[2];
        int positionOf = location.indexOf(LOCATION_SEPARATOR);

        if (positionOf != -1) {
            textLocation[0] = location.substring(0, positionOf + 2).trim();
            textLocation[1] = location.substring(positionOf + 2).trim();
        } else {
            textLocation[0] = "Near the";
            textLocation[1] = location.trim();
        }
        return textLocation;
    }
}
