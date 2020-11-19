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
            guiWrapper.addPlayer(players[i], colors[i]);
            players[i].setName(realName);
        }
    }

    public void setGUIDice(Die[] dice) {
        if (isTest) return;
        guiWrapper.setDice(dice[0].getValue(), dice[1].getValue());
    }

    public static int getMaxNumButtons() {
        return MAX_NUM_BUTTONS;
    }

    private static final int MAX_NUM_BUTTONS = 5;


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
