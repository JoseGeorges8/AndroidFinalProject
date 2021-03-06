package com.example.josegeorges.paintit.utils;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by josegeorges on 2018-04-05.
 */

public class InputValidator {

    //Fields needed to validate an email address
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public InputValidator(){

    }


    public boolean emailUsed(String email, Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        if(db.getUser(email)){
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    /**
     * This method checks if the email is a valid email address
     * @param email checking to validate
     * @return if matches with the pattern or not
     */
    public boolean validateEmail(String email){
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Checks equality of passwords
     * @param password password
     * @param repassword re entered passwords
     * @return
     */
    public boolean validatePasswordEquality(String password, String repassword){
        if(!password.equals(repassword)){
            return false;
        }
        return true;
    }

    /**
     * checks lenght of password
     * @param password
     * @return
     */
    public boolean validatePasswordLenght(String password){
        if(password.length() < 8){
            return false;
        }
        return true;
    }
}
