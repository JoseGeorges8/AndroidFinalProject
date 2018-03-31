package com.example.josegeorges.paintit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by josegeorges on 2018-03-28.
 */

/**
 * This adapter will serve to display the favourite colors related to the user
 */
public class FavouriteColorsAdapter extends RecyclerView.Adapter<FavouriteColorsAdapter.FavouriteRecyclerViewHolder>{

    protected ArrayList<Color> list;


    public FavouriteColorsAdapter(ArrayList<Color> list) {
        this.list = list;
    }

    @Override
    public FavouriteRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_colours_items, parent, false);
        FavouriteRecyclerViewHolder holder = new FavouriteRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FavouriteRecyclerViewHolder holder, int position) {
        holder.colorValue.setBackgroundColor(list.get(position).getHexValue());
        holder.colorName.setText(list.get(position).getColorName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class FavouriteRecyclerViewHolder extends RecyclerView.ViewHolder{

        protected ImageView colorValue;
        protected TextView colorName;


        public FavouriteRecyclerViewHolder(View itemView) {
            super(itemView);
            colorValue = itemView.findViewById(R.id.color_imageView);
            colorName  = itemView.findViewById(R.id.favourite_color_name_textView);
        }


    }


}

