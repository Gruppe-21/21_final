package com.gruppe21.card.moveCards.controllers;

import com.gruppe21.card.cardControllers.controllers.CardController;
import com.gruppe21.card.model.Card;
import com.gruppe21.card.view.CardView;
import com.gruppe21.game.Board;
import com.gruppe21.game.GameController;
import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.controllers.SquareController;

public abstract class RelocationCardController extends CardController {

    public RelocationCardController(CardView cardView, Card card){
        super(cardView, card);
    }

    /**
     * Method that shows description and moves player {code user} to specific squareID
     *
     * @param user Player that uses card
     */
    @Override
    public void use(PlayerController user) {
        super.use(user);
        Board board = GameController.getInstance().getBoard();
        relocate(user, getDestination(user, board));
    }

    protected abstract void relocate(PlayerController user, SquareController destination);

   protected abstract SquareController getDestination(PlayerController user, Board board);


}
