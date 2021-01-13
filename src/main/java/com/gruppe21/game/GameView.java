package com.gruppe21.game;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.PlayerController;
import com.gruppe21.utils.localisation.Localisation;
import gui_fields.GUI_Empty;

import java.awt.*;

public class GameView {
    GUIManager guiManager;
    Localisation localisation;

    public GameView(){
        guiManager = GUIManager.getInstance();
        localisation = Localisation.getInstance();
    }

    public void selectLanguage(){
        localisation.setCurrentLocale(
                guiManager.getUserChoiceDropDown(
                        localisation.getStringValue("select_language"),
                        localisation.getAllLocales()
                ).substring(0,5)
        );
    }

    public void displayBoard(Board board){
        GUIManager.getInstance().displayBoard(board);
    }

    public int askNumberOfPlayers(int minNumPlayers, int maxNumPlayers){
        return GUIManager.getInstance().getUserInteger(Localisation.getInstance().getStringValue("requestSpecifyNumPlayers"), minNumPlayers, maxNumPlayers);
    }

    public PlayerController askForFirstPlayer(PlayerController... playerControllers){
        String[] names = new String[playerControllers.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = playerControllers[i].getName();
        }
        String choosenPlayerName = GUIManager.getInstance().getUserChoiceDropDown( Localisation.getInstance().getStringValue("askForFirstPlayer"), names);
        for (int i = 0; i < names.length; i++) {
            if (choosenPlayerName.equals(names[i])) return playerControllers[i];
        }
        return null;
    }

    public void displayWinner(PlayerController playerController){
        guiManager.waitForUserAcknowledgement(localisation.getStringValue("winnerText", playerController.getName()));
    }

}
