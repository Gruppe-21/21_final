package com.gruppe21.player;
import com.gruppe21.deck.Deck;
import com.gruppe21.squares.controllers.OwnableSquareController;
import com.gruppe21.squares.controllers.PropertySquareController;
import com.gruppe21.squares.controllers.SquareController;
import gui_fields.GUI_Player;

import java.awt.*;

//Todo: add possesiveName

public class Player {
    private static final int START_FUNDS = 30000;
    private static final int MAX_NAME_LENGTH = 25;

    public static int getMaxNameLength() {
        return MAX_NAME_LENGTH;
    }

    private String name;
    private SquareController position;
    private int balance;
    private Deck heldCards;
    private OwnableSquareController[] ownedProperties;
    private int numOwnedProperties = 0;
    private StatusEffects statusEffects;
    private boolean bankrupt;

    private GUI_Player guiPlayer;


    public Player(){
        this.balance = START_FUNDS;
        heldCards = new Deck();
        ownedProperties = new OwnableSquareController[4];
        statusEffects = new StatusEffects();
    }

    /**
     *
     * @return
     */
    public SquareController getPosition(){
        return position;
    }

    /**
     *
     * @param squareController
     */
    public void updatePosition(SquareController squareController){
        position = squareController;
        guiPlayer.getCar().setPosition(squareController.getSquareField());
    }

    public int getBalance(){
        return balance;
    }

    public int setBalance(int balance){
        this.balance = balance;
        guiPlayer.setBalance(this.balance);
        return getBalance();
    }

    public int getTotalValue(){
        int totalValue = getBalance();
        for (int i = 0; i < numOwnedProperties; i++) {
            totalValue += ownedProperties[i].getPropertyValue();
        }
        return totalValue;
    }

    public StatusEffects getStatusEffects(){
        return statusEffects;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Deck getHeldCards(){
        return this.heldCards;
    }

    /**
     *
     * @return
     */
    public OwnableSquareController[] getOwnedProperties(){
        OwnableSquareController[] properties = new OwnableSquareController[numOwnedProperties];
        for (int i = 0; i < numOwnedProperties; i++) {
            properties[i] = ownedProperties[i];
        }
        return properties;
    }

    public void addOwnedProperty(OwnableSquareController ownableSquareController){
        numOwnedProperties++;
        if (numOwnedProperties > ownedProperties.length){
            OwnableSquareController[] newArray = new OwnableSquareController[numOwnedProperties * 2];
            for (int i = 0; i < ownedProperties.length; i++) {
                newArray[i] = ownedProperties[i];
            }
            ownedProperties = newArray;
        }
        ownedProperties[numOwnedProperties-1] = ownableSquareController;
    }

    public void removeOwnedProperty(OwnableSquareController propertySquareController){
        for (int i = 0; i < numOwnedProperties; i++) {
            if (ownedProperties[i] == propertySquareController){
                ownedProperties[i] = ownedProperties[--numOwnedProperties];
            }
        }
    }

    /**
     * Creates a new array containing all buildable properties owned by the player.
     * @return a {@code PropertySquareController} array of the players buildable properties.
     */
    public PropertySquareController[] getBuildableProperties(){
        OwnableSquareController[] properties = getOwnedProperties();
        PropertySquareController[] buildableProperties = new PropertySquareController[properties.length];
        int numBuildableProperties = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].mayBuild()) {
                buildableProperties[numBuildableProperties] = (PropertySquareController) properties[i];
                numBuildableProperties++;
            }
        }
        PropertySquareController[] resizedArray = new PropertySquareController[numBuildableProperties];
        for (int i = 0; i < numBuildableProperties; i++) {
            resizedArray[i] = buildableProperties[i];
        }
        return resizedArray;
    }

    public PropertySquareController[] getBuiltProperties(){
        OwnableSquareController[] properties = getOwnedProperties();
        PropertySquareController[] builtProperties = new PropertySquareController[properties.length];
        int numBuiltProperties = 0;
        for (int i = 0; i < properties.length; i++) {
            if (ownedProperties[i] instanceof PropertySquareController) {
                if (((PropertySquareController)ownedProperties[i]).getNumHouses() > 0){
                    builtProperties[i] = (PropertySquareController) ownedProperties[i];
                    numBuiltProperties++;
                }
            }
        }

        PropertySquareController[] resizedArray = new PropertySquareController[numBuiltProperties];
        for (int i = 0; i < numBuiltProperties; i++) {
            resizedArray[i] = builtProperties[i];
        }
        return resizedArray;
    }

    public OwnableSquareController[] getMortgagedProperties(){
        return getPropertiesWithMortgagedStatus(true);
    }
/*
    private int getNumMortgagedProperties(){
        return getNumPropertiesWithMortgagedStatus(true);
    }
*/
    public OwnableSquareController[] getNonMortgagedProperties(){
        return getPropertiesWithMortgagedStatus(false);
    }

    /*
    private int getNumNonMortgagedProperties(){
        return getNumPropertiesWithMortgagedStatus(false);
    }
*/

    private OwnableSquareController[] getPropertiesWithMortgagedStatus(boolean mortgaged){
        OwnableSquareController[] propertiesWithMortgagedStatus = new OwnableSquareController[getNumPropertiesWithMortgagedStatus(mortgaged)];
        int addedProperties = 0;
        for (int i = 0; i < getOwnedProperties().length; i++) {
            if (ownedProperties[i].isMortgaged() == mortgaged){
                propertiesWithMortgagedStatus[addedProperties] = ownedProperties[i];
                addedProperties++;
            }
        }
        return propertiesWithMortgagedStatus;
    }

    private int getNumPropertiesWithMortgagedStatus(boolean mortgaged){
        int numPropertiesWithMortgagedStatus = 0;
        for (OwnableSquareController property: getOwnedProperties()) {
            if (property.isMortgaged() == mortgaged) numPropertiesWithMortgagedStatus++;
        }
        return numPropertiesWithMortgagedStatus;
    }

    public GUI_Player getGuiPlayer() {
        return guiPlayer;
    }

    public void setGuiPlayer(GUI_Player guiPlayer) {
        this.guiPlayer = guiPlayer;
    }

    public Color[] getColors(){
        return new Color[] {guiPlayer.getPrimaryColor(), guiPlayer.getSecondaryColor()};
    }

    public boolean isBankrupt() {
        return bankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
    }
}

