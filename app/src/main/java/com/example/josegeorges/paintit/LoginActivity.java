package com.example.josegeorges.paintit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements RegisterFragment.OnFragmentInteractionListener,
            LoginFragment.OnFragmentInteractionListener{

    //keys for user sharedPref
    public static final String USER_EMAIL = "EMAIL";
    public static final String USER_FNAME = "FNAME";
    public static final String USER_LNAME = "LNAME";
    public static final String USER_PASSWORD = "PASSWORD";
    public static final String USER_ID = "USERID";
    public static final String USER_RECOVERY_EMAIL = "RECOVERYEMAIL";
    public static final String USER_PHONE = "PHONENUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean isLoggedIn = sharedPref.getBoolean(LoginFragment.USER_LOGGED_IN, false);
        if(isLoggedIn){
            int id = sharedPref.getInt(LoginActivity.USER_ID, 0);
            String email = sharedPref.getString(LoginActivity.USER_EMAIL, "");
            String fname = sharedPref.getString(LoginActivity.USER_FNAME, "");
            String lname = sharedPref.getString(LoginActivity.USER_LNAME, "");
            String password = sharedPref.getString(LoginActivity.USER_PASSWORD, "");
            String emailRecovery = sharedPref.getString(LoginActivity.USER_RECOVERY_EMAIL, "");
            String phone = sharedPref.getString(LoginActivity.USER_PHONE, "");

            User isUser = new User(id, fname, lname, email, password, emailRecovery, phone);


            Intent i = new Intent(this, MainActivity.class);
            i.putExtra(LoginFragment.USER_LOGGED_IN, isUser);
            startActivity(i);
        }else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.login_content, new LoginFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
