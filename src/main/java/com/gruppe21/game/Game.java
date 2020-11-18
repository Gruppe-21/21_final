package com.gruppe21.game;
//Todo:
// Add AI controlled players

import com.gruppe21.game.board.Board;
import com.gruppe21.game.board.squares.GoToPrisonSquare;
import com.gruppe21.game.board.squares.Square;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;
import com.gruppe21.utils.stringutils.RandomNameGenerator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Game {
    private final char MIN_PLAYERS = 2;
    private final char MAX_PLAYERS = 4;

    private Localisation localisation;
    private GUIManager guiManager;
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

    public Die[] getDice() {
        return dice;
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
        guiManager.initGUI(board);

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
        guiManager.addPlayersToGUI(players);
    }

    public boolean playRound() {
        // Wait for player to press "Roll"
        //Todo: player.getPossessive instead
        String playerNamePossessive = players[currentPlayer].getName() + (players[currentPlayer].isNameEndsWithS() ? "'" : "'s");
        guiManager.waitForUserButtonPress(localisation.getStringValue("rollDiceMessage", playerNamePossessive), localisation.getStringValue("rollButton"));
        guiManager.setGUIDice(dice);

        // Gets the sum of the random values that was set before round was started.
        //Not necessary for Monopoly Jr.
        int sum = 0;
        for (Die die : dice) {
            sum += die.getValue();
        }
        movePlayer(currentPlayer, board.getSquareAtNumber(sum));
        guiManager.setGUIPlayerBalance(currentPlayer, players[currentPlayer].getBankBalance().getBalance());
        for (Player player : players) {
            if (player.isBankrupt) return true;
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
        //TODO Fix loser
        guiManager.waitForUserAcknowledgement("winningMessage", loser.getName, winner.getName());
        guiManager.closeGUI();
    }

    public void movePlayer(int playerIndex, Square square) {
        Player player = players[playerIndex];
        int squareIndex = board.getSquareIndex(square);
        if (player.getCurrentSquareIndex() > squareIndex || player.getCurrentSquareIndex() != 0 && square.getClass() != GoToPrisonSquare.class)
            board.getSquareAtNumber(0).handleLandOn(player);
        guiManager.movePlayer(player, squareIndex);
        player.setCurrentSquareIndex(squareIndex);
        board.getSquareAtNumber(squareIndex).handleLandOn(player);
    }

    private int nextPlayer() {
        return (currentPlayer + 1) % players.length;
    }
}
