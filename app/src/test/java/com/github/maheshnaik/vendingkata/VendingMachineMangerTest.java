package com.github.maheshnaik.vendingkata;

import com.github.maheshnaik.vendingkata.model.Product;
import com.github.maheshnaik.vendingkata.model.VendingMachineManager;
import com.github.maheshnaik.vendingkata.service.VendingMachineOperation;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.maheshnaik.vendingkata.service.VendingMachineOperation.CANDY_COST;
import static com.github.maheshnaik.vendingkata.service.VendingMachineOperation.CHIPS_COST;
import static com.github.maheshnaik.vendingkata.service.VendingMachineOperation.COLA_COST;
import static org.junit.Assert.assertEquals;


public class VendingMachineMangerTest {

    public VendingMachineOperation vendingMachineOperation;
    private List<Product> stock;
    @Before
    public void setUp() {
        stock = new ArrayList<>();
        stock.add(new Product("Cola", COLA_COST, 1));
        stock.add(new Product("Candy", CANDY_COST, 10));
        stock.add(new Product("Chip", CHIPS_COST, 100));
        this.vendingMachineOperation = new VendingMachineManager(stock);
    }

    @Test
    public void insertCoinTest(){

        vendingMachineOperation.insertCoins(100);

        assertEquals("Insert Valid Coins only", vendingMachineOperation.getDisplayMessage());

        vendingMachineOperation.insertCoins(10);
        assertEquals("10", vendingMachineOperation.getDisplayMessage());

        vendingMachineOperation.insertCoins(25);
        assertEquals("35", vendingMachineOperation.getDisplayMessage());

    }

    @Test
    public void buyCola(){
        vendingMachineOperation.buyCola();
        assertEquals("Insert Coins", vendingMachineOperation.getDisplayMessage());
        vendingMachineOperation.insertCoins(25);
        vendingMachineOperation.insertCoins(25);
        vendingMachineOperation.insertCoins(25);
        vendingMachineOperation.buyCola();
        assertEquals("Insert Coins", vendingMachineOperation.getDisplayMessage());
        vendingMachineOperation.insertCoins(25);
        vendingMachineOperation.buyCola();
        assertEquals("Item Dispatched", vendingMachineOperation.getDisplayMessage());
        vendingMachineOperation.buyCola();
        assertEquals("Sold Out", vendingMachineOperation.getDisplayMessage());
    }

    @Test
    public void buyChips(){
        vendingMachineOperation.buyChips();
        assertEquals("Insert Coins", vendingMachineOperation.getDisplayMessage());
        vendingMachineOperation.insertCoins(25);
        vendingMachineOperation.insertCoins(25);
        vendingMachineOperation.insertCoins(25);
        vendingMachineOperation.buyChips();
        assertEquals("Item Dispatched", vendingMachineOperation.getDisplayMessage());
        assertEquals(25, vendingMachineOperation.getMoneyReturned());
    }
}