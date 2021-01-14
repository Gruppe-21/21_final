package com.gruppe21.card.cardControllers;

import com.gruppe21.card.cardView.CardView;
import com.gruppe21.card.cardType.Card;
import com.gruppe21.player.PlayerController;

public class PardonCardController extends CardController {
    public PardonCardController(CardView view, Card card) {
        super(view, card);
    }


    @Override
    public void onDraw(PlayerController drawer) {
        cardView.displayCard(card.getDescriptionOnDrawLabel());
    }

    @Override
    public void use(PlayerController user) {
        user.getStatusEffects().setImprisoned(false);
        super.use(user);
    }



}
