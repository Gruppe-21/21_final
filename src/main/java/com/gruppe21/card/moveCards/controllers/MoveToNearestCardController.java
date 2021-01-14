package com.gruppe21.card.moveCards.controllers;

import com.gruppe21.card.moveCards.model.TeleportToNearestCard;
import com.gruppe21.card.view.CardView;
import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.controllers.SquareController;

public class MoveToNearestCardController extends TeleportToNearestCardController {

    public MoveToNearestCardController(CardView cardView, TeleportToNearestCard card) {
        super(cardView, card);
    }

    @Override
    protected void relocate(PlayerController user, SquareController destination){
        user.moveTo(destination);
    }

}
