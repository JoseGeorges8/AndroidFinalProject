package com.example.josegeorges.paintit;

import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;


/**
 * This activity is meant for the user to chose custom colors and add them to their favourites or order them
 */
public class ColorPickerActivity extends AppCompatActivity implements RGBFragment.OnFragmentInteractionListener {

    //properties needed

    Toolbar toolbar; //custom Action Bar

    int redValue; //red value of the RGB
    int greenValue; //green value of the RGB
    int blueValue; //blue value of the RGB

    //TODO: camera activity
    FloatingActionButton fab; //for launching the camera activity
    FrameLayout layout; //holds the color value that the user wants
    ViewPager viewPager; //viewpager to show the fragments


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);


        //set up the toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //quit the activity when the user hits the X at the top left corner
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //set up the frame for the color value
        layout = findViewById(R.id.frame_layout);

        //default colors
        redValue = 0;
        greenValue = 0;
        blueValue = 0;


    }


    @Override
    public void onFragmentInteraction(int value, String key) {

    }
}
