package com.gruppe21.player;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.squares.controllers.SquareController;
import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

import java.awt.*;

public class PlayerView {

    public PlayerView(){
    }

    /**
     *
     * @param diceValues
     */
    public void rollDice(int... diceValues){
        GUIManager guiManager = GUIManager.getInstance();
        //tells the player to roll
        guiManager.waitForUserAcknowledgement("ROLL TEXT (PlayerView rollDice)"); //TODO: use localisation
        //What if there isn't two values?
        guiManager.rollDice(diceValues[0], diceValues[1]);
    }


    public void movePlayer(SquareController squareController){
        GUIManager.getInstance().setPlayerPosition(guiPlayer, squareController.getSquareField());
    }

    public String chooseName(int minLength, int maxLength){
        return GUIManager.getInstance().getUserTextInput("ASK NAME (PlayerView chooseName)", minLength, maxLength, true).trim();
    }

    public GUI_Car customiseCar(){
        //TODO: ask player to customise their car.
        return new GUI_Car(colors[colorToUse], Color.black, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
    }

    public void addToGui(GUI_Player guiPlayer){
        GUIManager.getInstance().addPlayer(guiPlayer);
    }

    //Temporary
    private static Color[] colors = {Color.GREEN, Color.BLUE, Color.RED, Color.ORANGE, Color.CYAN, Color.magenta};
    private static int colorToUse = 0;
}
