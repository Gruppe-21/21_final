package com.gruppe21.player;

public class PlayerController {

    Player player;

    public PlayerController(){
        player = new Player();
    }


    /**
     * Transfers money to another player. If the player does not have enough money, their properties are sold.
     * @param debit is the amount of money transferred out of the account.
     *              Can be a negative number in which case money is transferred into the account instead.
     * @param creditor is the player which receives the money.
     *                 if creditor == null the money is given to the bank.
     *
     */
    public void transferMoney(int debit, PlayerController creditor){
        if (creditor == this) return;
        if (player.totalValue < debit){
            //We have gone bankrupt
            //Sell houses
            //Transfer cash
            //Transfer properties
            return;
        }
        if (player.getBalance() < debit)
        {
            debit -= player.getBalance();
            transferMoney(player.getBalance(), creditor); //If we don't have enough cash, we first transfer what we have.
            debit -= sellProperties(debit, creditor); //It should be possible to trade with other players here too
        }
        addBalance(-debit);
        if (creditor != null){ //creditor == null -> creditor is the bank
            creditor.addBalance(debit);
        }
    }

    /**
     * add value of parameter "amount" to current balance
     *
     * @param amount
     * @return getBalance() new balance
     */
    public int addBalance(int value) {
        setBalance(getBalance() + value);
        return getBalance();
    }
}
