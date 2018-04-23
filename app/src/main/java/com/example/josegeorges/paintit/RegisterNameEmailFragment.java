package com.example.josegeorges.paintit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.josegeorges.paintit.utils.InputValidator;

/**
 * Created by josegeorges on 2018-04-05.
 */

public class RegisterNameEmailFragment extends Fragment {



    //fields
    private EditText fnameEditText;
    private EditText lnameEditText;
    private EditText emailEditText;

    //buttons
    private Button cancelButton;
    private Button continueButton;


    //needed public constructor
    public RegisterNameEmailFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name_email_registration, container, false);

        //linking views
        fnameEditText = view.findViewById(R.id.register_firstname);
        lnameEditText = view.findViewById(R.id.register_lastname);
        emailEditText = view.findViewById(R.id.register_email);
        cancelButton = view.findViewById(R.id.cancel_button);
        continueButton = view.findViewById(R.id.next_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = fnameEditText.getText().toString();
                String lname = lnameEditText.getText().toString();
                String email = emailEditText.getText().toString();

                if(validateData(fname, lname, email)){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.slide_right, R.anim.slide_left, R.anim.slide_back_left, R.anim.slide_back_right)
                            .replace(R.id.login_content, RegisterPasswordFragment.newInstance(fname, lname, email))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return view;
    }


    //function checking that all the fields have been filled out and correctly
    public boolean validateData(String fname, String lname, String email){

        //checking if they are empty
        if(TextUtils.isEmpty(fname)){
            fnameEditText.setError("Missing Field");
            return false;
        }
        if(TextUtils.isEmpty(lname)){
            lnameEditText.setError("Missing Field");
            return false;
        }
        if(TextUtils.isEmpty(email)){
            emailEditText.setError("Missing Field");
            return false;
        }

        //checking for email validation
        InputValidator validator = new InputValidator();
        if(!validator.validateEmail(email)){
            emailEditText.setError("Invalid Email Address");
            return false;
        }

        if(validator.emailUsed(email, getContext())){
            emailEditText.setError("This email as already been taken");
            emailEditText.setText("");
            return false;
        }

        Log.d("SIGNUP", "Validation successful");
        return true;
    }
}
