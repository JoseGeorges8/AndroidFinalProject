package com.example.josegeorges.paintit;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.josegeorges.paintit.POJO.User;
import com.example.josegeorges.paintit.adapters.SimpleFragmentPagerAdapter;
import com.example.josegeorges.paintit.utils.DatabaseHandler;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StoreFragment.OnFragmentInteractionListener,
            ProfileFragment.OnFragmentInteractionListener, FavouriteColorsFragment.OnFragmentInteractionListener,
            ItemListFragment.OnFragmentInteractionListener,
            ShoppingCartFragment.OnFragmentInteractionListener{

    //required elements


    private Toolbar toolbar; //action bat
    private ViewPager viewPager; //for swipe
    private SimpleFragmentPagerAdapter adapter; //adapter for the viewPager
    private FragmentManager fm; //FragmentManager needed for transactions
    private TabLayout tabs; //TabLayout to organize the navigation

    private FloatingActionMenu mainFab; //big main fab button
    private FloatingActionButton fabColour; //mini fab button for the colourPicker
    private FloatingActionButton fabPalette; //mini fab button for the palettePicker
    private List<FloatingActionMenu> menus = new ArrayList<>();
    private Handler mUiHandler = new Handler();

    private RecyclerView favouriteColoursRecyclerView; //max 3 colours to display

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(getIntent() != null) {
            user = getIntent().getParcelableExtra(LoginFragment.USER_LOGGED_IN);
            Log.d("USER", user.getEmail() + " logged in");
        }



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
        adapter = new SimpleFragmentPagerAdapter(fm, getApplicationContext(), user);
        viewPager.setAdapter(adapter);

        //setting up the tab layout
        tabs = findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);
        tabs.setBackgroundColor(getResources().getColor(R.color.cardsColor));
        tabs.setTabTextColors(getResources().getColor(R.color.colorAccentDark), getResources().getColor(R.color.colorAccent));


        //setting up the main fab button
        mainFab = (FloatingActionMenu)findViewById(R.id.main_fab);
        menus.add(mainFab);
        int delay = 400;
        for (final FloatingActionMenu menu : menus) {
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    menu.showMenuButton(true);
                }
            }, delay);
            delay += 150;
        }

        //link the mini fab button for the colour picker and when click open the activity.
        fabColour = (FloatingActionButton) findViewById(R.id.color_picker_fab);
        fabColour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ColorPickerActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);
            }
        });

        //link the mini fab button for the colour picker and when click open the activity.
        fabPalette = (FloatingActionButton) findViewById(R.id.palette_picker_fab);
        fabPalette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PalletePickerActivity.class);
                startActivity(intent);
            }
        });


        mainFab.setClosedOnTouchOutside(true);
        mainFab.hideMenuButton(false);
    }

    /*
    This adds the toolbar_icons file to the toolbar, which shows the settings symbol

    TODO: Add the cart icon to this xml file so it shows here
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_icons, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
    This method takes care of looking for which icon from the toolbar has been selected and run code depending on it

     TODO: Add the cart icon function to open the cart fragment
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_cart:
                if(user.getEmail().equals(LoginFragment.GUEST_EMAIL)){
                    //don't show settings to the guest user
                    new AlertDialog.Builder(this)
                            .setTitle("Access Denied")
                            .setMessage("This portion of the app is accessible only for logged in users!")
                            .show();
                }else{
                    //show settings if it's not the guest user
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_content, new ShoppingCartFragment())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            case R.id.action_settings:
                if(user.getEmail().equals(LoginFragment.GUEST_EMAIL)){
                    //don't show settings to the guest user
                    new AlertDialog.Builder(this)
                            .setTitle("Access Denied")
                            .setMessage("This portion of the app is accessible only for logged in users!")
                            .show();
                }else{
                    //show settings if it's not the guest user
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_content, SettingsScreenFragment.newInstance(user))
                            .addToBackStack(null)
                            .commit();
                }
             break;
            case R.id.action_about:
                new LibsBuilder()
                        .withLicenseShown(true)
                        .withActivityTitle("About Us")
                        .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                    .start(this);
                break;
            //log the user out
            case R.id.action_log_out:
                DatabaseHandler db = new DatabaseHandler(this);
                db.deleteAllItems();
                db.deleteAllTypes();
                db.close();
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean(LoginFragment.USER_LOGGED_IN, false).apply();
                Intent intent = new Intent(this, LoginActivity.class);
                finish();
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainFab.close(true);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



}
