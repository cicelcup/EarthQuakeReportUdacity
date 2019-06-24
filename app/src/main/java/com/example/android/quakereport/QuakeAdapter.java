package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
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

        //Circle behind the magnitude
        GradientDrawable magnitudeCircle = (GradientDrawable) textViewMagnitude.getBackground();

        //Check the color of the circle
        magnitudeCircle.setColor(formatMagColor(quake.getMagnitude()));

        //set the place text
        TextView textViewOffSet = currentView.findViewById(R.id.location_offset);
        textViewOffSet.setText(formatLocation(quake.getLocation())[0]);

        TextView textViewLocation = currentView.findViewById(R.id.location);
        textViewLocation.setText(formatLocation(quake.getLocation())[1]);

        //set the date text
        TextView textViewDate = currentView.findViewById(R.id.date);
        textViewDate.setText(formatDate(quake.getDate()));

        //Set the time text
        TextView textViewTime = currentView.findViewById(R.id.time);
        textViewTime.setText(formatTime(quake.getDate()));

        return currentView;
    }

    //color of background circle
    private int formatMagColor(float mag) {
        int minMag = (int) Math.floor(mag);
        int color;

        switch (minMag) {
            case 0:
            case 1:
                color = getContext().getResources().getColor(R.color.magnitude1);
                break;
            case 2:
                color = getContext().getResources().getColor(R.color.magnitude2);
                break;
            case 3:
                color = getContext().getResources().getColor(R.color.magnitude3);
                break;
            case 4:
                color = getContext().getResources().getColor(R.color.magnitude4);
                break;
            case 5:
                color = getContext().getResources().getColor(R.color.magnitude5);
                break;
            case 6:
                color = getContext().getResources().getColor(R.color.magnitude6);
                break;
            case 7:
                color = getContext().getResources().getColor(R.color.magnitude7);
                break;
            case 8:
                color = getContext().getResources().getColor(R.color.magnitude8);
                break;
            case 9:
                color = getContext().getResources().getColor(R.color.magnitude9);
                break;
            default:
                color = getContext().getResources().getColor(R.color.magnitude10plus);
                break;
        }
        return color;
    }

    /*Format the magnitude*/
    private String formatMag(float mag) {
        return String.format(Locale.US, "%.1f", mag);
    }

    /*format the date*/
    private String formatDate(Long dateQuake) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY", Locale.US);
        return dateFormat.format(new Date(dateQuake));
    }

    /*format the time*/
    private String formatTime(Long dateQuake) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a", Locale.US);
        return dateFormat.format(new Date(dateQuake));
    }

    /*format the location*/

    private String[] formatLocation(String location) {
        final String LOCATION_SEPARATOR = "of";

        String[] textLocation = new String[2];
        int positionOf = location.indexOf(LOCATION_SEPARATOR);

        //Found the of separation and split the line in two
        if (positionOf != -1) {
            textLocation[0] = location.substring(0, positionOf + 2).trim();
            textLocation[1] = location.substring(positionOf + 2).trim();
        } //Not found the location separation
        else {
            textLocation[0] = "Near the";
            textLocation[1] = location.trim();
        }
        return textLocation;
    }
}
