package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;
import com.gruppe21.card.typeOfCards.MoveRelativeCard;

public class MoveRelativeCardController extends CardController {
    private MoveRelativeCard cardModel;

    public MoveRelativeCardController(CardView view, MoveRelativeCard cardModel) {
        super(view);
        this.cardModel = cardModel;
    }
}
