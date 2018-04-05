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
import android.widget.Toast;

import com.example.josegeorges.paintit.utils.InputValidator;

/**
 * Created by josegeorges on 2018-04-05.
 */

public class RegisterRecoveryFragment extends Fragment {


    //values from last fragment
    String fname;
    String lname;
    String email;
    String password;

    //Keys to receive those values
    public static final String F_NAME = "fname";
    public static final String L_NAME = "lname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";


    //fields
    private EditText recoveryemailEditText;
    private EditText phoneEditText;

    //buttons
    private Button cancelButton;
    private Button continueButton;


    //needed public constructor
    public RegisterRecoveryFragment(){

    }

    /**
     * receiving data from last fragment
     * @param fname first name
     * @param lname last name
     * @param email email address
     * @param password password
     * @return
     */
    public static RegisterRecoveryFragment newInstance(String fname, String lname, String email, String password) {
        RegisterRecoveryFragment fragment = new RegisterRecoveryFragment();
        Bundle args = new Bundle();
        args.putString(F_NAME, fname);
        args.putString(L_NAME, lname);
        args.putString(EMAIL, email);
        args.putString(PASSWORD, password);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recovery_registration, container, false);

        //linking views
        recoveryemailEditText = view.findViewById(R.id.register_recovery_email);
        phoneEditText = view.findViewById(R.id.register_phone_number);
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

                String recoveryemail = recoveryemailEditText.getText().toString();
                String phone = phoneEditText.getText().toString();

                if(validateData(recoveryemail)){
                    Log.d("SIGNUP", "all credentials where checked, you can now add the user");
                    Toast.makeText(getContext(), "all credentials where checked, you can now add the use", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


    //function checking that all the fields have been filled out and correctly
    public boolean validateData(String recoveryemail){

        //checking if they are empty
        if(TextUtils.isEmpty(recoveryemail)){
            recoveryemailEditText.setError("Missing Field");
            return false;
        }

        //checking for email validation
        InputValidator validator = new InputValidator();
        if(!validator.validateEmail(recoveryemail)){
            recoveryemailEditText.setError("Invalid Email Address");
            return false;
        }

        Log.d("SIGNUP", "Validation successful");
        return true;
    }
}
