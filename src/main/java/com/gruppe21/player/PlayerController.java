package com.gruppe21.player;

import com.gruppe21.card.cardControllers.CardController;
import com.gruppe21.card.typeOfCards.PardonCard;
import com.gruppe21.deck.Deck;
import com.gruppe21.game.Board;
import com.gruppe21.game.GameController;
import com.gruppe21.squares.controllers.OwnableSquareController;
import com.gruppe21.squares.controllers.PropertySquareController;
import com.gruppe21.squares.controllers.SquareController;
import gui_fields.GUI_Player;

import java.awt.*;
import java.util.Random;

public class PlayerController {

    private Player player;
    private PlayerView playerView;
    private GameController gameController;
    private static final Random random = new Random();
    private int lastRollForBrewery = 0;

    public PlayerController(){
        gameController = GameController.getInstance();
        player = new Player();
        playerView = new PlayerView();
        player.setName(playerView.chooseName(0, Player.getMaxNameLength()));
        player.setGuiPlayer(new GUI_Player(player.getName(), player.getBalance(), playerView.customiseCar()));
        playerView.addToGui(player.getGuiPlayer());
    }

    /**
     *
     * @param board
     */
    public void takeTurn(Board board){
        boolean roll = false;
        while (!roll){
            switch (playerView.startTurn()){
                case 0 : {
                    roll = true;
                    break;
                }
                case 1 : {
                    purchaseBuildings();
                    break;
                }
                case 2 : {
                    liquidateAssets();
                    break;
                }
            }
        }

        int[] diceRolls = {random.nextInt(7), random.nextInt(7)};
        lastRollForBrewery = diceRolls[0] + diceRolls[1];
        StatusEffects status = player.getStatusEffects();
        if (diceRolls[0] == diceRolls[1])
            status.addIdenticalDice(1);
        else status.setIdenticalDice(0);
        if (status.isImprisoned()){
            CardController pardonCard = player.getHeldCards().drawCardOfClass(PardonCard.class);
            switch (playerView.chooseJailRemoval(pardonCard != null, status.getTimeInJail() < 3)){
                case 49 : { // '1'
                    //Use pardon card
                    pardonCard.use(this);
                    break;
                }
                case 50 : { // '2'
                    if(status.getIdenticalDice() > 0) player.getStatusEffects().setImprisoned(false);
                    break;
                }
                case 51 : { // '3'
                    transferMoney(1000, null);
                    player.getStatusEffects().setImprisoned(false);
                }
            }
        }
        playerView.rollDice(diceRolls);
        if (status.getIdenticalDice() == 3){
            playerView.imprisonedDiceCheater();
            status.setImprisoned(true);
            status.setIdenticalDice(0);
            teleportTo(board.getSquareControllerFromId(31));
            return;
        }
        if (!status.isImprisoned())
            moveTo(board.getSquareControllerRelativeTo(player.getPosition(), diceRolls[0] + diceRolls[1]));
        if (status.getIdenticalDice() > 0) takeTurn(board);
    }

    /**
     *
     * @param squareController
     */
    public void teleportTo(SquareController squareController){
        getPlayer().updatePosition(squareController);
    }

    /**
     *
     * @param squareController
     */
    public void moveTo(SquareController squareController){
        teleportTo(squareController);
        squareController.onMoveTo(this);
    }


    /**
     *
     * @param deck
     * @return
     */
    public CardController drawCard(Deck deck){
        CardController card = deck.nextCard();
        //player.getHeldCards().addCard(card);
        player.getHeldCards().returnCard(card);
        card.onDraw(this);
        return card; //Should it return void?
    }




    /**
     * Transfers money to another player or the bank.
     * <p>If bankrupted by the transfer their buildings are first sold and
     * then everything the own is transferred to the creditor.</p>
     * <p>Otherwise, if the player does not have enough cash
     * assets of their own choosing are liquidated.</p>
     * @param debit the amount of money transferred out of the account.
     *              Can be a negative number in which case money is transferred into the account instead.
     * @param creditor the player which receives the money.
     *                 if creditor == null the money is given to the bank.
     *
     */
    public void transferMoney(int debit, PlayerController creditor){
        if (creditor == this) return;
        if (player.getTotalValue() < debit){ //We have gone bankrupt
            //We have gone bankrupt
            //Sell houses
            //Transfer cash
            //Transfer properties
            return;
        }
        if (player.getBalance() < debit) //We can pay but we don't have enough cash
        {
            liquidateAssets(debit - player.getBalance(), false);
        }
        addBalance(-debit);
        if (creditor != null){ //creditor == null -> creditor is the bank
            creditor.addBalance(debit);
        }
    }

    /**
     *
     * @return
     */
    public int liquidateAssets(){
         return liquidateAssets(-1, true);
    }

