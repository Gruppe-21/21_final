package com.gruppe21.game;

import com.gruppe21.player.PlayerController;;

public class Game {
    public static final char MIN_PLAYERS = 3;
    public static final char MAX_PLAYERS = 6;

    private final int numPlayers;
    private final PlayerController[] playerControllers;
    private final Board board;

    public Game(int numPlayers){
        if (numPlayers < MIN_PLAYERS || numPlayers > MAX_PLAYERS){} //Throw exception
        this.numPlayers = numPlayers;
        playerControllers = new PlayerController[this.numPlayers];
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public PlayerController[] getPlayerControllers() {
        return playerControllers;
    }
}
