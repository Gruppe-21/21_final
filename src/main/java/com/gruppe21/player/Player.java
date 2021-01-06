package com.gruppe21.player;
import com.gruppe21.game.Game;
import com.gruppe21.game.board.Deck.Deck;
import com.gruppe21.game.board.chancecard.ChanceCard;
import com.gruppe21.game.board.squares.PropertySquare;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.utils.arrayutils.OurArrayList;
import com.gruppe21.utils.localisation.Localisation;
import gui_fields.GUI_Player;

import java.util.Objects;

//Todo: add possesiveName

public class Player {
    private static final int START_FUNDS = 30000;

    SquareController position;
    int balance;
    int numSubscribers = 0;
    Object[] onMovedSubscribers = new Object[1]; //Shouldn't be Object

    public void subscribeToOnMoved(Object subscriber){
        //Lengthens array if too short
        if (numSubscribers == onMovedSubscribers.length){
            Object[] temp = new Object[onMovedSubscribers.length * 2];
            for (int i = 0; i < onMovedSubscribers.length; i++) {
                temp[i] = onMovedSubscribers[i];
            }
            onMovedSubscribers = temp;
        }
        onMovedSubscribers[numSubscribers] = subscriber;
        numSubscribers++;
    }

    private void notifyMove(){
        for (Object subscriber: onMovedSubscribers) {
            //notify them!
        }
    }

    public void moveTo(SquareController squareController){

    }

    public void teleportTo(SquareController squareController){

    }

}