    /**
     *
     * @param minAmount
     * @param optional
     * @return
     */
    public int liquidateAssets(int minAmount, boolean optional){
        int startBalance = player.getBalance();

        while (true) {
            switch (playerView.chooseHowToLiquidate(optional)) {
                case 1: {
                    //TODO: implement choice of selling property or building(s)
                    playerView.choosePropertyToSell(player.getOwnedProperties()).sell();
                }
                case 2: {
                    OwnableSquareController[] nonMortgaged = player.getNonMortgagedProperties();
                    playerView.choosePropertyToMortgage(nonMortgaged).mortgage();
                }
                case 3: {
                    //TODO: implement trade
                    //Sell or trade properties and/or cards to other players.
                }
                case 4: {
                    return startBalance - player.getBalance();
                }
            }
            if (!optional){
                if (startBalance - player.getBalance() > minAmount) return (startBalance - player.getBalance()) + liquidateAssets();
            }
        }
    }


    //TODO: split into askToPurchaseProperty and purchaseProperty? That way it can be called after fx players has traded
    /**
     * <p>Asks the player if they want to purchase the property. If they do, payment is transferred to the current owner,
     * the property it is added to their list of owned properties, the owner of the property is changed to the player
     * and the method returns true. Otherwise it simply returns false.</p>
     * @param property the {@code PropertySquareController} of the property to, potentially, be purchased.
     * @param price the price of the property.
     * @return true if the property was purchased and false if it was not.
     */
    public boolean purchaseProperty(OwnableSquareController property, int price) {
        if (price > player.getTotalValue()) return false; //tell them maybe
        int missingCash = price - player.getBalance();
        do{
            if (!playerView.askPurchase(property.getName(), price, missingCash > 0))
                return false;
            if (missingCash > 0)
                liquidateAssets(missingCash, true);
        } while ((missingCash = price - player.getBalance()) > 0);
        transferMoney(price, property.getOwner()); //property.getOwner() should always return null here.
        property.setOwner(this); //This maybe shouldn't be done here? Maybe it should be done by the SquareController instead?
        return true;
    }

    /**
     *
     * @param propertySquareController
     */
    public void addOwnedProperty(OwnableSquareController propertySquareController){
        player.addOwnedProperty(propertySquareController);
    }

    /**
     *
     * @param propertySquareController
     */
    public void removeOwnedProperty(OwnableSquareController propertySquareController){
        player.removeOwnedProperty(propertySquareController);
    }


    public void purchaseBuildings(){
        //Todo: limit the number of houses and hotels in play at once
        PropertySquareController[] buildableProperties = player.getBuildableProperties();
        if (buildableProperties.length == 0) return;
        PropertySquareController toBuild = playerView.choosePropertyBuildBuilding(buildableProperties);
        //TODO: the player should be able to change their mind.
        while (player.getBalance() < toBuild.getBuildingCost()){
            liquidateAssets(toBuild.getBuildingCost() - player.getBalance(), true);
            //TODO: the player should be able to pick another property
        }
        transferMoney(toBuild.getBuildingCost(), null);
        toBuild.addHouse();
    }


    /**
     * Add value of parameter {@code value} to current balance
     *
     * @param value the value to be added to the balance. Can be a negative number.
     * @return {@code getBalance()} new balance
     */
    public int addBalance(int value) {
        return player.setBalance(player.getBalance() + value);
    }

    public String getName(){
        return player.getName();
    }

    public Color[] getColors(){
        return player.getColors();
    }

    public Deck getHeldCards(){
        return player.getHeldCards();
    }

    //Preferably don't use this; it might be removed in the future.
    public Player getPlayer() {
        return player;
    }

    public StatusEffects getStatusEffects(){
        return player.getStatusEffects();
    }

    /**
     * Returns the total number of buildings owned by the player, that is, the sum of all owned hotels and houses.
     * @return an {@code int} representing the total number of buildings owned by the player
     */
    public int getTotalNumberOfBuildings(){
        return getTotalNumberOfHouses() + getTotalNumberOfHotels();
    }

    /**
     * Returns the total number of houses owned by the player.
     * @return an {@code int} representing the total number of house owned by the player
     */
    public int getTotalNumberOfHouses(){
        int numberOfHouses = 0;
        for (PropertySquareController property: getPlayer().getBuildableProperties()) {
            if (property.getNumHouses() != property.getMaxNumHouses()) numberOfHouses += property.getNumHouses();
        }
        return numberOfHouses;
    }

    /**
     * Returns the total number of hotels owned by the player.
     * @return an {@code int} representing the total number of hotels owned by the player
     */
    public int getTotalNumberOfHotels(){
        int numberOfHotels = 0;
        for (PropertySquareController property: getPlayer().getBuildableProperties()) {
            if (property.getNumHouses() != property.getMaxNumHouses()) numberOfHotels += property.getNumHouses();
        }
        return numberOfHotels;
    }




    //This makes me sad
    public int getLastRollForBrewery(){
        return lastRollForBrewery;
    }


}
