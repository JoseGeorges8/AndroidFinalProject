package com.example.josegeorges.paintit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by josegeorges on 2018-04-05.
 */

public class RegisterNameEmailFragment extends Fragment {


    //needed public constructor
    public RegisterNameEmailFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name_email_registration, container, false);



        return view;
    }
}
