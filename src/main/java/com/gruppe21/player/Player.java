package com.gruppe21.player;

import org.graalvm.compiler.nodes.PiArrayNode;

public class Player {
    private static final int MAX_NAME_LENGTH = 50;
    private static final String[] PLAYER_PIECES_TEXT = {"\uD83D\uDC15", "\uD83D\uDC08", "\uD83D\uDE97", "\uD83D\uDEA2"};

    private String name = "";            // The player's name
    private BankBalance bankBalance;        // The player's bank balance
    private boolean nameEndsWithS;     // Checks whether or not the the player's name ends with an "s"
    private PlayerPiece piece;            // Piece
    public Boolean prisonStatus;       // Boolean status whether Player is in prison or not
    private int age;                    // Int age of player. Youngest player starts.
    public String[] ownedProperties;     // All owned properties of a player
    public String[] ownedCards;           // All currently owned chance cards of a player

    private int currentSquareIndex;
    public Player(String name) {
        setName(name);
        setPiece(piece);
        setAge(age);
        initPlayer();
    }

    public Player() {
        initPlayer();
    }

    private void initPlayer() {
        bankBalance = new BankBalance();
        currentSquareIndex = 0;
        prisonStatus = false;

    }

    // Gets the bank balance
    public BankBalance getBankBalance() {
        return bankBalance;
    }

    // Sets the player's points to a given int
    public void setBankBalance(BankBalance bankBalance) {
        this.bankBalance = bankBalance;
    }

    // Gets the player's name as a string
    public String getName() {
        return name;
    }

    // Gets the player's age as an integer
    public int getAge() {
        return age;
    }

    // Sets the player's piece
    public void setPiece(PlayerPiece piece) {
        this.piece = piece;
    }

    // Sets the player's owned properties
    public boolean setProperties(String[] ownedProperties) {
        ownedProperties = new String[]{};
        this.ownedProperties = ownedProperties;
        return true;
    }

    // Sets the player's currently owned cards
    public void setCards(String[] ownedCards) {
        ownedCards = new String[]{};
        this.ownedCards = ownedCards;
        return true;
    }

    // Sets the player's name
    public boolean setName(String name) {
        if (name.length() > MAX_NAME_LENGTH) return false;
        this.name = name;
        nameEndsWithS = getName().toLowerCase().endsWith("s");
        return true;
    }

    //Sets player's age
    public boolean setAge(int age) {
        if (age < 3) return false;
        if (age < 5) {
            // Possibility to add sout telling ages 5 or older is recommended
            return true;
        }
        this.age = age;
        return true;
    }

    // Checks if the player's name ends with a "s"
    public boolean isNameEndsWithS() {
        return nameEndsWithS;
    }

    public int getCurrentSquareIndex() {
        return currentSquareIndex;
    }

    // examines which square the player is on
    public void setCurrentSquareIndex(int currentSquareIndex) {
        this.currentSquareIndex = currentSquareIndex;
    }

    public String getPieceAsString(){
        return PLAYER_PIECES_TEXT[piece.ordinal()];
    }
}

