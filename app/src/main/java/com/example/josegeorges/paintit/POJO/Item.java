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
    private long upc;
    private double price;
    private String description;
    private String name;
    private String imageName;
    private int size;
    private int itemTypeId;




    /*
    Constructors
     */
    public Item(long upc, double price, String imageName, int itemTypeId,  int size, String description) {
        this.upc = upc;
        this.price = price;
        this.description = description;
        this.imageName = imageName;
        this.size = size;
        this.itemTypeId = itemTypeId;
    }

    public Item(int itemID, long upc, double price, String imageName, int itemTypeId,  int size, String description) {
        this.itemID = itemID;
        this.upc = upc;
        this.price = price;
        this.description = description;
        this.imageName = imageName;
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

    public long getUpc() {
        return upc;
    }

    public void setUpc(long upc) {
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

    public String getImageView() {
        return imageName;
    }

    public void setImageView(String imageView) {
        this.imageName = imageView;
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
        dest.writeLong(this.upc);
        dest.writeDouble(this.price);
        dest.writeString(this.description);
        dest.writeString(this.name);
        dest.writeString(this.imageName);
        dest.writeInt(this.size);
        dest.writeInt(this.itemTypeId);
    }

    protected Item(Parcel in) {
        this.itemID = in.readInt();
        this.upc = in.readLong();
        this.price = in.readDouble();
        this.description = in.readString();
        this.name = in.readString();
        this.imageName = in.readString();
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
