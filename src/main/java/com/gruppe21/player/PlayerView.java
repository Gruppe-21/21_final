package com.gruppe21.player;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.squares.controllers.SquareController;
import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

import java.awt.*;

public class PlayerView {
    private GUI_Player gui_player;

    public PlayerView(String name, int balance, GUI_Car car){
        gui_player = new GUI_Player(name, balance, car);
    }

    public PlayerView(String name, int balance, Color primaryColor, Color secondaryColor, GUI_Car.Type carType, GUI_Car.Pattern pattern){
        this(name, balance, new GUI_Car(primaryColor, secondaryColor, carType, pattern));
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
        GUIManager.getInstance().setPlayerPosition(gui_player, squareController.getSquareField());
    }



}
