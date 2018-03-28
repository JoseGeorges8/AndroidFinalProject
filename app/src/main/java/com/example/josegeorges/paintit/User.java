package com.example.josegeorges.paintit;

/**
 * Created by Keegan on 2018-03-25.
 */

public class User {

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
}
