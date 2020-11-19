package com.gruppe21.game;
//Todo:
// Add AI controlled players

import com.gruppe21.game.board.Board;
import com.gruppe21.game.board.Deck.Deck;
import com.gruppe21.game.board.chancecard.ChanceCard;
import com.gruppe21.game.board.chancecard.ChanceCardGetOutOfJailFree;
import com.gruppe21.game.board.squares.PrisonSquare;
import com.gruppe21.game.board.squares.Square;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.player.PlayerPiece;
import com.gruppe21.utils.arrayutils.OurArrayList;
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
    private Deck deck;

    public Game(Player[] players, Die[] dice, boolean isTest) {
        if (players == null && isTest){}//Todo: Should throw error
        initGame(players, dice, isTest);
    }

    public Game() {
        this(null, new Die[]{new Die(), new Die()}, false);
    }

    public Game(Player[] players) {
        this(players, new Die[]{new Die(), new Die()}, false);
    }

    public Game(Player[] players, Die[] dice) {
        this(players, dice, false);
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
        guiManager.isTest = isTest;
        localisation = Localisation.getInstance();
        board = new Board();
        this.isTest = isTest;
        guiManager.initGUI(board);

        //Should make sure that 1 < players.length < 5  and dice.length = 1
        this.deck = new Deck();
        this.dice = dice;
        initialisePlayerArray(players);
        //It is insured that all players != null and all players have a name
        initialisePlayers(players);
        guiManager.addPlayersToGUI(this.players);
    }

    private void initialisePlayers(Player[] players) {
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
        playerSelection();

    }

    private void initialisePlayerArray(Player[] players) {
        if (players == null) {
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
        }
        this.players = players;
    }

    public boolean playRound() {
        Player curPlayer = players[currentPlayer];
        try {
            if (curPlayer.prisonStatus) {
                Square currentSquare = board.getSquareAtIndex(curPlayer.getCurrentSquareIndex());
                if (currentSquare.getClass() != PrisonSquare.class)
                    throw new Exception("Player.prisonStatus is true but currentSquare is not a PrisonSquare");
                ((PrisonSquare)currentSquare).getOutOfJail(curPlayer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Wait for player to press "Roll"
        guiManager.waitForUserButtonPress(localisation.getStringValue("rollDiceMessage", curPlayer.getPossessiveName()), localisation.getStringValue("rollButton"));
        guiManager.setGUIDice(dice);

        // Gets the sum of the random values that was set before round was started.
        //Not necessary for Monopoly Jr.
        int sum = 0;
        for (Die die : dice) {
            sum += die.getValue();
        }
        movePlayerBy(curPlayer, sum);
        for (Player player : players) {
            //if (player.isBankrupt()) return true;
        }
        currentPlayer = nextPlayer();
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
       // guiManager.waitForUserAcknowledgement("winningMessage", loser.getName, winner.getName());
        guiManager.closeGUI();
    }

    public void movePlayerBy(Player player, int numSquares){
        movePlayer(player, board.getSquareAtRelativePosition(board.getSquareAtIndex(player.getCurrentSquareIndex()), numSquares));
    }

    public void movePlayer(int playerIndex, Square square) {
        movePlayer(players[playerIndex], square);
    }
    public void movePlayer(Player player, Square square) {
        int squareIndex = board.getSquareIndex(square);
        int oldPosition = player.getCurrentSquareIndex();
        teleportPlayer(player, squareIndex);
        if (player.getCurrentSquareIndex() < oldPosition || player.getCurrentSquareIndex() != 0)
            board.getSquareAtIndex(0).handleLandOn(player);
        board.getSquareAtIndex(squareIndex).handleLandOn(player);
    }

    public void teleportPlayer(Player player, int squareIndex){
        guiManager.movePlayer(player, squareIndex);
        player.setCurrentSquareIndex(squareIndex);
        //board.getSquareAtIndex(squareIndex).handleLandOn(player);
    }

    public void teleportPlayer(Player player, Square square){
        teleportPlayer(player, board.getSquareIndex(square));
    }

    private int getPlayerIndex(Player player){
        for (int i = 0; i < players.length; i++) {
            if (players[i].equals(player)) return i;
        }
        return -1;
    }

    private int nextPlayer() {
        return (currentPlayer + 1) % players.length;
    }

    public Deck getDeck() {
        return deck;
    }

    public void playerSelection()
    {
        OurArrayList<String> availablePieces = new OurArrayList<>();
        availablePieces.add("\uD83D\uDC15");
        availablePieces.add("\uD83D\uDC08");
        availablePieces.add("\uD83D\uDE97");
        availablePieces.add("\uD83D\uDEA2");
        for (Player player : getPlayers()) {
            String selected = guiManager.waitForUserButtonPress(localisation.getStringValue("choosePiece", player.getName()), availablePieces.toArray(new String[0]));
            switch (selected) {
                case "\uD83D\uDC15" -> player.setPiece(PlayerPiece.Dog);
                case "\uD83D\uDC08" -> player.setPiece(PlayerPiece.Cat);
                case "\uD83D\uDE97" -> player.setPiece(PlayerPiece.Car);
                case "\uD83D\uDEA2" -> player.setPiece(PlayerPiece.Boat);
            }
            availablePieces.remove(selected);
        }

    }
}
