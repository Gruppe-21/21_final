package com.gruppe21.game;

import com.gruppe21.gui.GUIManager;
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

    public int askNumberOfPlayers(int minNumPlayers, int maxNumPlayers){
        return GUIManager.getInstance().getUserInteger("ASK NUM PLAYERS, GameView askNumberOfPlayers", minNumPlayers, maxNumPlayers);
    }

}
