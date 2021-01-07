package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;
import com.gruppe21.card.typeOfCards.Card;
import com.gruppe21.card.typeOfCards.ModifyMoneyCard;

public class MoneyCardController extends CardController  {
    private ModifyMoneyCard card;

    public MoneyCardController(CardView view, ModifyMoneyCard cardModel) {
        super(view);
        this.card = cardModel;
    }

}
