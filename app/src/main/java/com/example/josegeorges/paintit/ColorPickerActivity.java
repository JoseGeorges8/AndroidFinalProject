package com.example.josegeorges.paintit;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 * This activity is meant for the user to chose custom colors and add them to their favourites or order them
 */
public class ColorPickerActivity extends AppCompatActivity implements RGBFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
