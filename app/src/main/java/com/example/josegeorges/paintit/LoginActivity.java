package com.example.josegeorges.paintit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * This activity hosts the login system and takes care of checking the user status
 */
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

        //get the shared pref
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //we check to see if the user is logged in by checking the shared preferences
        boolean isLoggedIn = sharedPref.getBoolean(LoginFragment.USER_LOGGED_IN, false);
        if(isLoggedIn){
            //if is logged in, then we get it from the shared preferences,
            // so we can pass it an used it as before throughout the app
            int id = sharedPref.getInt(LoginActivity.USER_ID, 0);
            String email = sharedPref.getString(LoginActivity.USER_EMAIL, "");
            String fname = sharedPref.getString(LoginActivity.USER_FNAME, "");
            String lname = sharedPref.getString(LoginActivity.USER_LNAME, "");
            String password = sharedPref.getString(LoginActivity.USER_PASSWORD, "");
            String emailRecovery = sharedPref.getString(LoginActivity.USER_RECOVERY_EMAIL, "");
            String phone = sharedPref.getString(LoginActivity.USER_PHONE, "");

            //create the user
            User isUser = new User(id, fname, lname, email, password, emailRecovery, phone);

            //start the mainActivity
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra(LoginFragment.USER_LOGGED_IN, isUser);
            startActivity(i);
        }else {
            //if there is no user logged in, then display the fragment for the user to log in
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.login_content, new LoginFragment())
                    .commit();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /*
    Kill the activity and take it out of the history when back is pressed
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hideKeyboard();
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            Log.d("KEYBOARD", "Hiding keyboard");
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
