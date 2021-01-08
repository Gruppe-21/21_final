package com.gruppe21.player;

import com.gruppe21.card.cardControllers.CardController;
import com.gruppe21.card.typeOfCards.PardonCard;
import com.gruppe21.deck.Deck;
import com.gruppe21.game.GameController;
import com.gruppe21.squares.controllers.SquareController;
import gui_fields.GUI_Player;

import java.util.Random;

public class PlayerController {

    private Player player;
    private PlayerView playerView;
    private GameController gameController;
    private static final Random random = new Random();

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
     */
    public void takeTurn(Board board){
        int[] diceRolls = {random.nextInt(7), random.nextInt(7)};
        StatusEffects status = player.getStatusEffects();
        if (diceRolls[0] == diceRolls[1])
            status.addIdenticalDice(1);
        else status.setIdenticalDice(0);
        if (status.isImprisoned()){
            switch (playerView.chooseJailRemoval(player.getHeldCards().getCardOfClass(PardonCard.class), status.getTimeInJail() < 3)){
                case 49 : { // '1'
                    //Use pardon card
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

        if (!status.isImprisoned())
            moveTo(board.getSquareControllerRelativeTo(player.getPosition(), diceRolls[0] + diceRolls[1]));
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
     * Transfers money to another player. If the player does not have enough money, their properties are sold.
     * @param debit is the amount of money transferred out of the account.
     *              Can be a negative number in which case money is transferred into the account instead.
     * @param creditor is the player which receives the money.
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
            liquidateAssets(debit - player.getBalance());
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
    public void liquidateAssets(){
         liquidateAssets(-1);
    }

    /**
     *
     * @param minAmount
     * @return
     */
    public void liquidateAssets(int minAmount){
        //TODO: Implement liquidateAssets
        //Sell houses, hotels and/or properties to the bank
        //Sell or trade properties and/or cards to other players.
    }



    /**
     * add value of parameter "amount" to current balance
     *
     * @param amount
     * @return getBalance() new balance
     */
    public int addBalance(int value) {
        return player.setBalance(player.getBalance() + value);
    }

    public String getName(){
        return player.getName();
    }

    public Player getPlayer() {
        return player;
    }
}
