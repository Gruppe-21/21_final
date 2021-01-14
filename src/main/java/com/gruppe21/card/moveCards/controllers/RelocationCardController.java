package com.gruppe21.card.moveCards.controllers;

import com.gruppe21.card.cardControllers.UseOnDrawCardController;
import com.gruppe21.card.cardType.Card;
import com.gruppe21.card.view.CardView;
import com.gruppe21.game.Board;
import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.controllers.SquareController;

public abstract class RelocationCardController extends UseOnDrawCardController {

    public RelocationCardController(CardView cardView, Card card){
        super(cardView, card);
    }

    /**
     * Method that shows description and moves player {code user} to specific squareID
     *
     * @param user Player that uses card
     */
    @Override
    public void use(PlayerController user, Board board) {
        super.use(user);

        relocate(user, getDestination(user, board));

        // return card
        returnToDeck(user);
    }

    protected abstract void relocate(PlayerController user, SquareController destination);

   protected abstract SquareController getDestination(PlayerController user, Board board);


}
