package com.example.josegeorges.paintit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.Calendar;


/**
 * This activity is meant for the user to chose custom colors and add them to their favourites or order them
 */
public class ColorPickerActivity extends AppCompatActivity implements RGBFragment.OnFragmentInteractionListener {

    //properties needed

    private static final int REQUEST_CODE = 18;     //this request code is for when passing result from thr cameraActivity to this one
    int colorPickedByCameraValue = 0; //color picked by the cameraActivity
    int definitiveChosenColor = 0; //color saved when confirmed by user


    Toolbar toolbar; //custom Action Bar

    int redValue; //red value of the RGB
    int greenValue; //green value of the RGB
    int blueValue; //blue value of the RGB

    FloatingActionButton fab; //for launching the camera activity
    FrameLayout layout; //holds the color value that the user wants

    EditText colorName; //name of the color to save
    Button confirmButton; //button to confirm and save the color to the database

    User user; //user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);


        if(getIntent() != null){
            user = getIntent().getParcelableExtra("USER");
            Log.d("USER", user.getEmail() + " is picking a color");
        }

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

        //setting up the fab button
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ColorPickerActivity.this, CameraActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });


        //set up the frame for the color value
        layout = findViewById(R.id.frame_layout);

        //set up the EditText to get the name
        colorName = findViewById(R.id.color_picker_color_name_edit_text);

        //set up the confirmation button
        confirmButton = findViewById(R.id.color_picker_confirm_button);


        //default colors
        redValue = 0;
        greenValue = 0;
        blueValue = 0;


        //set up the view pager
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager()));


    }

    /**
     * This method gets called by the confirm button and it adds the color to the database.
     * @param view
     */
    public void addColor(View view){
        String name = colorName.getText().toString();
        int value = definitiveChosenColor;
        String currentTime = Calendar.getInstance().getTime().toString();
        Log.d("ADDCOLOR", "Color details: " + name + " " + value + " " + currentTime.toString());
        com.example.josegeorges.paintit.Color color = new com.example.josegeorges.paintit.Color(value, name, currentTime, user.getUserID());
        DatabaseHandler db = new DatabaseHandler(this);
        boolean  result = db.addColor(color);
            if (result) {
                Log.d("COLORPICKER", "Color successfully added on the db");
                boolean secondResult = db.addFavoriteColor(color, color.getUserId());
                if (secondResult) {
                    Log.d("COLORPICKER", "Color successfully added on the favourites table for user " + user.getEmail());
                } else {
                    Log.d("COLORPICKER", "Something went wrong, probably the user already has this color");
                }
                onBackPressed();
            } else {
                Log.d("COLORPICKER", "Something went wrong when adding the color");
                onBackPressed();
            }

    }

    /**
     *  Will get call when we receive results from an activity, in this case CameraActivity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (REQUEST_CODE) : {
                if (resultCode == Activity.RESULT_OK && data!=null) {
                    colorPickedByCameraValue = data.getIntExtra(CameraActivity.SELECTED_COLOR, 0);
                    definitiveChosenColor = colorPickedByCameraValue;
                    layout.setBackgroundColor(definitiveChosenColor);
                    layout.refreshDrawableState();
                }
                break;
            }
        }
    }

    /**
     * receive the values from the fragments and add them to the frame_layout
     */
    @Override
    public void onFragmentInteraction(int value, String key) {
        if(key == RGBFragment.RED)
            redValue = value;
        if(key == RGBFragment.GREEN)
            greenValue = value;
        if(key == RGBFragment.BLUE)
            blueValue = value;

        definitiveChosenColor = Color.rgb( redValue, greenValue, blueValue);
        layout.setBackgroundColor(definitiveChosenColor);
        layout.refreshDrawableState();
    }


    /**
     * Small simple custom adapter to show the different fragments
     */
    //TODO: As of right now I only have RGB. Create different fragments later
    private class CustomAdapter extends FragmentPagerAdapter {

        public CustomAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new RGBFragment();
                case 1:
                    return new RGBFragment();
                default:
                    return new RGBFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
