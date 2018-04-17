package com.example.josegeorges.paintit.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.josegeorges.paintit.MainActivity;
import com.example.josegeorges.paintit.POJO.Item;
import com.example.josegeorges.paintit.POJO.ShoppingCartList;
import com.example.josegeorges.paintit.ProfileFragment;
import com.example.josegeorges.paintit.R;
import com.example.josegeorges.paintit.utils.DatabaseHandler;
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
    public void onBindViewHolder(final ShoppingCartViewHolder holder, int position) {
        //adding image using picasso
        Picasso.with(activity).load(ProfileFragment.loadImage(list.get(position).getImageView(), activity)).resize(60,
                80).centerCrop().into(holder.itemImage);
        holder.itemName.setText(list.get(position).getDescription());
        holder.itemPrice.setText("$" + String.valueOf(list.get(position).getPrice()));
        holder.itemDescription.setText(list.get(position).getDescription() + " (" + list.get(position).getSize() + " Gallons)");

        holder.itemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(activity)
                        .setTitle("Delete Color")
                        .setMessage("Are you sure you want to delete this color?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //get the selected item
                                int color = holder.getAdapterPosition();

                                //get the shopping cart
                                ShoppingCartList shoppingCartList = ShoppingCartList.getIntance();
                                //delete from the shopping cart list
                                shoppingCartList.getList().remove(color);
                                //notify recycler view
                                notifyItemRemoved(color);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
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
        protected ImageButton itemDelete;


        public ShoppingCartViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.cart_image);
            itemName = itemView.findViewById(R.id.cart_item);
            itemDescription = itemView.findViewById(R.id.cart_item_description);
            itemPrice = itemView.findViewById(R.id.cart_item_price);
            itemDelete = itemView.findViewById(R.id.remove_from_cart);
        }


    }


}
