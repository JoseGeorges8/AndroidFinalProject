package com.example.josegeorges.paintit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This adapter will be used to display all the Stains and Finishes to the user
 */

public class StainsAdapter extends RecyclerView.Adapter<StainsAdapter.StainsRecyclerViewHolder>{

    protected ArrayList<Stains> list;


    public StainsAdapter(ArrayList<Stains> list) {
        this.list = list;
    }

    @Override
    public StainsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view and inflate with stains_item.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stains_item, parent, false);
        StainsRecyclerViewHolder holder = new StainsRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(StainsRecyclerViewHolder holder, int position) {
        holder.stainImage.setImageResource(list.get(position).getStainImageView());
        holder.stainName.setText(list.get(position).getStainName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class StainsRecyclerViewHolder extends RecyclerView.ViewHolder{

        protected ImageView stainImage;
        protected TextView stainName;


        public StainsRecyclerViewHolder(View itemView) {
            super(itemView);
            stainImage = itemView.findViewById(R.id.stain_imageView);
            stainName  = itemView.findViewById(R.id.stain_name);
        }


    }


}
