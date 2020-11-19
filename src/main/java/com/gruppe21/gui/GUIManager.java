package com.gruppe21.gui;


import com.gruppe21.game.Die;
import com.gruppe21.game.board.Board;
import com.gruppe21.game.board.squares.PropertySquare;
import com.gruppe21.player.Player;
import com.gruppe21.utils.arrayutils.OurArrayList;
import com.gruppe21.utils.localisation.Localisation;

import java.awt.*;

public class GUIManager {

    public static GUIManager instance;
    public boolean isTest;
    private GUIWrapper guiWrapper;
    private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

    public static GUIManager getInstance() {
        if (instance == null) {
            instance = new GUIManager();
        }
        return instance;
    }

    public void initGUI(Board board) {
        if (isTest) return;
        guiWrapper = new GUIWrapper();
        guiWrapper.reloadGUI(board.getSquares());
    }

    public void addPlayersToGUI(Player[] players) {
        if (isTest) return;
        for (int i = 0; i < players.length; i++) {
            String realName = players[i].getName();
            String guiName = realName;

            int j = 2;
            while (guiWrapper.hasPlayerWithName(guiName)) {
                guiName = realName + " (" + j + ")";
                j++;
            }

            players[i].setName(guiName);
            guiWrapper.addPlayer(players[i], colors[0]);
            players[i].setName(realName);
        }
    }

    public void setGUIDice(Die[] dice) {
        if (isTest) return;
        guiWrapper.setDice(dice[0].getValue(), dice[1].getValue());
    }

    private static final int MAX_NUM_BUTTONS = 5;
    public PropertySquare[] sellProperties(PropertySquare[] properties, String creditorName, int debt){
        //Todo: should show next and previous buttons if there aren't any properties to show
        OurArrayList<PropertySquare> selected = new OurArrayList<>();
        Localisation localisation = Localisation.getInstance();
        int currentlyViewing = 0;
        int numPropertyButtons = Math.min(properties.length, MAX_NUM_BUTTONS - 2);
        String[] buttons = new String[numPropertyButtons+2];
        buttons[0] = localisation.getStringValue("nextBtn");
        buttons[buttons.length-1] = localisation.getStringValue("previousBtn");
        do {
            for (int i = 0; i < numPropertyButtons-1; i++) {
                buttons[i+1] = properties[currentlyViewing+i].getDescription();
            }

            String selectedButton = waitForUserButtonPress(localisation.getStringValue("sellProperties", Integer.toString(debt), creditorName), buttons);
            if (selectedButton.equals(buttons[0]) && currentlyViewing < (properties.length - 3) ) currentlyViewing++;
            else if(selectedButton.equals(buttons[buttons.length-1]) && currentlyViewing > 0) currentlyViewing--;
            else {
                for (PropertySquare property : properties) {
                    if (property.getDescription().equals(selectedButton)){
                        if (selected.indexOf(property) == -1){
                            selected.add(property);
                            debt -= property.getPrice();
                        }
                        else {
                            selected.remove(property);
                            debt += property.getPrice();
                        }
                    }
                }

            }

        }while (debt > 0);
        return selected.toArray(new PropertySquare[0]);
    }

    public void setGUIPlayerBalance(Player player, int newBalance) {
        if (isTest) return;
        guiWrapper.updatePlayerBalance(player.getGuiPlayer(), newBalance);
    }

    public void movePlayer(Player player, int squareIndex) {
        if (isTest) return;
        guiWrapper.movePlayer(player, squareIndex);
    }

    public void waitForUserAcknowledgement(String message) {
        if (isTest) return;
        guiWrapper.showMessage(message);
    }

    public String waitForUserButtonPress(String message, String... buttonText) {
        if (isTest) return null;
        return guiWrapper.getButtonPress(message, buttonText);
    }

    public String waitForUserTextInput(String message) {
        if (isTest) return null;
        return guiWrapper.getStringInput(message);
    }

    public void closeGUI() {
        if (guiWrapper != null) guiWrapper.close();
    }
}
