package com.example.josegeorges.paintit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josegeorges.paintit.POJO.Item;
import com.example.josegeorges.paintit.R;

import java.util.ArrayList;

/**
 * This adapter will be used to display all the Items to the user
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsRecyclerViewHolder>{

    protected ArrayList<Item> list;


    public ItemsAdapter(ArrayList<Item> list) {
        this.list = list;
    }

    @Override
    public ItemsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view and inflate with item_layout.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        ItemsRecyclerViewHolder holder = new ItemsRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemsRecyclerViewHolder holder, int position) {
        holder.itemImage.setImageResource(list.get(position).getImageView());
        holder.itemName.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ItemsRecyclerViewHolder extends RecyclerView.ViewHolder{

        protected ImageView itemImage;
        protected TextView itemName;


        public ItemsRecyclerViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_imageView);
            itemName = itemView.findViewById(R.id.item_name);
        }


    }


}
