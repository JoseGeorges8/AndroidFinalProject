package com.example.josegeorges.paintit.POJO;

import java.util.ArrayList;

/**
 * Created by josegeorges on 2018-04-11.
 */

public class ShoppingCartList {

    private static ShoppingCartList intance = null;
    private double totalCost = 0;

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


    public void addCost(double itemCost){
        totalCost += itemCost;
    }

    public void removeCost(double itemCost){
        totalCost -= itemCost;
    }

    //getters and setters
    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public ArrayList<Item> getList() {
        return list;
    }

    public void setList(ArrayList<Item> list) {
        this.list = list;
    }
}
