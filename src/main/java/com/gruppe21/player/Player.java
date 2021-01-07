package com.gruppe21.player;
import com.gruppe21.squares.controllers.SquareController;

import java.util.Objects;

//Todo: add possesiveName

public class Player {
    private static final int START_FUNDS = 30000;
    private static final int MAX_NAME_LENGTH = 25;

    public static int getMaxNameLength() {
        return MAX_NAME_LENGTH;
    }

    private String name;
    private SquareController position;
    private int balance;
    private int totalValue;
    private Deck heldCards;
    private int statusEffects;

    private int numSubscribers = 0;

    Object[] onMovedSubscribers = new Object[1]; //Shouldn't be Object

    public Player(){
        this.balance = START_FUNDS;
        this.totalValue = START_FUNDS;
        heldCards = new Deck();
    }

    /**
     *
     * @param subscriber
     */
    public void subscribeToOnMoved(Object subscriber){
        //Lengthens array if too short
        if (numSubscribers == onMovedSubscribers.length){
            Object[] temp = new Object[onMovedSubscribers.length * 2];
            for (int i = 0; i < onMovedSubscribers.length; i++) {
                temp[i] = onMovedSubscribers[i];
            }
            onMovedSubscribers = temp;
        }
        onMovedSubscribers[numSubscribers] = subscriber;
        numSubscribers++;
    }

    /**
     *
     */
    private void notifyMove(){
        for (Object subscriber: onMovedSubscribers) {
            //notify them!
        }
    }

    public void updatePosition(SquareController squareController){
        position = squareController;

    }

    public int getBalance(){
        return this.getBalance();
    }

    public int setBalance(int balance){
        totalValue += balance - this.balance;
        this.balance = balance;
        return getBalance();
    }

    public int getTotalValue(){
        return totalValue;
    }

    public int getStatusEffects(){
        return statusEffects;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public CardController getHeldCards(){
        return this.heldCards;
    }

}

