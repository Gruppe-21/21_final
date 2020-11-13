package com.gruppe21.player;

public class Player {
    private static final int MAX_NAME_LENGTH = 50;

    private String name = "";            // The player's name
    private BankBalance bankBalance;        // The player's bank balance
    //private boolean toSekserer = false;  // Checks whether or not the player played two 6
    private boolean nameEndsWithS;     // Checks whether or not the the player's name ends with an "s"
    private String piece[];            // Piece color
    private Boolean PrisonStatus = false;       // Boolean status whether Player is in prison or not
    private int age;                    // Int age of player. Youngest player starts.
    private int amountDue;              // The owed amount of Player
    public String ownedProperties;     // All owned properties of a player
    public String ownedCards;           // All currently owned chancecards of a player

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

        if (getBankBalance()-amountDue <0) {
            // Player loses
        }
    }

    // Gets the bank balance
    public BankBalance getBankBalance() {
        return bankBalance;
    }

    // Sets the player's point to a given int
    public void setBankBalance(BankBalance bankBalance) {
        this.bankBalance = bankBalance;
    }


    //public boolean isToSekserer() {return toSekserer;}

    //public void setToSekserer(boolean toSekserer) {this.toSekserer = toSekserer;}

    // Gets the player's name as a string
    public String getName() {
        return name;
    }

    // Sets the player's piece
    public boolean setPiece(String[] piece) {
        String[] pieceColor = new String[]{"Black", "White", "Green", "Yellow"};
        this.piece = pieceColor;
    return pieceColor;
    }
    // Sets the player's name
    public boolean setName(String name) {
        if (name.length() > MAX_NAME_LENGTH) return false;
        this.name = name;
        nameEndsWithS = getName().toLowerCase().endsWith("s");
        return true;
    }
    //Sets player's age
    public int setAge(int age) {
        this.age = age;
        return age;
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
}

