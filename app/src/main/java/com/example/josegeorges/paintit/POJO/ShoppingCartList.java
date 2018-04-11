package com.example.josegeorges.paintit.POJO;

import java.util.ArrayList;

/**
 * Created by josegeorges on 2018-04-11.
 */

public class ShoppingCartList {

    private static ShoppingCartList intance = null;

    ArrayList<Item> list = new ArrayList<>();

    private ShoppingCartList(){
        if (intance == null){

        }
    }


    public static ShoppingCartList getIntance(){
        if (intance == null)
            intance = new ShoppingCartList();
        return intance;
    }

    public void finish(){
        list.clear();
    }

    //getters and setters
    public ArrayList<Item> getList() {
        return list;
    }

    public void setList(ArrayList<Item> list) {
        this.list = list;
    }
}
