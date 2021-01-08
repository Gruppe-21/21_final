package com.gruppe21.game;

import com.gruppe21.player.PlayerController;;

public class Game {
    public static final char MIN_PLAYERS = 3;
    public static final char MAX_PLAYERS = 6;

    private final int numPlayers;
    private final PlayerController[] playerControllers;
    private int currentPlayerIndex;
    private final Board board;

    public Game(Board board, int numPlayers){
        this.board = board;
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

    public PlayerController getCurrentPlayer() {
        return playerControllers[currentPlayerIndex];
    }

    public void setCurrentPlayer(PlayerController currentPlayer){
        if (getCurrentPlayer() == currentPlayer) return;
        int oldIndex = currentPlayerIndex++;
        while (currentPlayerIndex != oldIndex){
            if (getCurrentPlayer() == currentPlayer) return;
        }
        //Should throw exception if we get here
    }

    /**
     *
     * @return
     */
    public PlayerController nextPlayer(){
        currentPlayerIndex++;
        currentPlayerIndex %= getPlayerControllers().length;
        return getCurrentPlayer();
    }

    public Board getBoard() {
        return board;
    }
}
