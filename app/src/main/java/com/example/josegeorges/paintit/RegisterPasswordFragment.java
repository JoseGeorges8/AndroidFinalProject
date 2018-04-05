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

public class RegisterPasswordFragment extends Fragment {

    //values from last fragment
    String fname;
    String lname;
    String email;

    //Keys to receive those values
    public static final String F_NAME = "fname";
    public static final String L_NAME = "lname";
    public static final String EMAIL = "email";


    //fields
    private EditText passwordEditText;
    private EditText repasswordEditText;

    //buttons
    private Button cancelButton;
    private Button continueButton;


    //needed public constructor
    public RegisterPasswordFragment(){

    }

    /**
     * receiving data from last fragment
     * @param fname first name
     * @param lname last name
     * @param email email address
     * @return
     */
    public static RegisterPasswordFragment newInstance(String fname, String lname, String email) {
        RegisterPasswordFragment fragment = new RegisterPasswordFragment();
        Bundle args = new Bundle();
        args.putString(F_NAME, fname);
        args.putString(L_NAME, lname);
        args.putString(EMAIL, email);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password_registration, container, false);

        //linking views
        passwordEditText = view.findViewById(R.id.register_password);
        repasswordEditText = view.findViewById(R.id.register_password_repeat);
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
                String password = passwordEditText.getText().toString();
                String repassword = repasswordEditText.getText().toString();

                if(validateData(password, repassword)){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.login_content, new RegisterNameEmailFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return view;
    }


    //function checking that all the fields have been filled out and correctly
    public boolean validateData(String password, String repassword){

        //checking if they are empty
        if(TextUtils.isEmpty(password)){
            passwordEditText.setError("Missing Field");
            return false;
        }
        if(TextUtils.isEmpty(repassword)){
            repasswordEditText.setError("Missing Field");
            return false;
        }


        //checking for password equality
        InputValidator validator = new InputValidator();
        if(!validator.validatePasswordEquality(password, repassword)){
            repasswordEditText.setError("Passwords does not match");
            passwordEditText.setText("");
            repasswordEditText.setText("");
            return false;
        }

        //checking lenght of password
        if(!validator.validatePasswordLenght(password)){
            passwordEditText.setError("Password is too short");
            passwordEditText.setText("");
            repasswordEditText.setText("");
            return false;
        }

        Log.d("SIGNUP", "Validation successful");
        return true;
    }
}
