package com.gruppe21.player;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.squares.controllers.OwnableSquareController;
import com.gruppe21.squares.controllers.PropertySquareController;
import com.gruppe21.utils.localisation.Localisation;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import java.awt.*;

public class PlayerView {
    Localisation localisation;
    GUIManager guiManager;

    public PlayerView(){
        localisation = Localisation.getInstance();
        guiManager = GUIManager.getInstance();
    }

    public int startTurn() {
        return guiManager.getUserButtonPressed("STARTTURNMESG","STARTTURN","BUILDSTUFF","LIQUIDATEASSETS");/*
                localisation.getStringValue("startTurnMesgLabel"),
                localisation.getStringValue("startTurnButtonLabel"),
                localisation.getStringValue("purchaseBuildingsButtonLabel"),
                localisation.getStringValue("liquidateAssetsButtonLabel"));*/
    }


    /**
     *
     * @param diceValues
     */
    public void rollDice(int... diceValues){
        //tells the player to roll
        guiManager.waitForUserAcknowledgement(localisation.getStringValue("rollDiceMesgLabel")); //TODO: use localisation
        //What if there isn't two values?
        guiManager.rollDice(diceValues[0], diceValues[1]);
    }

    public String chooseName(int minLength, int maxLength){
        return guiManager.getUserTextInput("ASK NAME (PlayerView chooseName)", minLength, maxLength, true).trim();
    }

    public GUI_Car customiseCar(){
        //TODO: ask player to customise their car.
        return new GUI_Car(colors[colorToUse++], Color.black, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
    }

    public void addToGui(GUI_Player guiPlayer){
        guiManager.addPlayer(guiPlayer);
    }

    public void imprisonedDiceCheater(){
        guiManager.waitForUserAcknowledgement("'GO TO PRISON, DICE CHEATER!' TEXT");
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
     * @param liquidateAssets
     * @return
     */
    public boolean askPurchase(String name, int price, boolean liquidateAssets){
        return guiManager.getUserBoolean("PURCHASE TEXT " + name + "PRICE TEXT " + price  + (liquidateAssets ? "LIQUIDATE ASSETS TEXT" : ""), "YESTEXT", "NOTEXT");
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

    /**
     *
     * @return
     */
    public int chooseHowToLiquidate(boolean optional) {
        String[] buttons;
        if (optional) {
            buttons = new String[] {"1: SELL PROPERTY", "2: MORTGAGE", "3: TRADE", "4: BACK"};
        } else {
            buttons = new String[] {"1: SELL PROPERTY", "2: MORTGAGE", "3: TRADE"};
        }
        String choice = guiManager.getUserButtonPress("CHOOSE LIQUIDATION METHOD", buttons);
        return (choice.charAt(0) - '1');
    }

    public OwnableSquareController chooseProperty(OwnableSquareController[] properties, String messageLabel){
        String[] names = new String[properties.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = properties[i].getName();
        }
        String choice = guiManager.getUserChoiceDropDown(localisation.getStringValue(messageLabel), names);
        for (int i = 0; i < names.length; i++) {
            if (choice.equals(names[i])) return properties[i];
        }
        return null;
    }

    public OwnableSquareController choosePropertyToSell(OwnableSquareController[] properties){
        return chooseProperty(properties, "PROPERTY SELL LABEL HERE");

    }

    public OwnableSquareController choosePropertyToMortgage(OwnableSquareController[] properties){
        return chooseProperty(properties, "PROPERTY MORTGAGE LABEL HERE");
    }


    //Temporary
    private static Color[] colors = {Color.GREEN, Color.BLUE, Color.RED, Color.ORANGE, Color.CYAN, Color.magenta};
    private static int colorToUse = 0;
}
