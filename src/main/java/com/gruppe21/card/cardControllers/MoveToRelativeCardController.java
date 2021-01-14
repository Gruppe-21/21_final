package com.gruppe21.card.cardControllers;


import com.gruppe21.card.cardType.MoveRelativeCard;
import com.gruppe21.card.cardType.TeleportToNearestCard;
import com.gruppe21.card.cardView.CardView;
import com.gruppe21.game.Board;
import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.controllers.SquareController;

public class MoveToRelativeCardController extends RelocationCardController {

    public MoveToRelativeCardController(CardView cardView, MoveRelativeCard card) {
        super(cardView, card);
    }

    protected SquareController getDestination(PlayerController user, Board board) {
        return board.getSquareControllerRelativeTo(user.getPlayer().getPosition(), ((MoveRelativeCard) card).getMoveSquares());
    }

    protected void relocate(PlayerController user, SquareController destination){
        user.moveTo(destination);
    }

}
