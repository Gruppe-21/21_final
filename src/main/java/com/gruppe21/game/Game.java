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

public class Game {
    private final char MIN_PLAYERS = 2;
    private final char MAX_PLAYERS = 4;

    private Localisation localisation;
    private GUIManager guiManager;
    private boolean isTest;

    private Board board;
    private Player[] players;
    private int currentPlayerIndex;
    private Die[] dice;
    private Deck deck;

    public Game(Player[] players, Die[] dice, boolean isTest) {
        if (players == null && isTest){}//Todo: Should throw error
        initGame(players, dice, isTest);
    }

    public Game() {
        this(null);
    }

    public Game(Player[] players) {
        this(players, new Die[]{new Die()});
    }

    public Game(Player[] players, Die[] dice) {
        this(players, dice, false);
    }



    public Board getBoard() {
        return board;
    }

    public Deck getDeck() {
        return deck;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Die[] getDice() {
        return dice;
    }

    //Unused
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    //Unused
    public Player getCurrentPlayer(){
        return players[getCurrentPlayerIndex()];
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
        deck.shuffleDeck();
        this.dice = dice;
        this.players = initialisePlayerArray(players);
        //It is insured that all players != null and all players have a name
        initialisePlayers(this.players);
        guiManager.addPlayersToGUI(this.players);
    }

    private Player[] initialisePlayerArray(Player[] players) {
        if (players == null) {
            int numberOfPlayers;
            while (true) {
                String inputString = guiManager.waitForUserTextInput(localisation.getStringValue("requestSpecifyNumPlayers"));
                //TODO: Should be handled by guiManager.
                try {
                    numberOfPlayers = Integer.parseInt(inputString.trim());
                    if (numberOfPlayers > MAX_PLAYERS || numberOfPlayers < MIN_PLAYERS)
                        throw new Exception("Invalid number of players");
                    players = new Player[numberOfPlayers];
                } catch (Exception e) {
                    guiManager.waitForUserAcknowledgement(localisation.getStringValue("invalidNumber", Integer.toString(MIN_PLAYERS), Integer.toString(MAX_PLAYERS)) );
                    continue;
                }
                break;
            }
        }
        return players;
    }

    private void initialisePlayers(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) players[i] = new Player();
            players[i].getBankBalance().setBalance(20 - (players.length-2)*2);

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

            while (players[i].getAge() == -1) {
                //TODO: Should be handled by guiManager.
                String inputString = guiManager.waitForUserTextInput(localisation.getStringValue("requestPlayerAge", players[i].getName()));
                try {
                    int age = Integer.parseInt(inputString.trim());
                    if (age < 0 || age > 999) //TODO: Should be defined elsewhere
                        throw new Exception("Invalid age");
                    players[i].setAge(age);
                } catch (Exception e) {
                    guiManager.waitForUserAcknowledgement(localisation.getStringValue("invalidNumber", Integer.toString(0), Integer.toString(999)) );
                    continue;
                }
                break;
            }
        }
        playerSelection();
    }

    public int getFirstPlayerIndex(){
        int i, minAge = getPlayers()[0].getAge(), indexOfYoungestPlayer = 0;
        for (i = 1; i < getPlayers().length; i++) {
            if (getPlayers()[i].getAge() < minAge){
                minAge = getPlayers()[i].getAge();
                indexOfYoungestPlayer = i;
            }
        }
        return indexOfYoungestPlayer;
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
            if (isTest) selected = availablePieces.get(0);
            switch (selected) {
                case "\uD83D\uDC15":
                    player.setPiece(PlayerPiece.Dog);
                    break;
                case "\uD83D\uDC08":
                    player.setPiece(PlayerPiece.Cat);
                    break;
                case "\uD83D\uDE97":
                    player.setPiece(PlayerPiece.Car);
                    break;
                case "\uD83D\uDEA2":
                    player.setPiece(PlayerPiece.Boat);
                    break;
            }
            availablePieces.remove(selected);
        }
    }

    private boolean checkGameOver(){
        for (Player player: players) {
            if (player.isBankrupt()) return true;
        }
        return false;
    }

    public boolean playRound() {
        Player curPlayer = players[currentPlayerIndex];
        try {
            if (curPlayer.prisonStatus) {
                Square currentSquare = board.getSquareAtIndex(curPlayer.getCurrentSquareIndex());
                if (currentSquare.getClass() != PrisonSquare.class)
                    throw new Exception("Player.prisonStatus is true but currentSquare is not a PrisonSquare");

                ((PrisonSquare)currentSquare).getOutOfJail(this, curPlayer);
                if (checkGameOver()) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Any chance cards that should be used at the start of the player's turn is used
        //All cards except get out of jail free and character cards are used immediately.
        for (ChanceCard chanceCard: curPlayer.getOwnedCards().toArray(new ChanceCard[0])) {
            if (chanceCard.getClass() != ChanceCardGetOutOfJailFree.class)
                chanceCard.use(this, curPlayer);
        }

        if (checkGameOver()) return true;

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

        if (checkGameOver()) return true;

        currentPlayerIndex = nextPlayer();
        return false;
    }

    public void startGame() {
        currentPlayerIndex = getFirstPlayerIndex();
        guiManager.waitForUserAcknowledgement(localisation.getStringValue("startMessage", getPlayers()[currentPlayerIndex].getName()));
        do {
            for (Die die : dice) {
                die.rollDie();
            }
        } while (!playRound());

        Player[] winners = getWinners();
        String winnerNames = winners[0].getName();
        for (int i = 1; i < winners.length - 1; i++) {
            winnerNames += ", " + winners[i].getName();
        }
        //TODO Add to xml
        winnerNames += " and " + winners[winners.length - 1].getName();
        guiManager.waitForUserAcknowledgement(
                localisation.getStringValue("winningMessage" + (winners.length > 1 ? "Tie" : ""),
                        players[currentPlayerIndex].getName(), winnerNames));
        guiManager.closeGUI();
    }

    private Player[] getWinners(){
        int maxMoney = 0;
        int maxTotalValue = 0;
        for (Player player : players) {
            int balance = player.getBankBalance().getBalance(), totalValue = player.canPayInTotal();
            if(balance >= maxMoney) {
                if (balance > maxMoney){
                    maxMoney = balance;
                    maxTotalValue = totalValue;
                }
                else if (totalValue > maxTotalValue){
                    maxTotalValue = totalValue;
                }
            }
        }
        OurArrayList<Player> winners = new OurArrayList<>(1);
        for (Player player: players) {
            if (player.getBankBalance().getBalance() == maxMoney && player.canPayInTotal() == maxTotalValue)
                winners.add(player);
        }
        return winners.toArray(new Player[0]);
    }

    public void movePlayerBy(Player player, int numSquares){
        movePlayer(player, board.getSquareAtRelativePosition(board.getSquareAtIndex(player.getCurrentSquareIndex()), numSquares));
        //movePlayer(player, board.getSquareAtRelativePosition(board.getSquareAtIndex(player.getCurrentSquareIndex()), numSquares));
    }

    public void movePlayer(int playerIndex, Square square) {
        movePlayer(players[playerIndex], square);
    }
    public void movePlayer(Player player, Square square) {
        int squareIndex = board.getSquareIndex(square);
        int oldPosition = player.getCurrentSquareIndex();
        teleportPlayer(player, squareIndex);
        if (player.getCurrentSquareIndex() < oldPosition && player.getCurrentSquareIndex() != 0)
            board.getSquareAtIndex(0).handleLandOn(player);
        board.getSquareAtIndex(squareIndex).handleLandOn(player, this);
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
        return (currentPlayerIndex + 1) % players.length;
    }

}
