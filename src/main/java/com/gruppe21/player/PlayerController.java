package com.gruppe21.player;

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
            status.setIdenticalDice(status.getIdenticalDice() + 1);
        else status.setIdenticalDice(0);
        if (player.getStatusEffects().isImprisoned()){
            //Implement prison
            player.getStatusEffects().setImprisoned(false);
        }
        playerView.rollDice(diceRolls);
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
        player.getHeldCards().addCard(card);
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
        if (player.getTotalValue() < debit){
            //We have gone bankrupt
            //Sell houses
            //Transfer cash
            //Transfer properties
            return;
        }
        if (player.getBalance() < debit)
        {
            debit -= player.getBalance();
            transferMoney(player.getBalance(), creditor); //If we don't have enough cash, we first transfer what we have.
            debit -= sellProperties(debit, creditor); //It should be possible to trade with other players here too
        }
        addBalance(-debit);
        if (creditor != null){ //creditor == null -> creditor is the bank
            creditor.addBalance(debit);
        }
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
