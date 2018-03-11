package com.github.maheshnaik.vendingkata.model;


import com.github.maheshnaik.vendingkata.event.UpdateVendingMachine;
import com.github.maheshnaik.vendingkata.service.VendingMachineOperation;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


public class VendingMachineManager implements VendingMachineOperation {

    protected String displayMessage;
    protected int totalInsertedMoney = 0;
    protected int moneyReturned;

    protected final String ItemDispatched = "Item Dispatched";
    protected final String SoldOut = "Sold Out";
    protected final String InsertMoreCoins = "Insert Coins";
    protected final String InsertValid = "Insert Valid Coins only";


    private List<Product> stock;

    public VendingMachineManager() {
        stock = new ArrayList<>();
        stock.add(new Product("Cola", COLA_COST, 1));
        stock.add(new Product("Candy", CANDY_COST, 10));
        stock.add(new Product("Chip", CHIPS_COST, 100));
    }

    public VendingMachineManager(List<Product> stock) {
        this.stock = stock;
    }

    @Override
    public void insertCoins(int usc) {
        switch (usc) {
            case DIME:
            case QUARTERS:
            case NICKELS:
                totalInsertedMoney = totalInsertedMoney + usc;
                displayMessage = totalInsertedMoney + "";
                break;
            default:
                displayMessage = InsertValid;
        }
        EventBus.getDefault().post(new UpdateVendingMachine());
    }

    @Override
    public String getDisplayMessage() {
        return displayMessage;
    }

    @Override
    public int getMoneyReturned() {
        return moneyReturned;
    }

    @Override
    public void buyCola() {
        tryBuyProduct(0);

    }

    @Override
    public void buyCandy() {
        tryBuyProduct(1);
    }

    @Override
    public void buyChips() {
        tryBuyProduct(2);
    }

    private void tryBuyProduct(int product) {
        Product item = stock.get(product);
        int available = item.getAvailable();
        if (available <= 0) {
            displayMessage = SoldOut;
            moneyReturned = totalInsertedMoney;
            totalInsertedMoney = 0;
        } else if (totalInsertedMoney >= item.getCost()) {
            displayMessage = ItemDispatched;
            moneyReturned = totalInsertedMoney - item.getCost();
            totalInsertedMoney = 0;
            item.updateAvailable(available - 1);
        } else {
            displayMessage = InsertMoreCoins;
        }
        EventBus.getDefault().post(new UpdateVendingMachine());
    }


}
