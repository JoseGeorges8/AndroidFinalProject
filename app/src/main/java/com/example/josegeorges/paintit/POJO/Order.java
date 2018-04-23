package com.example.josegeorges.paintit.POJO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Keegan on 2018-03-25.
 */

public class Order implements Parcelable {
    private int orderID;
    private String orderNumber;
    private String dateOrdered;
    private String pickUpDate;
    private int userID;

    public Order() {
    }

    public Order(int orderID, String orderNumber, String dateOrdered, String pickUpDate, int userID) {
        this.orderID = orderID;
        this.orderNumber = orderNumber;
        this.dateOrdered = dateOrdered;
        this.pickUpDate = pickUpDate;
        this.userID = userID;
    }

    public Order(String orderNumber, String dateOrdered, String pickUpDate, int userID) {
        this.orderNumber = orderNumber;
        this.dateOrdered = dateOrdered;
        this.pickUpDate = pickUpDate;
        this.userID = userID;
    }


    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.orderID);
        dest.writeString(this.orderNumber);
        dest.writeString(this.dateOrdered);
        dest.writeString(this.pickUpDate);
        dest.writeInt(this.userID);
    }

    protected Order(Parcel in) {
        this.orderID = in.readInt();
        this.orderNumber = in.readString();
        this.dateOrdered = in.readString();
        this.pickUpDate = in.readString();
        this.userID = in.readInt();
    }

    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
