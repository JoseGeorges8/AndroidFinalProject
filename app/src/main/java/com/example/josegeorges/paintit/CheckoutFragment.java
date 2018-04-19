package com.example.josegeorges.paintit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.josegeorges.paintit.POJO.ShoppingCartList;

import java.util.Calendar;


public class CheckoutFragment extends Fragment {

    //hours chosen by the user
     public static int year = 0;
     public static int month = 0;
     public static int day = 0;
     public static int hour = 0;
     public static int minute = 0;


    public CheckoutFragment() {
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
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        TextView buttonTime = view.findViewById(R.id.set_time);
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
            }
        });

        buttonTime.setText(hour + " - " + minute);

        TextView buttonDate = view.findViewById(R.id.set_date);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });

        buttonDate.setText(year + " - " + month + " - " + day);


        // grab the total from the shopping cart and store it in subtotal in the checkout
        TextView subTotal = view.findViewById(R.id.subTotal);
        subTotal.setText(String.valueOf(ShoppingCartList.getIntance().getTotalCost()));

        TextView total = view.findViewById(R.id.total);
        total.setText("$" + (Double.parseDouble(subTotal.getText().toString()) * 1.13));






        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    /*
      Displays a window to set time for the user
       */
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            CheckoutFragment.hour = hourOfDay;
            CheckoutFragment.minute = minute;
            //This block of code makes sure that the change in the textview total is displayed in the shopping cart
            Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.main_content);
            if (currentFragment instanceof CheckoutFragment){
                FragmentTransaction fragTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragTransaction.detach(currentFragment);
                fragTransaction.attach(currentFragment);
                fragTransaction.commit();
            }

        }
    }


    /*
    Displays a window to set date for the user
     */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            CheckoutFragment.year = year;
            CheckoutFragment.month = month;
            CheckoutFragment.day = day;
            //This block of code makes sure that the change in the textview total is displayed in the shopping cart
            Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.main_content);
            if (currentFragment instanceof CheckoutFragment){
                FragmentTransaction fragTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragTransaction.detach(currentFragment);
                fragTransaction.attach(currentFragment);
                fragTransaction.commit();
            }
        }




    }

}
