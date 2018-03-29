package com.example.josegeorges.paintit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements RegisterFragment.OnFragmentInteractionListener,
            LoginFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_content, new LoginFragment())
                .addToBackStack(null)
                .commit();


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
