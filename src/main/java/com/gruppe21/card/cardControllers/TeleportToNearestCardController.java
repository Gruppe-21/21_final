package com.gruppe21.card.cardControllers;

import com.gruppe21.card.cardView.CardView;
import com.gruppe21.card.cardType.TeleportToNearestCard;
import com.gruppe21.game.Board;
import com.gruppe21.player.PlayerController;

public class TeleportToNearestCardController extends CardController{
    private TeleportToNearestCard card;
    private CardView view;

    public TeleportToNearestCardController(CardView view, TeleportToNearestCard card) {
        super(view, card);
    }


    /**
     * Calls card use method when the {code:drawer}
     * @param drawer Player that draws card
     */
    @Override
    public void onDraw(PlayerController drawer) {
        use(drawer);
    }

    /**
     * Method that shows description and moves player {code user} to specific squareID
     * @param user Player that uses card
     */
    @Override
    public void use(PlayerController user, Board board){
        super.use(user);
        int moveToSquareID = card.getSquareID();

        // move player to prison square ID
        user.moveTo(board.getSquareControllerFromId(moveToSquareID));

        // return card
        user.getHeldCards().removeCard(this);
        getReturnDeck().returnCard(this);
    }
}
