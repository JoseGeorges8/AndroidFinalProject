package com.example.josegeorges.paintit;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Keegan on 2018-03-25.
 */

public class User implements Parcelable {

    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String recoveryEmail;
    private String phoneNumber;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String recoveryEmail, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.recoveryEmail = recoveryEmail;
        this.phoneNumber = phoneNumber;
    }

    public User(int userID, String firstName, String lastName, String email, String password, String recoveryEmail) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.recoveryEmail = recoveryEmail;
        this.phoneNumber = phoneNumber;
    }

    public User(int userID, String firstName, String lastName, String email, String password, String recoveryEmail, String phoneNumber) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.recoveryEmail = recoveryEmail;
        this.phoneNumber = phoneNumber;
    }



    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRecoveryEmail() {
        return recoveryEmail;
    }

    public void setRecoveryEmail(String recoveryEmail) {
        this.recoveryEmail = recoveryEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userID);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.recoveryEmail);
        dest.writeString(this.phoneNumber);
    }

    protected User(Parcel in) {
        this.userID = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.recoveryEmail = in.readString();
        this.phoneNumber = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
