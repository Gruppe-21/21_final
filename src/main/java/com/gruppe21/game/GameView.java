package com.gruppe21.game;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.PlayerController;
import com.gruppe21.utils.localisation.Localisation;

public class GameView {

    public GameView(){
    }

    public void selectLanguage(){
        Localisation.getInstance().setCurrentLocale(
                GUIManager.getInstance().getUserChoiceDropDown(
                        "SELECT LANGUAGE, GameView selectLanguage",
                        Localisation.getInstance().getAllLocales()
                )
        );
    }

    public void displayBoard(Board board){
        GUIManager.getInstance().displayBoard(board);
    }

    public int askNumberOfPlayers(int minNumPlayers, int maxNumPlayers){
        return GUIManager.getInstance().getUserInteger("ASK NUM PLAYERS, GameView askNumberOfPlayers", minNumPlayers, maxNumPlayers);
    }

    public PlayerController askForFirstPlayer(PlayerController... playerControllers){
        String[] names = new String[playerControllers.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = playerControllers[i].getName();
        }
        String choosenPlayerName = GUIManager.getInstance().getUserChoiceDropDown("ASK FIRST PLAYER, GameView askForFirstPlayer", names);
        for (int i = 0; i < names.length; i++) {
            if (choosenPlayerName.equals(playerControllers[i].getName())) return playerControllers[i];
        }
        return null;
    }

}
