package com.example.josegeorges.paintit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.josegeorges.paintit.POJO.User;
import com.example.josegeorges.paintit.utils.DatabaseHandler;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    //Views
    private ImageView cancel;
    private EditText fnameEditText;
    private EditText lnameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText recoveryEmailEditText;
    private EditText numberEditText;
    private Button register;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        //to close the fragment
        cancel = view.findViewById(R.id.register_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


        //edit text fields
        fnameEditText = view.findViewById(R.id.register_fname);
        lnameEditText = view.findViewById(R.id.register_lname);
        emailEditText = view.findViewById(R.id.register_email);
        passwordEditText = view.findViewById(R.id.register_password);
        recoveryEmailEditText = view.findViewById(R.id.register_recovery_email);
        numberEditText = view.findViewById(R.id.register_number);


        register = view.findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fname = fnameEditText.getText().toString();
                String lname = lnameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String recoveryEmail = recoveryEmailEditText.getText().toString();
                String number = numberEditText.getText().toString();

                if (validateData(fname, lname, email, password, recoveryEmail, number)) {
                    User newUser = new User(fname, lname, email, password, recoveryEmail, number);
                    DatabaseHandler db = new DatabaseHandler(getContext());
                    Boolean result = db.addUser(newUser);
                    if (result) {
                        Toast.makeText(getActivity(), "User successfully created", Toast.LENGTH_LONG);
                        Log.d("DATABASE", "User successfully created");
                    } else {
                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG);
                        Log.d("DATABASE", "Something went wrong");
                    }
                }
                getActivity().onBackPressed();
            }
        });


        return view;
    }

    //function checking that all the fields have been filled out and correctly
    public boolean validateData(String fname, String lname, String email, String password, String recoveryEmail, String number){

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
        if(TextUtils.isEmpty(password)){
            passwordEditText.setError("Missing Field");
            return false;
        }if(TextUtils.isEmpty(recoveryEmail)){
            recoveryEmailEditText.setError("Missing Field");
            return false;
        }
        Log.d("SIGNUP", "Validation successful");
        return true;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
