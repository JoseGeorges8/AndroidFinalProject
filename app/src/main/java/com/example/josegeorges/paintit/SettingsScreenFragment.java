package com.example.josegeorges.paintit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by josegeorges on 2018-03-30.
 */

/**
 * Displays tthe settings for the app
 */
public class SettingsScreenFragment extends PreferenceFragment {

    //setting up toolbar
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //adds the xml file!
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_screen, container, false);

        //setting up an action bar
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
        setHasOptionsMenu(true);

        ListView lv =  view.findViewById(android.R.id.list);

        //log out the user
        Button logout = view.findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean(LoginFragment.USER_LOGGED_IN, false).apply();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });

        return view;
    }

    //this method takes care of changing the toolbar style
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_noicons, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
