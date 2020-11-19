package com.gruppe21.player;
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

    private GUI_Player guiPlayer;
    private String name = "";            // The player's name
    private String possessiveName = ""; //This is currently not implemented
    private BankBalance bankBalance;        // The player's bank balance
    private PlayerPiece piece;            // Piece
    public Boolean prisonStatus;       // Boolean status whether Player is in prison or not
    private int age;                    // Int age of player. Youngest player starts.
    private OurArrayList<PropertySquare> ownedProperties;     // All owned properties of a player
    private ChanceCard[] ownedCards;           // All currently owned chance cards of a player

    private int currentSquareIndex;

    public Player() {
        bankBalance = new BankBalance(this);
        currentSquareIndex = 0;
        prisonStatus = false;
        ownedProperties = new OurArrayList<>();
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
    public void setCards(ChanceCard[] ownedCards) {
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
        return PLAYER_PIECES_TEXT[piece.ordinal()];
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

    public void removeProperties(PropertySquare[] properties){
        for (PropertySquare property: properties) {
            removeProperty(property);
        }
    }

    public int sellProperties(int debt, Player creditor) {
        if (isBankrupt(debt)) { //TODO: Probably should tell the player
            for (PropertySquare property : getOwnedProperties().toArray(new PropertySquare[0])) {
                property.purchaseProperty(creditor, 0); //May cause problems if creditor can't own all the properties
            }
            return canPay();
        }

        PropertySquare[] soldProperties = sellProperties(ownedProperties.toArray(new PropertySquare[0]), creditor.getName(), debt);
        for (PropertySquare property : soldProperties) {
            property.purchaseProperty(creditor, 0);
        }
        return debt;

    }

    private PropertySquare[] sellProperties(PropertySquare[] properties, String creditorName, int debt){
        //Todo: should show next and previous buttons if there aren't any properties to show
        OurArrayList<PropertySquare> selected = new OurArrayList<>();
        Localisation localisation = Localisation.getInstance();
        GUIManager guiManager = GUIManager.getInstance();
        int currentlyViewing = 0;
        int numPropertyButtons = Math.min(properties.length, GUIManager.getMaxNumButtons() - 2);
        String[] buttons = new String[numPropertyButtons+2];
        buttons[0] = localisation.getStringValue("nextBtn");
        buttons[buttons.length-1] = localisation.getStringValue("previousBtn");
        do {
            for (int i = 0; i < numPropertyButtons-1; i++) {
                buttons[i+1] = properties[currentlyViewing+i].getDescription();
            }

            String selectedButton = guiManager.waitForUserButtonPress(localisation.getStringValue("sellProperties", Integer.toString(debt), creditorName), buttons);
            if (selectedButton.equals(buttons[0]) && currentlyViewing < (properties.length - 3) ) currentlyViewing++;
            else if(selectedButton.equals(buttons[buttons.length-1]) && currentlyViewing > 0) currentlyViewing--;
            else {
                for (PropertySquare property : properties) {
                    if (property.getDescription().equals(selectedButton)){
                        if (selected.indexOf(property) == -1){
                            selected.add(property);
                            debt -= property.getPrice();
                        }
                        else {
                            selected.remove(property);
                            debt += property.getPrice();
                        }
                    }
                }

            }

        }while (debt > 0);
        return selected.toArray(new PropertySquare[0]);
    }

    public int canPay(){
        int totalValue = getBankBalance().getBalance();

        for (PropertySquare ownedProperty : ownedProperties.toArray(new PropertySquare[0])) {
            totalValue += ownedProperty.getPrice();
        }

        return totalValue;
    }

    public boolean isBankrupt(int price){
        return price > canPay();
    }

    public ChanceCard[] getOwnedCards() {
        return ownedCards;
    }

    public void setOwnedCards(ChanceCard[] ownedCards) {
        this.ownedCards = ownedCards;
    }
}

