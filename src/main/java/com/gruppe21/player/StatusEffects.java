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

    private static final int numEffects = 2;
    //Should use an enum probably
    public void enableEffect(int effect){
        if (effect == 0) return;
        for (int i = 0; i < numEffects; i++) {
            if ((effect & (1 << i)) == 0){
                continue;
            }
            switch (i){
                case 0 :
                    setImprisoned(true);
                    break;
                case 1 :
                    setImprisoned(false);
                    break;
            }
        }
    }

}
