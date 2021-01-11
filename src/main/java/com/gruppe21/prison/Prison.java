package com.gruppe21.prison;

import com.gruppe21.squares.controllers.SquareController;

public class Prison {

    private boolean isImprisoned;

    public boolean GoToPrison(Playercontroller playercontroller , SquareController squareController, boolean isimprisoned){
        this.isImprisoned = isimprisoned.isImprisoned(true);

        return isImprisoned;


    }
    public void GetOutOfPrison(){
        return isImprisoned(false);
    }
}
