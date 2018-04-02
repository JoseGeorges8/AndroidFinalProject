package com.example.josegeorges.paintit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by josegeorges on 2018-03-30.
 */

/**
 * Displays tthe settings for the app
 */
public class SettingsScreenFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {



    //setting up toolbar
    private Toolbar toolbar;

    //SharedPref
    SharedPreferences sharedPref;

    public static final String USER = "user";
    private User user;

    /*
    So we can receive the user
     */
    public static SettingsScreenFragment newInstance(User user) {
        SettingsScreenFragment fragment = new SettingsScreenFragment();
        Bundle args = new Bundle();
        args.putParcelable(USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //adds the xml file!
        addPreferencesFromResource(R.xml.preferences);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        sharedPref.registerOnSharedPreferenceChangeListener(this);

        //set the user
        if(getArguments() != null){
            user = getArguments().getParcelable(USER);
        }
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

    /**
     *
     * this method will take care of updating the db has the settings change.
     *
     * @param sharedPreferences our default sharedpreferences
     * @param s the key of the item being changed
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        DatabaseHandler db = new DatabaseHandler(getActivity());
        int result;
        if(s.equals("pref_key_change_fname")){
            user.setFirstName(sharedPreferences.getString(s, ""));
            result = db.updateUser(user);
            if(result != -1){
                Toast.makeText(getActivity(), "User updated successfully, first name: " + user.getFirstName(), Toast.LENGTH_SHORT).show();
            }
        }else if(s.equals("pref_key_change_lname")){
            user.setLastName(sharedPreferences.getString(s, ""));
            result = db.updateUser(user);
            if(result != -1){
                Toast.makeText(getActivity(), "User updated successfully, last name: " + user.getLastName(), Toast.LENGTH_SHORT).show();
            }
        }else if(s.equals("pref_key_change_password")){
            user.setPassword(sharedPreferences.getString(s, ""));
            result = db.updateUser(user);
            if(result != -1){
                Toast.makeText(getActivity(), "User updated successfully, password: " + user.getPassword(), Toast.LENGTH_SHORT).show();
            }
        }else if(s.equals("pref_key_change_phone_number")){
            user.setPhoneNumber(sharedPreferences.getString(s, ""));
            result = db.updateUser(user);
            if(result != -1){
                Toast.makeText(getActivity(), "User updated successfully, phone number: " + user.getPhoneNumber(), Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }


}
