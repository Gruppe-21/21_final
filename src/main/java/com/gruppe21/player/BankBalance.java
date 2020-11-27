package com.gruppe21.player;

import com.gruppe21.game.Game;
import com.gruppe21.gui.GUIManager;

public class BankBalance {
    private int balance;
    private final Player parent;

    public BankBalance(Player parent) {
        balance = 20;
        this.parent = parent;
    }


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
        GUIManager.getInstance().setGUIPlayerBalance(parent, getBalance());
        if (balance < 0 ) {
            parent.setBankrupt(true);
            this.balance = 0;
        }

    }

    /**
     * Transfers money to the bank
     * @param debit is the amount of money transferred out of the account.
     *              Can be a negative number in which case money is transferred into the account instead
     */
    public void transferMoney(int debit){
        transferMoney(debit, null);
    }

    /**
     * Transfers money to another player. If the player does not have enough money, their properties are sold.
     * @param debit is the amount of money transferred out of the account.
     *              Can be a negative number in which case money is transferred into the account instead.
     * @param creditor is the player which receives the money.
     *                 if creditor == null the money is given to the bank.
     *
     */
    public void transferMoney(int debit, Player creditor){
        if (creditor == parent) return;
        if (getBalance() < debit)
        {
            if (Game.isAdvanced()) {
                debit -= getBalance();
                transferMoney(getBalance(), creditor);
                debit -= parent.sellProperties(debit, creditor);
            }
        }
        addBalance(-debit);
        if (creditor != null){ //creditor == null -> creditor is the bank
            creditor.getBankBalance().addBalance(debit);
        }
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

    /**
     *  Checks if paying the passed amount would bankrupt the owner of the BankBalance
     * @param price
     * @return if paying price would bankrupt the player
     */
    public boolean willBankrupt(int price){
        return price > parent.canPayInTotal();
    }
}
