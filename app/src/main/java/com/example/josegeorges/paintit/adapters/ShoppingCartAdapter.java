package com.example.josegeorges.paintit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josegeorges.paintit.MainActivity;
import com.example.josegeorges.paintit.POJO.Item;
import com.example.josegeorges.paintit.ProfileFragment;
import com.example.josegeorges.paintit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by josegeorges on 2018-04-11.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder>{


    protected ArrayList<Item> list;
    private MainActivity activity;


    public ShoppingCartAdapter(ArrayList<Item> list, MainActivity activity) {
        this.list = list;
        this.activity = activity;
    }


    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view and inflate with item_layout.xml
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_cart_item, parent, false);
        final ShoppingCartViewHolder holder = new ShoppingCartViewHolder(view);




        return holder;
    }

    @Override
    public void onBindViewHolder(ShoppingCartViewHolder holder, int position) {
        //adding image using picasso
        Picasso.with(activity).load(ProfileFragment.loadImage(list.get(position).getImageView(), activity)).resize(60,
                80).centerCrop().into(holder.itemImage);
        holder.itemName.setText(list.get(position).getDescription());
        holder.itemPrice.setText("$" + String.valueOf(list.get(position).getPrice()));
        holder.itemDescription.setText(list.get(position).getDescription() + " (" + list.get(position).getSize() + " Gallons)");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ShoppingCartViewHolder extends RecyclerView.ViewHolder{

        protected ImageView itemImage;
        protected TextView itemName;
        protected TextView itemDescription;
        protected TextView itemPrice;


        public ShoppingCartViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.cart_image);
            itemName = itemView.findViewById(R.id.cart_item);
            itemDescription = itemView.findViewById(R.id.cart_item_description);
            itemPrice = itemView.findViewById(R.id.cart_item_price);
        }


    }


}
