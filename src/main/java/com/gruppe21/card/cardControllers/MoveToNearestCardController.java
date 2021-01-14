package com.gruppe21.card.cardControllers;

import com.gruppe21.card.cardType.Card;
import com.gruppe21.card.cardType.MoveToNearestCard;
import com.gruppe21.card.cardView.CardView;
import com.gruppe21.board.Board;
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

    /**
     * Method finds the nearest SquareController in {code:board} with Board's closestSquareController()
     * method.The method calcualtes the {code:user} position relative to the closest squareController
     * and uses PlayerControllers moveTo() method.
     * @param user Player using the card
     * @param board Board which the game is played with
     */
    @Override
    public void use(PlayerController user, Board board){
        super.use(user);

        int[] squaresIdArr = card.getIDSquares();
        SquareController[] squareControllersArr = new SquareController[squaresIdArr.length];

        // convert ID to SquareController
        for(int i=0;i<squaresIdArr.length;i++){
            squareControllersArr[i] = board.getSquareControllerFromId(squaresIdArr[i]);
        }

        // move player to nearest SquareController in squareControllersArr
        user.moveTo(board.closestSquareController(user.getPlayer().getPosition(), squareControllersArr));

        // return card
        user.getHeldCards().removeCard(this);
        getReturnDeck().returnCard(this);
    }

}
