package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;

public abstract class CardController {
    private CardView cardView;

    public CardController(CardView cardView){
        this.cardView = cardView;
    }

}
