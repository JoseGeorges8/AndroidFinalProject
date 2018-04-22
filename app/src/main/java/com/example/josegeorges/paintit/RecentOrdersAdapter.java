package com.example.josegeorges.paintit;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josegeorges.paintit.POJO.Color;
import com.example.josegeorges.paintit.POJO.Order;
import com.example.josegeorges.paintit.utils.DatabaseHandler;

import java.util.ArrayList;

/**
 * This adapter will serve to display the favourite colors related to the user
 */
public class RecentOrdersAdapter extends RecyclerView.Adapter<RecentOrdersAdapter.RecentOrdersRecyclerViewHolder>{

    protected ArrayList<Order> list;
    private Context context;

    public RecentOrdersAdapter(ArrayList<Order> list) {
        this.list = list;
    }

    @Override
    public RecentOrdersRecyclerViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_orders_layout, parent, false);
        final RecentOrdersRecyclerViewHolder holder = new RecentOrdersRecyclerViewHolder(view);
        context = parent.getContext();


        return holder;
    }

    @Override
    public void onBindViewHolder(RecentOrdersRecyclerViewHolder holder, int position) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext());

        // Grab the user information from shared preferences
        String email = sharedPref.getString(LoginActivity.USER_EMAIL, "");
        String fname = sharedPref.getString(LoginActivity.USER_FNAME, "");
        String lname = sharedPref.getString(LoginActivity.USER_LNAME, "");
        String fullName = fname + " " + lname;


        holder.orderNumberText.setText("Order #  ");
        holder.orderNumber.setText(list.get(position).getOrderNumber());
        holder.userID.setText(fullName);
        holder.dateOrdered.setText(list.get(position).getDateOrdered());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class RecentOrdersRecyclerViewHolder extends RecyclerView.ViewHolder{

        protected TextView orderNumberText;
        protected TextView orderNumber;
        protected TextView userID;
        protected TextView dateOrdered;



        public RecentOrdersRecyclerViewHolder(View itemView) {
            super(itemView);
            orderNumberText = itemView.findViewById(R.id.order_numberText);
            orderNumber  = itemView.findViewById(R.id.order_number);
            userID  = itemView.findViewById(R.id.userID);
            dateOrdered  = itemView.findViewById(R.id.date_ordered);
        }


    }


}

