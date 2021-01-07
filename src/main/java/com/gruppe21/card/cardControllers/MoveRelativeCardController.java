package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;
import com.gruppe21.card.typeOfCards.Card;

public class MoveRelativeCardController {
    private Card card;
    private CardView cardView;

    public MoveRelativeCardController(Card cardModel, CardView view) {
        this.card = cardModel;
        this.cardView = view;
    }
}
