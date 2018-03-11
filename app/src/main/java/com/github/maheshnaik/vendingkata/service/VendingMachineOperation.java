package com.github.maheshnaik.vendingkata.service;



public interface VendingMachineOperation {

    public final static int  DIME = 10;

    public final static int  QUARTERS = 25;

    public final static int  NICKELS = 5;


    public final static int  COLA_COST = 100;

    public final static int  CHIPS_COST = 50;

    public final static int  CANDY_COST = 65;

    public void insertCoins(int usc);

    public void buyCandy();

    public void buyCola();

    public void buyChips();

    public String getDisplayMessage();

    public int getMoneyReturned();
}
