package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;
import com.gruppe21.card.typeOfCards.Card;
import com.gruppe21.player.PlayerController;

public abstract class CardController {
    protected CardView cardView;
    protected Card card;

    public CardController(CardView cardView, Card card){
        this.cardView = cardView;
        this.card = card;
    }

    protected abstract void onDraw(PlayerController playerController);

}
