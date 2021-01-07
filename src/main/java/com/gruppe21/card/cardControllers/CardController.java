package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;
import com.gruppe21.card.typeOfCards.Card;

public class CardController {
    private Card card;
    private CardView cardView;

    public CardController(Card cardModel, CardView view) {
        this.card = cardModel;
        this.cardView = view;
    }



}
