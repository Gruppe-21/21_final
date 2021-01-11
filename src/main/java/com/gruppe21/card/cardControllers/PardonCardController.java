package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;
import com.gruppe21.card.typeOfCards.Card;
import com.gruppe21.card.typeOfCards.PardonCard;
import com.gruppe21.player.PlayerController;

public class PardonCardController extends CardController {
    protected CardView cardView;
    protected Card card;

    public PardonCardController(CardView view, PardonCard card) {
        super(view, card);
    }


    @Override
    public void onDraw(PlayerController drawer) {
        cardView.displayCard(card.getDescriptionOnDrawLabel());
    }

    @Override
    public void use(PlayerController user) {
        super.use(user); // text to view
    }



}
