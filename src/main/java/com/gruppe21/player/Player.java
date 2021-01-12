package com.gruppe21.player;
import com.gruppe21.deck.Deck;
import com.gruppe21.squares.controllers.OwnableSquareController;
import com.gruppe21.squares.controllers.PropertySquareController;
import com.gruppe21.squares.controllers.SquareController;
import gui_fields.GUI_Player;

import java.awt.*;
import java.util.Objects;

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

    private GUI_Player guiPlayer;


    public Player(){
        this.balance = START_FUNDS;
        heldCards = new Deck();
        ownedProperties = new PropertySquareController[4];
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
        OwnableSquareController[] properties = new PropertySquareController[numOwnedProperties];
        for (int i = 0; i < numOwnedProperties; i++) {
            properties[i] = ownedProperties[i];
        }
        return properties;
    }

    public void addOwnedProperty(OwnableSquareController propertySquareController){
        numOwnedProperties++;
        if (numOwnedProperties > ownedProperties.length){
            OwnableSquareController[] newArray = new PropertySquareController[numOwnedProperties * 2];
            for (int i = 0; i < ownedProperties.length; i++) {
                newArray[i] = ownedProperties[i];
            }
            ownedProperties = newArray;
        }
        ownedProperties[numOwnedProperties-1] = propertySquareController;
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
        PropertySquareController[] buildableProperties = new PropertySquareController[ownedProperties.length];
        int numBuildableProperties = 0;
        for (int i = 0; i < getOwnedProperties().length; i++) {
            if (ownedProperties[i].mayBuild()) {
                buildableProperties[i] = (PropertySquareController) ownedProperties[i];
                numBuildableProperties++;
            }
        }
        PropertySquareController[] tempArray = new PropertySquareController[numBuildableProperties];
        for (int i = 0; i < numBuildableProperties; i++) {
            tempArray[i] = buildableProperties[i];
        }
        return tempArray;
    }

    public OwnableSquareController[] getMortgagedProperties(){
        return getPropertiesWithMortgagedStatus(true);
    }

    private int getNumMortgagedProperties(){
        return getNumPropertiesWithMortgagedStatus(true);
    }

    public OwnableSquareController[] getNonMortgagedProperties(){
        return getPropertiesWithMortgagedStatus(false);
    }

    private int getNumNonMortgagedProperties(){
        return getNumPropertiesWithMortgagedStatus(false);
    }


    private OwnableSquareController[] getPropertiesWithMortgagedStatus(boolean mortgaged){
        OwnableSquareController[] nonMortgagedProperties = new OwnableSquareController[getNumNonMortgagedProperties()];
        int addedProperties = 0;
        for (int i = 0; i < getOwnedProperties().length; i++) {
            if (ownedProperties[i].isMortgaged() == mortgaged){
                nonMortgagedProperties[addedProperties] = ownedProperties[i];
                addedProperties++;
            }
        }
        return nonMortgagedProperties;
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
}

