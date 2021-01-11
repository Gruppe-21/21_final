package com.gruppe21.player;

public class StatusEffects {
    private boolean isImprisoned = false;
    private int timeInJail = 0;
    private int identicalDice = 0;

    public boolean isImprisoned() {
        return isImprisoned;
    }

    public void setImprisoned(boolean imprisoned) {
        isImprisoned = imprisoned;
    }

    public int getTimeInJail() {
        return timeInJail;
    }

    public void setTimeInJail(int timeInJail) {
        this.timeInJail = timeInJail;
    }

    public void addTimeInJail(int time){
        timeInJail += time;
    }

    public int getIdenticalDice() {
        return identicalDice;
    }

    public void setIdenticalDice(int identicalDice) {
        this.identicalDice = identicalDice;
    }

    public void addIdenticalDice(int numDice){
        identicalDice += numDice;
    }

}
