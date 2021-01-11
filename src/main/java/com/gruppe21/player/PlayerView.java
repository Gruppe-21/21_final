package com.gruppe21.player;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.squares.controllers.PropertySquareController;
import gui_fields.GUI_Car;
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


    /**
     * Asks the player to choose a method of release from prison.
     * @param hasPardon true if the player can use a <code>PardonCard</code.>
     * @param mayRollForFreedom true if the player may roll to be released.
     * @return an {@code int} representing the players choice. 49 ('1') means they chose to use a pardon,
     *         50 ('2') means they chose to roll the dice and 51 ('3') means they chose to pay.
     */
    public int chooseJailRemoval(boolean hasPardon, boolean mayRollForFreedom){
        String[] options = new String[1 + (hasPardon ? 1 : 0) + (mayRollForFreedom ? 1 : 0)];
        for (int i = 0; i < options.length; i++) {
            options[i] = i + ": JAIL REMOVAL CHOICE " + i + ", PlayerView chooseJailRemoval";
        }
        int choice = GUIManager.getInstance().getUserChoiceDropDown("CHOOSE JAIL REMOVAL MESSAGE, PlayerView chooseJailRemoval").charAt(0) + (hasPardon ? 0 : 1);
        return choice + ( (mayRollForFreedom && choice == 49) ? 0 : 1);
    }

    /**
     *
     * @param name
     * @param price
     * @param liquidate
     * @return
     */
    public boolean askPurchase(String name, int price, boolean liquidateAssets){
        return GUIManager.getInstance().getUserBoolean("PURCHASE TEXT " + name + "PRICE TEXT " + price  + (liquidateAssets ? "LIQUIDATE ASSETS TEXT" : ""), "YESTEXT", "NOTEXT");
    }

    /**
     *
     * @param properties
     * @return
     */
    public PropertySquareController choosePropertyBuildBuilding(PropertySquareController[] properties){
        String[] choices = new String[properties.length];
        for (int i = 0; i < properties.length; i++) {
            choices[i] = properties[i].getName() + " " + properties[i].getBuildingCost();
        }
        String choice = GUIManager.getInstance().getUserChoiceDropDown("BUILD TEXT", choices);
        for (int i = 0; i < choice.length(); i++) {
            if (choice.equals(choices[i])) return properties[i];
        }
        return null;
        //Throw exception
    }




    //Temporary
    private static Color[] colors = {Color.GREEN, Color.BLUE, Color.RED, Color.ORANGE, Color.CYAN, Color.magenta};
    private static int colorToUse = 0;
}
