package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;
import com.gruppe21.card.typeOfCards.MoveRelativeCard;
import com.gruppe21.player.PlayerController;
import com.gruppe21.game.Board;

public class MoveRelativeCardController extends CardController {
    private MoveRelativeCard card;
    private CardView view;

    public MoveRelativeCardController(CardView view, MoveRelativeCard card) {
        super(view, card);
    }

    @Override
    public void onDraw(PlayerController drawer) {
        use(drawer);
    }

    @Override
    public void use(PlayerController user){
        super.use(user);
        int moveToSquareID = card.getSquareID();

        user.moveTo(Board.getSquareControllerFromId(moveToSquareID));

    }

}
