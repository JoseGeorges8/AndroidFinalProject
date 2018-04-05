package com.example.josegeorges.paintit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    //key for when passing the logged user to the main Activity
    public static final String USER_LOGGED_IN = "user_logged_in";


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }


    //fields
    EditText emailEditText;
    EditText passwordEditText;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        //Sign as a user to the app
        emailEditText = view.findViewById(R.id.input_email);
        passwordEditText = view.findViewById(R.id.input_password);

        //when click login, check for valid user and if found log it in
        Button logInButton = view.findViewById(R.id.btn_login);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(getContext());
                User isUser = db.getUser(emailEditText.getText().toString(), passwordEditText.getText().toString());
                if (isUser != null){
                    //we state that the user is logged in.
                    // We make this so that we can check if the user is logged in or not to log it out
                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean(USER_LOGGED_IN, true);

                    //we add all the user information into the sharepreferences
                    //that way, even though we have it on the database we know which user to load when the app opens again
                    editor.putInt(LoginActivity.USER_ID, isUser.getUserID());
                    editor.putString(LoginActivity.USER_EMAIL, isUser.getEmail());
                    editor.putString(LoginActivity.USER_FNAME, isUser.getFirstName());
                    editor.putString(LoginActivity.USER_LNAME, isUser.getLastName());
                    editor.putString(LoginActivity.USER_PASSWORD, isUser.getPassword());
                    editor.putString(LoginActivity.USER_RECOVERY_EMAIL, isUser.getRecoveryEmail());
                    editor.putString(LoginActivity.USER_PHONE, isUser.getPhoneNumber());
                    editor.apply();

                    //open the main activity
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra(USER_LOGGED_IN, isUser);
                    startActivity(intent);
                }else{
                    //show the user that the credentials are wrong
                    Toast.makeText(getActivity(), "Wrong username or password", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });


        //open the register fragment
        TextView register = view.findViewById(R.id.link_signup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.login_content, new RegisterNameEmailFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });


        //sign as a guest to the app
        Button signAsGuest = view.findViewById(R.id.btn_guest);
        signAsGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
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
