package com.gruppe21.card.cardControllers;

import com.gruppe21.card.cardView.CardView;
import com.gruppe21.card.cardType.Card;
import com.gruppe21.card.cardType.PardonCard;
import com.gruppe21.player.PlayerController;

public class PardonCardController extends CardController {
    private CardView cardView;
    private Card card;

    public PardonCardController(CardView view, PardonCard card) {
        super(view, card);
    }


    @Override
    public void onDraw(PlayerController drawer) {
        cardView.displayCard(card.getDescriptionOnDrawLabel());
    }

    @Override
    public void use(PlayerController user) {
        super.use(user);
    }



}
