package com.example.josegeorges.paintit;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

/**
 * Created by josegeorges on 2018-03-30.
 */

/**
 * Displays tthe settings for the app
 */
public class SettingsScreenFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //adds the xml file!
        addPreferencesFromResource(R.xml.preferences);
    }
}
