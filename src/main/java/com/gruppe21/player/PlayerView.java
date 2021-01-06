package com.gruppe21.player;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.squares.controllers.SquareController;

public class PlayerView {

    public void rollDice(int... diceValues){
        GUIManager.getInstance().waitForUserAcknowledgement("ROLL TEXT (PlayerView rollDice)"); //TODO: use localisation
    }

    public void movePlayer(SquareController squareController){

    }



}
