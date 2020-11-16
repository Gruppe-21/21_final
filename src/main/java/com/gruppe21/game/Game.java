package com.gruppe21.game;
//Todo:
// Add text to xml
// Read text from file

//Todo:
// Add AI controlled players

//Todo:
// Deal with exceptions during board creation in initGame

//Todo:
// Create chance cards/deck in initGame

//Todo:
// rename getSquareAtNumber to getSquareAtIndex

import com.gruppe21.game.board.Board;
import com.gruppe21.game.board.squares.Square;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;
import com.gruppe21.utils.stringutils.RandomNameGenerator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class Game {
    private final char MIN_PLAYERS = 2;
    private final char MAX_PLAYERS = 4;

    private Localisation localisation;
    private GUIManager guiManager;
    private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN};
    private Color[] availableColors = colors.clone();
    private boolean isTest;

    private Board board;
    private Player[] players;
    private int currentPlayer;
    private Die[] dice;

    public Game() {
        initGame(null, new Die[]{new Die(), new Die()}, false);
    }


    public Game(Player[] players) {
        initGame(players, new Die[]{new Die(), new Die()}, false);
    }

    public Game(Player[] players, Die[] dice) {
        initGame(players, dice, false);
    }

    public Game(Player[] players, Die[] dice, boolean isTest) {
        initGame(players, dice, isTest);
    }

    public Board getBoard() {
        return board;
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
        guiManager = GUIManager.getInstance();
        localisation = Localisation.getInstance();
        //Todo: Deal with exceptions
        try {
            board = new Board();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        this.isTest = isTest;
        initGUI();

        //Should make sure that 1 < players.length < 5  and dice.length = 1
        this.dice = dice;
        if (players != null) {
            this.players = players;
        } else {
            int numberOfPlayers;
            while (true) {
                String inputString = guiManager.waitForUserTextInput(localisation.getStringValue("requestSpecifyNumPlayers"));
                try {
                    numberOfPlayers = Integer.parseInt(inputString.trim());
                    if (numberOfPlayers > MAX_PLAYERS || numberOfPlayers < MIN_PLAYERS)
                        throw new Exception("Invalid number of players");
                    players = new Player[numberOfPlayers];
                } catch (Exception e) {
                    guiManager.waitForUserAcknowledgement(localisation.getStringValue("invalidNumberOfPlayers"));
                    continue;
                }
                break;
            }
            players = new Player[numberOfPlayers];
        }

        //It is insured that all players != null and all players have a name
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) players[i] = new Player();

            while (players[i].getName().isEmpty()) {
                try {
                    String providedPlayerName = guiManager.waitForUserTextInput(localisation.getStringValue("requestPlayerName", Integer.toString(i + 1)));
                    if (providedPlayerName == null) {
                        //It should not be possible to get here
                        throw new Exception("providedPlayerName is null");
                    }

                    if (providedPlayerName.isEmpty())
                        providedPlayerName = RandomNameGenerator.GetNameDifferentFrom(players);

                    if (!players[i].setName(providedPlayerName.trim()) || players[i].getName().isEmpty())
                        guiManager.waitForUserAcknowledgement(localisation.getStringValue("invalidName"));
                } catch (Exception e) {
                    guiManager.waitForUserAcknowledgement(localisation.getStringValue("unknownError"));
                }
            }

        }
        addPlayersToGUI(players);
    }

    public boolean playRound() {
        // Wait for player to press "Roll"
        //Todo: player.getPossessive instead
        String playerNamePossessive = players[currentPlayer].getName() + (players[currentPlayer].isNameEndsWithS() ? "'" : "'s");
        guiManager.waitForUserButtonPress(localisation.getStringValue("rollDiceMessage", playerNamePossessive), localisation.getStringValue("rollButton"));
        setGUIDice(dice);

        // Gets the sum of the random values that was set before round was started.
        //Not necessary for Monopoly Jr.
        int sum = 0;
        for (Die die : dice) {
            sum += die.getValue();
        }
        movePlayer(currentPlayer, board.getSquareAtNumber(sum));


        guiManager.waitForUserAcknowledgement(squareLandedOn.handleLandOn(players[currentPlayer]));
        setGUIPlayerBalance(currentPlayer, players[currentPlayer].getBankBalance().getBalance());
        if (players[currentPlayer].getBankBalance().getBalance() >= 3000) {
            return true;
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
        guiManager.waitForUserAcknowledgement("winningMessage", loser.getName, winner.getName());
        closeGUI();
    }

    public void movePlayer(int playerIndex, Square square) {
        Player player = players[playerIndex];
        int squareIndex = board.getSquareIndex(square);
        guiManager.movePlayer(player, squareIndex);
        player.setCurrentSquareIndex(squareIndex);
        board.getSquareAtNumber(squareIndex).handleLandOn(player);
    }

    private int nextPlayer() {
        return (currentPlayer + 1) % players.length;
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

    public void setGUIPlayerBalance(int playerindex, int newBalance) {
        if (isTest) return;
        guiWrapper.updatePlayerBalance(playerindex, newBalance);
    }


}
