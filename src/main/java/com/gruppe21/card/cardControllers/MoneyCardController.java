package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;
import com.gruppe21.card.typeOfCards.ModifyMoneyCard;
import com.gruppe21.deck.Deck;

public class MoneyCardController extends CardController  {
    private ModifyMoneyCard card;
    private final int modifyValue;

    public MoneyCardController(CardView view, ModifyMoneyCard card, int modifyValue) {
        super(view, card);
        this.modifyValue = modifyValue;
    }


    @Override
    protected void onDraw(Deck deck) {

    }

    @Override
    protected void use(Deck deck, CardController putBackCard){
        super.use(deck,putBackCard);

    }

}
