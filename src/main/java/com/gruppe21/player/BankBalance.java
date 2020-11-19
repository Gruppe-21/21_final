package com.gruppe21.player;

import com.gruppe21.gui.GUIManager;

public class BankBalance {

    private int balance; // Player has 1000 as starting balance
    private final Player parent;

    public BankBalance(Player parent) {
        balance = 1000;
        this.parent = parent;
    }


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if (balance < 0) balance = 0;

        this.balance = balance;
        GUIManager.getInstance().setGUIPlayerBalance(parent, getBalance());
    }

    /**
     * add value of parameter "amount" to current balance
     *
     * @param amount
     * @return getBalance() new balance
     */
    public int addBalance(int amount) {
        setBalance(getBalance() + amount);
        return getBalance();
    }

}
