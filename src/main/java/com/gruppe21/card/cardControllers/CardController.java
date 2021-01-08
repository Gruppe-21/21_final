package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;
import com.gruppe21.card.typeOfCards.MoveRelativeCard;

public abstract class CardController {
    private CardView cardView;

    public CardController(CardView cardView){
        this.cardView = cardView;
    }

    protected abstract void onDraw(CardView view, MoveRelativeCard model);

}
