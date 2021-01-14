package com.gruppe21.card.cardControllers;

import com.gruppe21.card.cardView.CardView;
import com.gruppe21.card.cardType.TeleportToNearestCard;
import com.gruppe21.game.Board;
import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.controllers.SquareController;

public class TeleportToNearestCardController extends CardController {

    public TeleportToNearestCardController(CardView view, TeleportToNearestCard card) {
        super(view, card);
    }


    /**
     * Calls card use method when the {code:drawer}
     *
     * @param drawer Player that draws card
     */
    @Override
    public void onDraw(PlayerController drawer) {
        use(drawer);
    }

    /**
     * Method that shows description and moves player {code user} to specific squareID
     *
     * @param user Player that uses card
     */
    @Override
    public void use(PlayerController user, Board board) {
        super.use(user);

        SquareController destination = board.closestSquareController(user.getPlayer().getPosition(), ((TeleportToNearestCard) card).getPossibleDestinations());
        relocate(user, destination);

        // return card
        returnToDeck(user);
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
