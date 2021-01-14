package com.gruppe21.card.cardControllers;

import com.gruppe21.card.cardType.TeleportToNearestCard;
import com.gruppe21.card.cardView.CardView;
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
