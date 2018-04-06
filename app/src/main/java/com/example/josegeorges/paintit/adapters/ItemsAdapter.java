package com.example.josegeorges.paintit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josegeorges.paintit.POJO.Stains;
import com.example.josegeorges.paintit.R;

import java.util.ArrayList;

/**
 * This adapter will be used to display all the Stains and Finishes to the user
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.StainsRecyclerViewHolder>{

    protected ArrayList<Stains> list;


    public ItemsAdapter(ArrayList<Stains> list) {
        this.list = list;
    }

    @Override
    public StainsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view and inflate with item_layout.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        StainsRecyclerViewHolder holder = new StainsRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(StainsRecyclerViewHolder holder, int position) {
        holder.itemImage.setImageResource(list.get(position).getStainImageView());
        holder.itemName.setText(list.get(position).getStainName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class StainsRecyclerViewHolder extends RecyclerView.ViewHolder{

        protected ImageView itemImage;
        protected TextView itemName;


        public StainsRecyclerViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_imageView);
            itemName = itemView.findViewById(R.id.item_name);
        }


    }


}
