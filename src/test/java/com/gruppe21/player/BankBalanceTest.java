package com.gruppe21.player;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankBalanceTest {
    @Test
    public void addBankBalancePositiveAmountTest() {
        Player player = new Player();
        BankBalance bankBalance = new BankBalance(player);; // make instance of BankBalance called bankBalance
        int amount = 1;

        bankBalance.addBalance(amount); // call addBalance on BankBalance

        Assert.assertEquals(21, bankBalance.getBalance());
    }

    @Test
    public void addBankBalanceNegativeAmountTest() {
        Player player = new Player();
        BankBalance bankBalance = new BankBalance(player);// make instance of BankBalance called bankBalance
        int amount = -1;

        bankBalance.addBalance(amount); // call addBalance on BankBalance

        Assert.assertEquals(19, bankBalance.getBalance());
    }

    @Test
    public void addBankBalanceZeroTest() {
        Player player = new Player();
        BankBalance bankBalance = new BankBalance(player);
        bankBalance.setBalance(50);             // bankBalance set to 50
        int amount = -100;

        bankBalance.addBalance(amount); // call addBalance on bankBalance

        Assert.assertEquals(0, bankBalance.getBalance());
    }
}