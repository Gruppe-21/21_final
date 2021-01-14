package com.gruppe21.card.cardControllers;

import com.gruppe21.card.cardType.MoveRelativeCard;
import com.gruppe21.card.cardView.CardView;
import com.gruppe21.board.Board;
import com.gruppe21.player.PlayerController;

public class MoveToRelativeCardController extends CardController {
    MoveRelativeCard card;
    CardView view;

    public MoveToRelativeCardController(CardView cardView, MoveRelativeCard card) {
        super(cardView, card);
    }


    @Override
    public void onDraw(PlayerController drawer) {
        use(drawer);
    }

    @Override
    public void use(PlayerController user, Board board){
        super.use(user);
        int moveSquare = card.getMoveSquares();

        // move player a moveSquare distance forwards or backwards
        user.moveTo(board.getSquareControllerRelativeTo(user.getPlayer().getPosition(),moveSquare));

        // return card
        user.getHeldCards().removeCard(this);
        getReturnDeck().returnCard(this);
    }

}
