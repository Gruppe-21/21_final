package com.gruppe21.player;

public class StatusEffects {
    private boolean isImprisoned = false;
    private int identicalDice = 0;

    public boolean isImprisoned() {
        return isImprisoned;
    }

    public void setImprisoned(boolean imprisoned) {
        isImprisoned = imprisoned;
    }

    public int getIdenticalDice() {
        return identicalDice;
    }

    public void setIdenticalDice(int identicalDice) {
        this.identicalDice = identicalDice;
    }
}
