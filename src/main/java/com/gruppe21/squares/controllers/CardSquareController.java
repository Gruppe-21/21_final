package com.gruppe21.squares.controllers;

import com.gruppe21.card.cardControllers.CardController;
import com.gruppe21.deck.Deck;
import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.Square;
import com.gruppe21.squares.views.SquareView;

public class CardSquareController extends SquareController {
    public CardSquareController(Square model, SquareView view) {
        super(model, view);

    }

    @Override
    public void onMoveTo(PlayerController playerController) {
        super.onMoveTo(playerController);
        return Deck.nextCard();
    }
}
