package com.example.josegeorges.paintit;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.Toast;

import com.example.josegeorges.paintit.POJO.Item;
import com.example.josegeorges.paintit.POJO.Order;
import com.example.josegeorges.paintit.POJO.ShoppingCartList;
import com.example.josegeorges.paintit.utils.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import es.dmoral.toasty.Toasty;


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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
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

        buttonTime.setText(hour + ":" + minute);

        final TextView buttonDate = view.findViewById(R.id.set_date);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });

        buttonDate.setText(year + "/" + month + "/" + day);


        // grab the total from the shopping cart and store it in subtotal in the checkout
        TextView subTotal = view.findViewById(R.id.subTotal);
        subTotal.setText(String.valueOf(ShoppingCartList.getIntance().getTotalCost()));

        TextView total = view.findViewById(R.id.total);
        total.setText("$" + Math.ceil((Double.parseDouble(subTotal.getText().toString()) * 1.13 )));

        // Grab from the shared preferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext());

        // Grab the user information from shared preferences
        final int user_id = sharedPref.getInt(LoginActivity.USER_ID, 0);
        String email = sharedPref.getString(LoginActivity.USER_EMAIL, "");
        String fname = sharedPref.getString(LoginActivity.USER_FNAME, "");
        String lname = sharedPref.getString(LoginActivity.USER_LNAME, "");
        String phone = sharedPref.getString(LoginActivity.USER_PHONE, "");
        String fullName = fname + " " + lname;

        TextView displayName = view.findViewById(R.id.name);
        displayName.setText(fullName);

        TextView displayEmail = view.findViewById(R.id.email);
        displayEmail.setText(email);

        TextView displayPhoneNumber = view.findViewById(R.id.phoneNumber);
        displayPhoneNumber.setText(phone);

        Button backToCartButton = view.findViewById(R.id.backToCart_button);
        backToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        Button confirmOrderButton = view.findViewById(R.id.confirmOrder_button);
        confirmOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //we get today's date
                Date c = Calendar.getInstance().getTime();
                String currentDate = (c.getYear() + 1900) + "/" + (c.getMonth() +1) + "/" + c.getDate();

                //we first check for a valid date
                if(year <= c.getYear() + 1900){
                    if(month <= c.getMonth() +1){
                        if(day <= c.getDate()){
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Invalid Date")
                                    .setMessage("Please choose a date after today")
                                    .show();
                            return;
                        }
                    }
                }

                // Generate a random 10 digit number and cast it to a string for the order number
                long orderNumber = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
                String orderNumberAsString = Long.toString(orderNumber);

                //create the order and add it to the orders table
                Order newOrder = new Order(orderNumberAsString, currentDate, buttonDate.getText().toString(), user_id);
                DatabaseHandler db = new DatabaseHandler(getActivity());
                boolean result = db.addOrder(newOrder);
                if(result){
                    //add the items and the order to the orders/items table and clear the shopping cart
                    for (Item item : ShoppingCartList.getIntance().getList()){
                        result = db.addOrderItemId(newOrder.getOrderID(), item.getItemID());
                        if(result){
                            System.out.print("added to the table");
                        }else{
                            Toast.makeText(getActivity(), "Something went wrong with the order/items table", Toast.LENGTH_LONG)
                                    .show();
                            db.close();
                            return;
                        }
                    }
                    Toasty.success(getActivity(), "Order processed!", Toast.LENGTH_SHORT, true).show();
                    ShoppingCartList.getIntance().finish();
                    getActivity().getSupportFragmentManager().popBackStack();
                    getActivity().getSupportFragmentManager().popBackStack();
                }else{
                    Toast.makeText(getActivity(), "Something went wrong with the order", Toast.LENGTH_LONG)
                            .show();
                    db.close();
                    return;
                }
                db.close();

            }
        });




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
            CheckoutFragment.month = month + 1;
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
