package com.gruppe21.player;
import com.gruppe21.squares.controllers.SquareController;

import java.util.Objects;

//Todo: add possesiveName

public class Player {
    private static final int START_FUNDS = 30000;

    SquareController position;
    int balance;
    int totalValue;
    int numSubscribers = 0;
    Object[] onMovedSubscribers = new Object[1]; //Shouldn't be Object

    public Player(){
        this.balance = START_FUNDS;
        this.totalValue = START_FUNDS;
    }

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

    private void notifyMove(){
        for (Object subscriber: onMovedSubscribers) {
            //notify them!
        }
    }

    public void moveTo(SquareController squareController){

    }

    public void teleportTo(SquareController squareController){

    }

    public int getBalance(){
        return this.getBalance();
    }

    public int setBalance(int balance){
        totalValue += balance - this.balance;
        this.balance = balance;
        return getBalance();
    }

}

