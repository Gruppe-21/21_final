package com.gruppe21;

import com.gruppe21.gui.GUIWrapper;
import com.gruppe21.utils.stringutils.RandomNameGenerator;

import java.awt.*;
import java.util.Arrays;

public class Game {
    private GUIWrapper guiWrapper;
    private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN};
    private Color[] availableColors = colors.clone();
    private boolean isTest;

    private Board board;
    private Player[] players;
    private int currentPlayer;
    private Die[] dice;


    public Game(Player[] players) {
        initGame(players, new Die[]{new Die(), new Die()}, false);
    }

    public Game(Player[] players, Die[] dice) {
        initGame(players, dice, false);
    }

    public Game(Player[] players, Die[] dice, boolean isTest) {
        initGame(players, dice, isTest);
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Die[] getDice() {
        return dice;
    }

    public void setDice(Die[] dice) {
        this.dice = dice;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    private void initGame(Player[] players, Die[] dice, boolean isTest) {
        //Should make sure that players.length > 1 and dice.length > 0

        board = new Board();
        this.players = players;
        this.dice = dice;
        this.isTest = isTest;

        initGUI();

        //It is insured that all players != null and all players have a name
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) players[i] = new Player();

            while (players[i].getName().isEmpty()) {
                try {
                    String providedPlayerName = waitForUserTextInput("Please write your name, Player" + (i + 1) + " (Leave empty for a random name)");
                    if (providedPlayerName == null) {
                        //It should not be possible to get here
                        throw new Exception("providedPlayerName is null");
                    }

                    if (providedPlayerName.isEmpty())
                        providedPlayerName = RandomNameGenerator.GetNameDifferentFrom(players);

                    if (!players[i].setName(providedPlayerName.trim()) || players[i].getName().isEmpty())
                        waitForUserAcknowledgement("Invalid name");
                } catch (Exception e) {
                    waitForUserAcknowledgement("An error has occurred.");
                }
            }

        }
        addPlayersToGUI(players);
        waitForUserButtonPress("Welcome to The Quest for Kolding. Press start to begin!", "Start");
    }


    public boolean playRound() {
        // Wait for player to press "Roll"
        waitForUserButtonPress(players[currentPlayer].getName() + (players[currentPlayer].isNameEndsWithS() ? "'" : "'s") + " turn!", "Roll");
        setGUIDice(dice);

        // Gets the sum of the random values that was set before round was started.
        int sum = 0;
        for (Die die : dice) {
            sum += die.getValue();
        }
        movePlayer(currentPlayer, board.getSquareAtNumber(sum));

        Square squareLandedOn = board.getSquareAtNumber(sum);
        waitForUserAcknowledgement(squareLandedOn.handleEvent(players[currentPlayer]));
        setGUIPlayerBalance(currentPlayer, players[currentPlayer].getBankBalance().getBalance());
        if (players[currentPlayer].getBankBalance().getBalance() >= 3000) {
            return true;
        }
        if (squareLandedOn.getSquareType() != SquareType.ExtraTurn) {
            currentPlayer = nextPlayer();
        }
        return false;
    }

    public void startGame() {
        do {
            for (Die die : dice) {
                die.rollDie();
            }
        } while (!playRound());
        Player winner = players[currentPlayer];
        waitForUserAcknowledgement(winner.getName() + " has reached ¤" + winner.getBankBalance().getBalance()
                + " and won the game");
        waitForUserButtonPress("The game will now close.", "That's fine'");
        closeGUI();
    }

    private void movePlayer(int playerIndex, Square square) {
        int squareIndex = board.getSquareIndex(square);

        if (!isTest) guiWrapper.movePlayer(playerIndex, players[playerIndex].getCurrentSquareIndex(), squareIndex);

        players[playerIndex].setCurrentSquareIndex(squareIndex);
    }

    private int nextPlayer() {
        return (currentPlayer + 1) % players.length;
    }


    private void initGUI() {
        if (isTest) return;
        guiWrapper = new GUIWrapper();
        guiWrapper.reloadGUI(board.getSquares());
    }

    private void closeGUI() {
        if (guiWrapper != null) guiWrapper.close();
    }

    private void addPlayersToGUI(Player[] players) {
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

            if (availableColors.length != 0) {
                guiWrapper.addPlayer(players[i], availableColors[0]);
                availableColors = Arrays.copyOfRange(availableColors, 1, availableColors.length);
            } else guiWrapper.addPlayer(players[i], colors[(int) (Math.random() * colors.length)]);

            players[i].setName(realName);
        }
    }

    private void setGUIDice(Die[] dice) {
        if (isTest) return;
        //Should make sure that at least 2 dice in dice
        guiWrapper.setDice(dice[0].getValue(), dice[1].getValue());
    }

    private void setGUIPlayerBalance(int playerindex, int newBalance) {
        if (isTest) return;
        guiWrapper.updatePlayerBalance(playerindex, newBalance);
    }


    private void waitForUserAcknowledgement(String message) {
        if (isTest) return;
        guiWrapper.showMessage(message);
    }

    private void waitForUserButtonPress(String message, String buttonText) {
        if (isTest) return;
        guiWrapper.getButtonPress(message, buttonText);
    }

    private String waitForUserTextInput(String message) {
        if (isTest) return null;
        return guiWrapper.getStringInput(message);
    }


}
