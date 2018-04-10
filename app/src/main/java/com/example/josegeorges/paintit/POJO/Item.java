package com.example.josegeorges.paintit.POJO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Keegan on 2018-03-25.
 */

public class Item implements Parcelable {

    /*
    Properties
     */

    private int itemID;
    private int upc;
    private double price;
    private String description;
    private String name;
    private int imageView;
    private int size;
    private int itemTypeId;

    /*
    Constructors
     */

    public Item(int upc, double price, String description, int imageView, int size, int itemTypeId) {
        this.upc = upc;
        this.price = price;
        this.description = description;
        this.imageView = imageView;
        this.size = size;
        this.itemTypeId = itemTypeId;
    }

    public Item(int itemID, int upc, double price, int itemTypeId, int size, String description) {
        this.itemID = itemID;
        this.upc = upc;
        this.price = price;
        this.description = description;
        this.size = size;
        this.itemTypeId = itemTypeId;
    }

    /*
            Getters and Setters
             */
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getUpc() {
        return upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(int itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.itemID);
        dest.writeInt(this.upc);
        dest.writeDouble(this.price);
        dest.writeString(this.description);
        dest.writeString(this.name);
        dest.writeInt(this.imageView);
        dest.writeInt(this.size);
        dest.writeInt(this.itemTypeId);
    }

    protected Item(Parcel in) {
        this.itemID = in.readInt();
        this.upc = in.readInt();
        this.price = in.readDouble();
        this.description = in.readString();
        this.name = in.readString();
        this.imageView = in.readInt();
        this.size = in.readInt();
        this.itemTypeId = in.readInt();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
