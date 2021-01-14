package com.gruppe21.card.moveCards.controllers;

import com.gruppe21.card.view.CardView;
import com.gruppe21.card.moveCards.model.TeleportToNearestCard;
import com.gruppe21.game.Board;
import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.controllers.SquareController;

public class TeleportToNearestCardController extends RelocationCardController {

    public TeleportToNearestCardController(CardView view, TeleportToNearestCard card) {
        super(view, card);
    }

    protected SquareController getDestination(PlayerController user, Board board){
        return board.closestSquareController(user.getPlayer().getPosition(), ((TeleportToNearestCard) card).getPossibleDestinations());
    }

    protected void relocate(PlayerController user, SquareController destination){
        user.teleportTo(destination);
    }

    public void setPossibleDestinations(SquareController[] possibleDestinations) {
        ((TeleportToNearestCard) card).setPossibleDestinations(possibleDestinations);
    }

    public int[] getSquareIDs() {
        return ((TeleportToNearestCard) card).getSquareIDs();
    }
}
