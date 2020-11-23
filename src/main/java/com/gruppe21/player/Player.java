package com.gruppe21.player;
import com.gruppe21.game.Game;
import com.gruppe21.game.board.Deck.Deck;
import com.gruppe21.game.board.chancecard.ChanceCard;
import com.gruppe21.game.board.squares.PropertySquare;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.utils.arrayutils.OurArrayList;
import com.gruppe21.utils.localisation.Localisation;
import gui_fields.GUI_Player;

//Todo: add possesiveName

public class Player {
    public static int getMaxNumProperties() {
        return MAX_NUM_PROPERTIES;
    }

    private static final int MAX_NUM_PROPERTIES = 12; //Should be read from file?
    private static final int MAX_NAME_LENGTH = 50;
    private static final String[] PLAYER_PIECES_TEXT = {"\uD83D\uDC15", "\uD83D\uDC08", "\uD83D\uDE97", "\uD83D\uDEA2"};
    public static String playerPieceAsString(PlayerPiece playerPiece){
        return PLAYER_PIECES_TEXT[playerPiece.ordinal()];
    }

    private GUI_Player guiPlayer;
    private String name = "";            // The player's name
    private String possessiveName = ""; //This is currently not implemented
    private BankBalance bankBalance;        // The player's bank balance
    private PlayerPiece piece;            // Piece
    public Boolean prisonStatus;       // Boolean status whether Player is in prison or not
    private int age = -1;                    // Int age of player. Youngest player starts.
    private OurArrayList<PropertySquare> ownedProperties;     // All owned properties of a player
    private OurArrayList<ChanceCard> ownedCards;           // All currently owned chance cards of a player
    private Boolean isBankrupt;


    private int currentSquareIndex;

    public Player() {
        bankBalance = new BankBalance(this);
        currentSquareIndex = 0;
        prisonStatus = false;
        ownedProperties = new OurArrayList<>();
        ownedCards = new OurArrayList<>(2); //A player is probably rarely going to have more than 2 cards at once
        isBankrupt = false;
    }
    public Player(String name) {
        this();
        setName(name);
        setPiece(piece);
        setAge(age);
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

    // Sets the player's currently owned cards
    public void setCards(OurArrayList<ChanceCard> ownedCards) {
        this.setOwnedCards(ownedCards);
    }

    // Sets the player's name
    public boolean setName(String name) {
        if (name.length() > MAX_NAME_LENGTH) return false;
        this.name = name;
        setPossessiveName();
        return true;
    }
    public void setPossessiveName() {
        boolean nameEndsWithS = getName().toLowerCase().endsWith("s");
        this.possessiveName = this.getName() + (nameEndsWithS ? "'" : "'s");
    }
    public String getPossessiveName() {
        return possessiveName;
    }

    //Sets player's age
    public boolean setAge(int age) {
        this.age = age;
        if (age < 5) {
            // Possibility to add sout telling ages 5 or older is recommended
            return true;
        }
        return true;
    }

    public int getCurrentSquareIndex() {
        return currentSquareIndex;
    }

    // examines which square the player is on
    public void setCurrentSquareIndex(int currentSquareIndex) {
        this.currentSquareIndex = currentSquareIndex;
    }

    public String getPieceAsString(){
        return playerPieceAsString(getPiece());
    }

    public PlayerPiece getPiece() {
        return piece;
    }

    public GUI_Player getGuiPlayer() {
        return guiPlayer;
    }

    public void setGuiPlayer(GUI_Player guiPlayer) {
        this.guiPlayer = guiPlayer;
    }

    public OurArrayList<PropertySquare> getOwnedProperties() {
        return ownedProperties;
    }

    public void addProperty(PropertySquare property){
        ownedProperties.add(property);
    }

    public void removeProperty(PropertySquare property){
        ownedProperties.remove(property);
    }

    public Boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(Boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public void removeProperties(PropertySquare[] properties){
        for (PropertySquare property: properties) {
            removeProperty(property);
        }
    }

    public int sellProperties(int debt, Player creditor) {
        int soldPropertiesValue = 0;
        PropertySquare[] selectedProperties = getOwnedProperties().toArray(new PropertySquare[0]);
        if (!getBankBalance().willBankrupt(debt)) {
            selectedProperties = selectPropertiesToSell(creditor, debt, selectedProperties);
        }

        for (PropertySquare property : selectedProperties) {
            property.purchaseProperty(creditor, 0);
            soldPropertiesValue += property.getPrice();
        }
        return soldPropertiesValue;
    }

    private PropertySquare[] selectPropertiesToSell(Player creditor, int debt, PropertySquare... properties){
        //Todo: shouldn't show next and previous buttons if there aren't any properties to show
        OurArrayList<PropertySquare> selectable = new OurArrayList<>(properties.length), selected = new OurArrayList<>();
        for (PropertySquare property : properties) {
            selectable.add(property);
        }
        Localisation localisation = Localisation.getInstance();
        GUIManager guiManager = GUIManager.getInstance();
        String creditorName = creditor != null ? creditor.getName() : localisation.getStringValue("bankName");
        do {
            String[] propertyNames = new String[selectable.size()]; //TODO: Should probably be sorted by board position
            for (int i = 0; i < propertyNames.length; i++) {
                PropertySquare property = selectable.get(i);
                propertyNames[i] = "(" + localisation.getStringValue("currency", Integer.toString(property.getPrice())) + ") " + property.getName();
            }
            String selectedProperty = guiManager.getUserSelection(localisation.getStringValue("sellProperties", Integer.toString(debt), creditorName), propertyNames);
            for (int i = 0; i < selectable.size(); i++) {
                PropertySquare property = selectable.get(i);
                if (propertyNames[i].equals(selectedProperty)) {
                    selected.add(property);
                    selectable.remove(property);
                    debt -= property.getPrice();
                    break;
                }
            }
        }while (debt > 0);
        return selected.toArray(new PropertySquare[0]);
    }

    public int canPayInTotal(){
        int totalValue = getBankBalance().getBalance();

        for (PropertySquare ownedProperty : ownedProperties.toArray(new PropertySquare[0])) {
            totalValue += ownedProperty.getPrice();
        }

        return totalValue;
    }

    public OurArrayList<ChanceCard> getOwnedCards() {
        return ownedCards;
    }

    public void drawChanceCard(Deck deck, Game IShouldNotBeHere){
        getOwnedCards().add(deck.drawCard());
        getOwnedCards().get(0).onDraw(IShouldNotBeHere, this);
    }

    public void returnCard(Deck deck, ChanceCard chanceCard){
        getOwnedCards().remove(chanceCard);
        deck.returnCard(chanceCard);
    }

    public void setOwnedCards(OurArrayList<ChanceCard> ownedCards) {
        this.ownedCards = ownedCards;
    }
}

