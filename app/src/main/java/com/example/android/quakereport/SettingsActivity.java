package com.example.android.quakereport;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

//Settings activity
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);//Set the fragment
    }

    //Class for fragment
    public static class QuakePreferenceFragment extends PreferenceFragment
            implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //Setting the preferences layout
            addPreferencesFromResource(R.xml.settings_main);

            //Get the location preference
            Preference location = findPreference(getString(R.string.settings_location_key));
            preferenceSummary(location);

            //Get the preference for minMag
            Preference minMag = findPreference(getString(R.string.settings_min_magnitude_key));
            preferenceSummary(minMag);

            //Get the preference for order by
            Preference orderBy = findPreference(getString(R.string.settings_order_by_key));
            preferenceSummary(orderBy);

            //Get the number of earthquakes
            Preference quakeNumber = findPreference(getString(R.string.settings_quake_number_key));
            preferenceSummary(quakeNumber);
        }

        @Override
        //Setting the value of the preference
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();
            preference.setSummary(stringValue); //create the value below of the name
            return true;
        }

        private void preferenceSummary(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            //Get the preference
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences
                    (preference.getContext()); //getting the preference context

            //get the value
            String preferenceString = preferences.getString(preference.getKey(), "");
            //Setting the value
            onPreferenceChange(preference, preferenceString);
        }


    }
}
