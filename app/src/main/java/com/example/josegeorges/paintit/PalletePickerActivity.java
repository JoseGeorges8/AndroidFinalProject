package com.example.josegeorges.paintit;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.josegeorges.paintit.utils.PaletteRecyclerViewAdapter;

public class PalletePickerActivity extends AppCompatActivity implements RGBFragment.OnFragmentInteractionListener{

    //properties needed

    Toolbar toolbar; //custom Action Bar

    int redValue; //red value of the RGB
    int greenValue; //green value of the RGB
    int blueValue; //blue value of the RGB

    ViewPager viewPager; //viewpager to show the fragments
    RecyclerView recyclerView; //the recycler view to display all the colors from the palette
    FloatingActionButton fab; //for launching the camera activity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pallete_picker);


        //default colors
        redValue = 0;
        greenValue = 0;
        blueValue = 0;


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

        //recycler view of colors
        recyclerView = findViewById(R.id.palette_colors_recycler_view);
        //setting up the layoutManager
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getApplicationContext()){
            @Override
            public boolean supportsPredictiveItemAnimations() {

                return true;

            }
        };
        myLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setAdapter(new PaletteRecyclerViewAdapter());
        //item animator controls how animations look. we could make our custom version
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        //set up the view pager
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onFragmentInteraction(int value, String key) {
        if(key == RGBFragment.RED)
            redValue = value;
        if(key == RGBFragment.GREEN)
            greenValue = value;
        if(key == RGBFragment.BLUE)
            blueValue = value;
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
