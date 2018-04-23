package com.example.josegeorges.paintit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josegeorges.paintit.ItemDetailsFragment;
import com.example.josegeorges.paintit.MainActivity;
import com.example.josegeorges.paintit.POJO.Item;
import com.example.josegeorges.paintit.ProfileFragment;
import com.example.josegeorges.paintit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * This adapter will be used to display all the Items to the user
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsRecyclerViewHolder>{

    protected ArrayList<Item> list;
    private Context context;


    public ItemsAdapter(ArrayList<Item> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ItemsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view and inflate with item_layout.xml
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        final ItemsRecyclerViewHolder holder = new ItemsRecyclerViewHolder(view);

        //if an item is clicked, open the detailsFragment
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) context;
                activity.getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_right, R.anim.slide_left, R.anim.slide_back_left, R.anim.slide_back_right)
                        .replace(R.id.main_content, ItemDetailsFragment.newInstance(list.get(holder.getAdapterPosition())))
                        .addToBackStack(null)
                        .commit();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemsRecyclerViewHolder holder, final int position) {
        holder.itemName.setText(list.get(position).getDescription());
        //adding image using picasso
        Picasso.with(context).load(ProfileFragment.loadImage(list.get(position).getImageView(), (MainActivity) context)).resize(60,
                80).centerCrop().into(holder.itemImage);
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
