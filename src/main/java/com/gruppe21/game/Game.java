package com.gruppe21.game;

import com.gruppe21.player.PlayerController;;

public class Game {
    public static final char MIN_PLAYERS = 3;
    public static final char MAX_PLAYERS = 6;

    private final int initialNumberOfPlayers;
    private int numPlayers;
    private PlayerController[] playerControllers;
    private int currentPlayerIndex;
    private final Board board;

    public Game(Board board, int numPlayers){
        this.board = board;
        if (numPlayers < MIN_PLAYERS || numPlayers > MAX_PLAYERS){} //Throw exception
        initialNumberOfPlayers = numPlayers;
        this.numPlayers = numPlayers;
        playerControllers = new PlayerController[this.numPlayers];

    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public PlayerController[] getPlayerControllers() {
        return playerControllers;
    }

    /**
     *
     * @param playerController
     * @return
     */
    private int getIndexOfPlayer(PlayerController playerController){
        for (int i = 0; i < numPlayers; i++) {
            if (playerController == getPlayerControllers()[i]) return i;
        }
        return -1;
    }

    /**
     *
     * @param playerController the PlayerController of the player to be removed
     * @return                 a new PlayerController array without the passed PlayerController
     */
    public PlayerController[] removePlayer(PlayerController playerController){ //This would be a lot simpler if we used a dynamic data structure
        PlayerController[] newControllerArray = new PlayerController[--numPlayers];
        boolean skipped = false;
        for (int i = 0; i < numPlayers; i++) {
            if (playerControllers[i] == playerController) skipped = true;
            newControllerArray[i] = playerControllers[i + (skipped ? 1 : 0)]; //You can't add a boolean to an int in Java?! >:( Should I just make skipped an int? Yes. Am I going to make skipped an int? No. I refuse.
        }
        if (!skipped){
            numPlayers++;
            return playerControllers;
        }
        return newControllerArray;
    }

    public PlayerController getCurrentPlayer() {
        return playerControllers[currentPlayerIndex];
    }

    /**
     * Sets the current player to the passed PlayerController.
     * If the passed PlayerController is not in the list of players then nothing is changed.
     * @param currentPlayer the PlayerController of the new current player.
     */
    public void setCurrentPlayer(PlayerController currentPlayer){
        if (getCurrentPlayer() == currentPlayer) return;
        int oldIndex = currentPlayerIndex++;
        while (currentPlayerIndex != oldIndex){
            if (getCurrentPlayer() == currentPlayer) return;
        }
        //Should throw exception if we get here
    }


    /**
     * Sets the next player to the passed PlayerController.
     * If the passed PlayerController is not in the list of players then nothing is changed.
     * @param nextPlayer the PlayerController of the new next player.
     */
    public void setNextPlayer(PlayerController nextPlayer){
        if (getNextPlayer() == nextPlayer) return;
        int oldIndex = currentPlayerIndex++;
        while (currentPlayerIndex != oldIndex){
            if (getNextPlayer() == nextPlayer) return;
        }
        //Should throw exception if we get here
    }

    /**
     * Sets the current player to be the next player and returns this player.
     * This is different from getNextPlayer() which does not change the current player.
     * @return the PlayerController of the next player
     */
    public PlayerController nextPlayer() {
        currentPlayerIndex++;
        currentPlayerIndex %= getPlayerControllers().length;
        return getCurrentPlayer();
    }

    /**
     * Returns the PlayerController of the next player.
     * Unlike nextPlayer() the current player is unchanged.
     * @return the PlayerController of the next player
     * @see nextPlayer()
     */
    public PlayerController getNextPlayer() {
        return getPlayerControllers()[(currentPlayerIndex + 1) % numPlayers];
    }

    public Board getBoard() {
        return board;
    }
}

