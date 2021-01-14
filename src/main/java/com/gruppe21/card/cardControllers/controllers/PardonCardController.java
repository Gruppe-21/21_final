package com.gruppe21.card.cardControllers.controllers;

import com.gruppe21.card.model.PardonCard;
import com.gruppe21.card.view.CardView;
import com.gruppe21.player.PlayerController;

public class PardonCardController extends CardController {
    public PardonCardController(CardView view, PardonCard card) {
        super(view, card);
    }

    @Override
    public void onDraw(PlayerController drawer) {
        cardView.displayCard(((PardonCard)card).getDescriptionOnDrawLabel());
    }

}
