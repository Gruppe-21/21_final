package com.gruppe21.card.cardControllers;

import com.gruppe21.card.cardType.Card;
import com.gruppe21.card.cardType.MoveToNearestCard;
import com.gruppe21.card.cardView.CardView;
import com.gruppe21.game.Board;
import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.controllers.SquareController;

public class MoveToNearestCardController extends CardController {
    MoveToNearestCard card;
    CardView view;


    public MoveToNearestCardController(CardView cardView, Card card) {
        super(cardView, card);
    }

    @Override
    public void onDraw(PlayerController drawer) {
        use(drawer);
    }

    @Override
    public void use(PlayerController user, Board board){
        super.use(user);

        int[] squaresIdArr = card.getIDSquares();
        SquareController[] squareControllers = new SquareController[squaresIdArr.length];

        // convert ID to SquareController
        for(int i=0;i<squaresIdArr.length;i++){
            squareControllers[i] = board.getSquareControllerFromId(squaresIdArr[i]);
        }

        // move player to nearest square in squareControllers
        user.moveTo(board.closestSquareController(user.getPlayer().getPosition(), squareControllers));

        // return card
        user.getHeldCards().removeCard(this);
        getReturnDeck().returnCard(this);
    }




}
