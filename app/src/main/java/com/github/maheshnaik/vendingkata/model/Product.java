package com.github.maheshnaik.vendingkata.model;


public class Product {

    private String name;
    private int cost;
    private int available;

    public Product(String name, int cost, int available) {
        this.name = name;
        this.cost = cost;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getAvailable() {
        return available;
    }

    public void updateAvailable(int available) {
        this.available = available;
    }
}


