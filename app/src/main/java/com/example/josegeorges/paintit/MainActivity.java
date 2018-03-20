package com.example.josegeorges.paintit;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.josegeorges.paintit.utils.SimpleFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity implements StoreFragment.OnFragmentInteractionListener,
            ProfileFragment.OnFragmentInteractionListener{

    //required elements

    private Toolbar toolbar; //action bat
    private ViewPager viewPager; //for swipe
    private SimpleFragmentPagerAdapter adapter; //adapter for the viewPager
    private FragmentManager fm; //FragmentManager needed for transactions
    private TabLayout tabs; //TabLayout to organize the navigation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get fragment manager
        fm = getSupportFragmentManager();

        //setting up an action bar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        //setting up the view pager
        viewPager = findViewById(R.id.view_pager);
        adapter = new SimpleFragmentPagerAdapter(fm, getApplicationContext());
        viewPager.setAdapter(adapter);

        //setting up the tab layout
        tabs = findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);
        tabs.setBackgroundColor(getResources().getColor(R.color.cardsColor));
        tabs.setTabTextColors(getResources().getColor(R.color.colorAccentDark), getResources().getColor(R.color.colorAccent));


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
